package logisticassa.persistencia.sql;

import java.util.ArrayList;
import java.sql.*;
import logisticassa.persistencia.RotasDAO;
import logisticassa.ui.vo.RotasVO;
import logisticassa.entidades.*;
import logisticassa.exception.*;

public class RotaDAOSQL extends GenericDAOSQL implements RotasDAO {
	
	/*			
				  	
				  	SQL DAS TABELAS PARA DOCUMENTA��O
				  	
					create table rotas (	
					
							id int,		
							nome varchar(255),
							destino varchar(255),
							origem varchar(255),
							tempoEntrega int,	
							custoGrama double precision,
							capacidadeTotal double precision,
							capacidadeAlocada double precision,	
							tipo varchar(1),
							constraint id primary key ( id )
						
					);
						
					create table fracionarias (
						

							id_fracionada int,
							id_rota int,
							foreign key ( id_fracionada ) references rotas( id ),
							foreign key ( id_rota ) references rotas( id )	
							
					);
				  	
				  	
				  	
				  	
				  	
	 * 
	 * */

	
	private static final String INSERT_ROTA = "INSERT INTO rotas ( id, nome, destino, origem, tempoEntrega, custoGrama, capacidadeTotal, capacidadeAlocada, tipo ) "
																		+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
	
	private static final String INSERT_FRACIONARIA = "INSERT INTO fracionarias ( id_fracionada, id_rota ) VALUES ( ?, ? )";
		
	private static final String UPDATE_BY_ID_FRACIONARIAS = "UPDATE rotas SET capacidadealocada = ? WHERE id IN ( SELECT f.id_rota  FROM fracionarias AS f WHERE f.id_fracionada = ? )";	
	
	private static final String SELECT_BY_NAME = "SELECT * FROM rotas WHERE nome = ? ";
	
	private static final String SELECT_BY_ORIGEM = "SELECT * FROM rotas WHERE origem = ? ";
	
	private static final String SELECT_BY_DESTINO = "SELECT * FROM rotas WHERE destino = ? ";
	
	private static final String SELECT_ALL = "SELECT * FROM rotas ORDER BY id ";
	
	private static final String UPDATE_BY_ID = "UPDATE rotas SET capacidadeAlocada = ? WHERE id = ? ";
	
	private static final String SELECT_BY_ID = "SELECT * FROM rotas WHERE id = ?";
	
	private static final String SELECT_BY_ORIGEM_AND_DESTINO = "SELECT * FROM rotas WHERE origem = ? AND destino = ? ";
	
	private static final String SELECT_DESTINO = "SELECT destino FROM rotas GROUP BY destino ORDER BY destino";
	
	private static final String SELECT_ORIGEM = "SELECT origem FROM rotas GROUP BY origem ORDER BY origem";
	
	private static final String SELECT_DESTINO_BY_ORIGEM = "SELECT destino FROM rotas WHERE origem = ? GROUP BY destino, origem ORDER BY origem";
	
	

	@Override
	public void save( Rota rota ) throws Exception {
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.INSERT_ROTA );
		
		char tipo = '\0';
		
		if( rota instanceof Diretas ) {
			tipo = 'D';
		}
		else if ( rota instanceof Fracionadas ) {
			tipo = 'F';			
		}

		pStmt.setInt( 1, rota.getId() );
		pStmt.setString( 2, rota.getNome() );
		pStmt.setString( 3, rota.getDestino() );
		pStmt.setString( 4, rota.getOrigem() );
		pStmt.setInt( 5, rota.getTempoEntrega() );
		pStmt.setDouble( 6, rota.getCustoGrama() );
		pStmt.setDouble( 7, rota.getCapacidadeTotal() );
		pStmt.setDouble( 8, rota.getCapacidadeAlocada() );
		pStmt.setString( 9, Character.toString( tipo ) );
		
		pStmt.executeUpdate();
		
		if( rota instanceof Fracionadas ) {

			this.saveFracionada( ( Fracionadas ) rota );
			
		}
		

	}
	
	private void saveFracionada( Fracionadas rota ) throws Exception {
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.INSERT_FRACIONARIA );
		
		for( Rota atual : rota.rotas ) {
			
			pStmt.setInt( 1, rota.getId() );
			pStmt.setInt( 2, atual.getId() );
			pStmt.executeUpdate();
			
		}
		
	}
	
	public void updateAlocadaByIdFracionadas( int id, double capacidadeAlocada ) throws Exception {
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.UPDATE_BY_ID_FRACIONARIAS );
		
		pStmt.setDouble( 1, capacidadeAlocada );
		pStmt.setInt( 2, id );
		
		pStmt.executeUpdate();
		
		this.updateAlocadaById(id, capacidadeAlocada);

	}
	

	@Override
	public ArrayList<Rota> findByName( String nome ) throws Exception {
		
		ArrayList<Rota> rota = null;
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_NAME );
		
		pStmt.setString( 1, nome );
		
		ResultSet rSet = pStmt.executeQuery();
		
		rota = this.createRota( rSet );
		
		rSet.close();
		pStmt.close();
		
		if( rota == null )
			throw new RotaNaoEncontradaIdNomeException( nome );
		
		return rota;
		
	}
	
	private ArrayList<Rota> createRota( ResultSet rSet ) throws SQLException {
		
		ArrayList<Rota> rotas = new ArrayList<Rota>();
		
		Rota rota = null;
		
		while( rSet.next() ) {
			
			int id = rSet.getInt("id");
			String nome = rSet.getString("nome");
			String destino = rSet.getString("destino");
			String origem = rSet.getString("origem");
			int tempoEntrega = rSet.getInt("tempoEntrega");
			double custoGrama = rSet.getDouble("custoGrama");
			double capacidadeTotal = rSet.getDouble("capacidadeTotal");
			double capacidadeAlocada = rSet.getDouble("capacidadeAlocada");
							
			rota = new Diretas( id, nome, destino, origem, tempoEntrega, custoGrama, capacidadeTotal );
			rota.alocar( capacidadeAlocada );

			
			rotas.add( rota );
		}
			
			return rotas;
			
	}
	
	
	private ArrayList<RotasVO> createRotaVO( ResultSet rSet ) throws SQLException {
				
		ArrayList<RotasVO> rotas = new ArrayList<RotasVO>();
		
		RotasVO rota = null;
		
		while( rSet.next() ) {
			
			int id = rSet.getInt("id");
			String nome = rSet.getString("nome");
			String destino = rSet.getString("destino");
			String origem = rSet.getString("origem");
			int tempoEntrega = rSet.getInt("tempoEntrega");
			double custoGrama = rSet.getDouble("custoGrama");
			double capacidadeTotal = rSet.getDouble("capacidadeTotal");
			double capacidadeAlocada = rSet.getDouble("capacidadeAlocada");
			char tipo = rSet.getString("tipo").charAt(0);
		
			rota = new RotasVO(nome, id, tempoEntrega, capacidadeTotal, capacidadeAlocada, custoGrama, origem, destino, tipo);

			rotas.add( rota );

		}
			
			return rotas;
			
	}
	

	@Override
	public ArrayList<Rota> findByOrigem( String origem ) throws Exception {

		ArrayList<Rota> rota = null;
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_ORIGEM );
		
		pStmt.setString( 1, origem );
		
		ResultSet rSet = pStmt.executeQuery();
		
		rota = this.createRota( rSet );
		
		rSet.close();
		pStmt.close();
		
		if( rota == null )
			throw new RotaNaoEncontradaOrigemException( origem );

		
		return rota;
		
	}

	@Override
	public ArrayList<Rota> findByDestino( String destino ) throws Exception {


		ArrayList<Rota> rota = null;
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_DESTINO );
		
		pStmt.setString( 1, destino );
		
		ResultSet rSet = pStmt.executeQuery();
		
		rota = this.createRota( rSet );
		
		rSet.close();
		pStmt.close();
		
		if( rota == null )
			throw new RotaNaoEncontradaDestinoException( destino );
		
		return rota;

	}

	@Override
	public ArrayList<Rota> findAll() throws Exception {
		
		ArrayList<Rota> rota = new ArrayList<Rota>();
		
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_ALL );
		
		ResultSet rSet = pStmt.executeQuery();
	
		rota = this.createRota( rSet );	
		 
		return rota;
		
	}
	
	public ArrayList<RotasVO> findAllVO() throws Exception {
		
		ArrayList<RotasVO> rota = new ArrayList<RotasVO>();

		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_ALL );
		
		ResultSet rSet = pStmt.executeQuery();
					
		rota = this.createRotaVO( rSet );
 
		return rota;
		
	}

	@Override
	public void updateAlocadaById( int id, double capacidadeAlocada ) throws Exception {

		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.UPDATE_BY_ID );
		
		pStmt.setDouble( 1, capacidadeAlocada );
		pStmt.setInt( 2, id );
		
		pStmt.executeUpdate();
		

	}
	
	
	public RotasVO findByIdRota( int id ) throws Exception {
		
		ArrayList<RotasVO> rota = null;
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_ID );
		
		pStmt.setInt( 1, id );
		
			
		ResultSet rSet = pStmt.executeQuery();
			
		rota = this.createRotaVO( rSet );
		
		rSet.close();
		pStmt.close();
		
		if( rota == null ) {
			
			throw new RotaNaoEncontradaIdNomeException( id );
			
		}

		return rota.get( 0 );
		
	}
	
	
	@Override
	public ArrayList<Rota> findById( int id ) throws Exception {
		
		ArrayList<Rota> rota = null;
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_ID );
		
		pStmt.setInt( 1, id );
			
		ResultSet rSet = pStmt.executeQuery();
			
		rota = this.createRota( rSet );
		
		rSet.close();
		pStmt.close();
		
		if( rota == null ) {
			
			throw new RotaNaoEncontradaIdNomeException( id );
			
		}
		
		return rota;
		
	}

	@Override
	public ArrayList<Rota> findByOrigemAndDestino(String origem, String destino) throws Exception {

		ArrayList<Rota> rota = null;
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_ORIGEM_AND_DESTINO );
		
		pStmt.setString( 1, origem );
		pStmt.setString( 2, destino );
		
		ResultSet rSet = pStmt.executeQuery();
		
		rota = this.createRota( rSet );
		
		rSet.close();
		pStmt.close();
		
		if( rota == null ) {
			
			throw new RotaNaoEncontradaOrigemDestinoException(origem, destino);
			
		}
		
		return rota;

	}
	
	public ArrayList<RotasVO> findByOrigemAndDestinoVO( String origem, String destino ) throws Exception {

		ArrayList<RotasVO> rota = new ArrayList<RotasVO>();
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_BY_ORIGEM_AND_DESTINO );
		
		pStmt.setString( 1, origem );
		pStmt.setString( 2, destino );
		
		ResultSet rSet = pStmt.executeQuery();
		

		
		rota = this.createRotaVO( rSet );
		

		
		rSet.close();
		pStmt.close();

		
		return rota;

	}

	@Override
	public ArrayList<String> findDestino() throws Exception {

		ArrayList<String> strings = new ArrayList<String>();
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_DESTINO );
		
		ResultSet rSet = pStmt.executeQuery();
		
		
		while( rSet.next() ) {
			
			strings.add( rSet.getString( "destino"));
			
		}
		
		return strings;
	}

	@Override
	public ArrayList<String> findOrigem() throws Exception {
		
		ArrayList<String> strings = new ArrayList<String>();
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_ORIGEM );
		
		ResultSet rSet = pStmt.executeQuery();
		
		
		while( rSet.next() ) {
			
			strings.add( rSet.getString( "origem"));
			
		}
		
		return strings;
	}

	@Override
	public ArrayList<String> findDestinoByOrigem( String origem ) throws Exception {
		
		ArrayList<String> strings = new ArrayList<String>();
		
		PreparedStatement pStmt = this.getConnection().prepareStatement( RotaDAOSQL.SELECT_DESTINO_BY_ORIGEM );
		
		pStmt.setString( 1, origem );
		
		ResultSet rSet = pStmt.executeQuery();
		
		while( rSet.next() ) {
			
			strings.add( rSet.getString("destino") );
			
		}
		
		return strings;
	}


}
