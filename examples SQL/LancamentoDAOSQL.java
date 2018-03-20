package inf008.persistencia.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;

import inf008.contabil.entidades.Ativo;
import inf008.contabil.entidades.Conta;
import inf008.contabil.entidades.Lancamento;
import inf008.contabil.entidades.Passivo;
import inf008.contabil.entidades.PatrimonioLiquido;
import inf008.exception.ContaNaoEncontradaException;
import inf008.persistencia.LancamentoDAO;

public class LancamentoDAOSQL extends GenericDAOSQL implements LancamentoDAO {
	
	private static final String INSERT_LANCAMENTO = "INSERT INTO LANCAMENTO(ordem, descricao, valor, idcontadebito, idcontacredito) VALUES(?, ?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT ordem, descricao, valor, idcontadebito, idcontacredito FROM LANCAMENTO";

	
	
	@Override
	public void save(Lancamento lancamento) throws Exception {
		PreparedStatement pStmt = this
				  .getConnection()
				  .prepareStatement(LancamentoDAOSQL.INSERT_LANCAMENTO);

			pStmt.setInt(1, lancamento.getOrdem());
			pStmt.setString(2, lancamento.getDescricao());
			pStmt.setDouble(3, lancamento.getValor());
			pStmt.setInt(4, lancamento.getDebito().getId());
			pStmt.setInt(5, lancamento.getCredito().getId());
			pStmt.executeUpdate();

	}

	@Override
	public Collection<Lancamento> findAll() throws Exception {
		ContaDAOSQL contaDAO = new ContaDAOSQL();
		Collection<Lancamento> lancamentos = new LinkedList<Lancamento>();
		PreparedStatement pStmt = this
				  				  .getConnection()
				  				  .prepareStatement(LancamentoDAOSQL.SELECT_ALL);
		ResultSet rSet = pStmt.executeQuery();
		while(rSet.next()){
			Conta credito = contaDAO.findById(rSet.getInt("idcontacredito"));
			Conta debito = contaDAO.findById(rSet.getInt("idcontadebito"));			
			Lancamento lancamento = new Lancamento(rSet.getInt("ordem"), rSet.getString("descricao"), credito, debito, rSet.getDouble("valor"));
			lancamentos.add(lancamento);
		}
		rSet.close();
		pStmt.close();
		return lancamentos;
	}
	
    


}
