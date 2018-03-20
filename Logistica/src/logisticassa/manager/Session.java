package logisticassa.manager;

import java.util.ArrayList;
import logisticassa.Logistica;
import logisticassa.ui.vo.RotasVO;
import logisticassa.persistencia.sql.*;
import logisticassa.entidades.*;

public class Session implements Logistica {
	
	RotaDAOSQL sql = new RotaDAOSQL();

	@Override
	public void registrarRotaFracionada(int id, String nome, int[] ids) throws Exception {
		
		Fracionadas nova = new Fracionadas( id, nome );
		
		ArrayList<Rota> rotasId = new ArrayList<Rota>();
		
		for( int i = 0 ; i < ids.length ; i ++ ) {
			
			rotasId.add( sql.findById( ids[i] ).get( 0 ) );
			
		}
		
		for( Rota rota : rotasId ) {
			
			nova.add( rota );			
			
		}
		
		
		this.sql.save( nova );
		

	}

	@Override
	public void registrarRotaDireta( int id, String nome, String destino, String origem, int tempoEntrega,
									 double custoGrama, double capacidadeTotal ) throws Exception {
		
		Rota nova = new Diretas( id, nome , destino, origem, tempoEntrega, custoGrama, capacidadeTotal );
		
		nova.getCapacidadeAlocada();
		
		this.sql.save( nova );

	}

	@Override
	public ArrayList<String> gerarOrigem() throws Exception {

		
		return this.sql.findOrigem();
		
	}

	@Override
	public ArrayList<String> gerarDestino( String origem ) throws Exception {
		
		return this.sql.findDestinoByOrigem( origem );
	}

	@Override
	public boolean gerarContratacaoDeTransporte( int id, double peso ) throws Exception {
		
		RotasVO rotas = this.sql.findByIdRota( id );
		
		if( rotas.getTipo() == 'D' ) {
		
			if( rotas.alocar( peso ) ) {
	
				
				this.sql.updateAlocadaById( id , rotas.getCapacidadeAlocada());
				
				return true;
				
			}
			
			else {
				
				return false;
				
			}
		
		}
	
		else if ( rotas.getTipo() == 'F' ) {
			
			if( rotas.alocar( peso ) ) {
				
				this.sql.updateAlocadaByIdFracionadas( id, rotas.getCapacidadeAlocada() );
				
				return true;
				
			}
			
			else {
				
				return false;
				
			}
			
			
		}
		
		else {
			
			return false;
			
		}
		
		
	}

	@Override
	public ArrayList<RotasVO> gerarRotas() throws Exception {
		
		
		return this.sql.findAllVO();
		
	}

	@Override
	public ArrayList<RotasVO> findByOrigemAndDestino( String origem, String destino ) throws Exception {
		
		return this.sql.findByOrigemAndDestinoVO( origem, destino );
	}
	
	

}
