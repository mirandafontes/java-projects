package inf008.persistencia.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import inf008.contabil.entidades.Ativo;
import inf008.contabil.entidades.Conta;
import inf008.contabil.entidades.Passivo;
import inf008.contabil.entidades.PatrimonioLiquido;
import inf008.exception.ContaNaoEncontradaException;
import inf008.persistencia.ContaDAO;

public class ContaDAOSQL extends GenericDAOSQL implements ContaDAO{
	
	
	private static final String INSERT_CONTA = "INSERT INTO CONTA(id, saldo, nome, emUso, tipo) VALUES(?, ?, ?, ?, ?)";
	private static final String UPDATE_CONTA = "UPDATE CONTA SET saldo = ?, nome = ?, emUso = ? WHERE id = ?";
	private static final String SELECT_CONTA_BY_NAME = "SELECT id, saldo, nome, emUso, tipo FROM conta WHERE nome = ?";
	private static final String SELECT_CONTA_BY_ID = "SELECT id, saldo, nome, emUso, tipo FROM conta WHERE id = ?";	
	private static final String SELECT_CONTAS = "SELECT id, saldo, nome, emUso, tipo FROM conta";	
	
	@Override
	public void save(Conta conta) throws Exception {
		PreparedStatement pStmt = this
				 				  .getConnection()
				 				  .prepareStatement(ContaDAOSQL.INSERT_CONTA);
		
		char tipo = '\0';
		
		if(conta instanceof Ativo)
			tipo = 'A';
		else if(conta instanceof Passivo)
			tipo = 'P';
		else if(conta instanceof PatrimonioLiquido)
			tipo = 'L';
 		
		pStmt.setInt(1, conta.getId());
		pStmt.setDouble(2, conta.getSaldo());
		pStmt.setString(3, conta.getNome());
		pStmt.setBoolean(4, conta.isEmUso());
		pStmt.setString(5, Character.toString(tipo));
		pStmt.executeUpdate();
	}

	@Override
	public Conta findByName(String nome) throws Exception {
		Conta conta = null;
		PreparedStatement pStmt = this
				  				  .getConnection()
				  				  .prepareStatement(ContaDAOSQL.SELECT_CONTA_BY_NAME);
		pStmt.setString(1, nome);
		ResultSet rSet = pStmt.executeQuery();
		conta = this.createConta(rSet);
		rSet.close();
		pStmt.close();
		if(conta==null)
			throw new ContaNaoEncontradaException(nome);
		return conta;
	}

	@Override
	public void update(Conta conta) throws Exception {
				PreparedStatement pStmt = this
				  .getConnection()
				  .prepareStatement(ContaDAOSQL.UPDATE_CONTA);
		pStmt.setDouble(1, conta.getSaldo());
		pStmt.setString(2, conta.getNome());
		pStmt.setBoolean(3, conta.isEmUso());
		pStmt.setInt(4, conta.getId());
		pStmt.executeUpdate();
	}

	@Override
	public Conta findById(int idValue) throws Exception {
		Conta conta = null;
		PreparedStatement pStmt = this
				  				  .getConnection()
				  				  .prepareStatement(ContaDAOSQL.SELECT_CONTA_BY_ID);
		pStmt.setInt(1, idValue);
		ResultSet rSet = pStmt.executeQuery();
		conta = this.createConta(rSet);
		rSet.close();
		pStmt.close();
		if(conta==null)
			throw new ContaNaoEncontradaException(Integer.toString(idValue));
		return conta;
	}

	private Conta createConta(ResultSet rSet) throws SQLException {
		Conta conta = null;
		if(rSet.next()){
			int id = rSet.getInt("id");
			double saldo = rSet.getDouble("saldo");
			String nomeConta = rSet.getString("nome");
			boolean emUso = rSet.getBoolean("emUso");
			char tipo = rSet.getString("tipo").charAt(0);
			if(tipo == 'A')
				conta = new Ativo(id, nomeConta, saldo, emUso);
			else if(tipo == 'P')
				conta = new Passivo(id, nomeConta, saldo, emUso);
			else if(tipo == 'L')
				conta = new PatrimonioLiquido(id, nomeConta, saldo, emUso);
		}
		return conta;
	}

	@Override
	public Collection<Conta> findAll() throws Exception {
		List<Conta> contas = new ArrayList<Conta>();
		Conta conta = null;
		PreparedStatement pStmt = this
				  				  .getConnection()
				  				  .prepareStatement(ContaDAOSQL.SELECT_CONTAS);
		ResultSet rSet = pStmt.executeQuery();
		
		while(rSet.next()){
			int id = rSet.getInt("id");
			double saldo = rSet.getDouble("saldo");
			String nomeConta = rSet.getString("nome");
			boolean emUso = rSet.getBoolean("emUso");
			char tipo = rSet.getString("tipo").charAt(0);
			if(tipo == 'A')
				conta = new Ativo(id, nomeConta, saldo, emUso);
			else if(tipo == 'P')
				conta = new Passivo(id, nomeConta, saldo, emUso);
			else if(tipo == 'L')
				conta = new PatrimonioLiquido(id, nomeConta, saldo, emUso);
			contas.add(conta);
		}
		rSet.close();
		pStmt.close();
		return contas;
	}

}
