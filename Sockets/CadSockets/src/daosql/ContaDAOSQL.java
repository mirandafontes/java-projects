/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cadsockets.Conta;
import interfaces.GenericDAOSQL;
import java.io.Serializable;
import java.sql.SQLException;
import interfaces.ContaDAO;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class ContaDAOSQL extends GenericDAOSQL implements ContaDAO, Serializable {

    private static final String SALVAR_CONTA = "INSERT INTO contas(id,saldo) VALUES (?,?)";
    private static final String SELECIONAR_CONTA = "SELECT id, saldo FROM contas WHERE id = ?";
    private static final String UPDATE_CONTA = "UPDATE contas SET saldo = ? WHERE id = ?";

    @Override
    public void criarConta(Conta conta) throws Exception {
        PreparedStatement pStmt = this.getConnection().prepareStatement(ContaDAOSQL.SALVAR_CONTA);

        pStmt.setInt(1, conta.getId());
        pStmt.setFloat(2, conta.getSaldo());

        pStmt.executeUpdate();
    }

    @Override
    public Conta verificarSaldo(int id) throws Exception {
        Conta retornada = null;

        PreparedStatement pStmt = this.getConnection().prepareStatement(ContaDAOSQL.SELECIONAR_CONTA);

        pStmt.setInt(1, id);

        ResultSet rSet = pStmt.executeQuery();

        retornada = createConta(rSet); //Recupera a conta

        rSet.close();
        pStmt.close();

        if (retornada == null) {
            throw new Exception("ID invalido");
        }

        return retornada;
    }

    @Override
    public Conta depositarValor(int id, float valor) throws Exception {
        Conta retornada = null;

        PreparedStatement pStmt = this.getConnection().prepareStatement(ContaDAOSQL.SELECIONAR_CONTA);

        pStmt.setInt(1, id);

        ResultSet rSet = pStmt.executeQuery();

        retornada = createConta(rSet); //Recupera a conta

        if (retornada == null) {
            throw new Exception("ID invalido");
        }

        retornada.depositar(valor); //Deposita valor na conta

        updateConta(retornada); //Atualiza no banco

        return retornada;

    }

    @Override
    public Conta sacarValor(int id, float valor) throws Exception {
        Conta retornada = null;

        PreparedStatement pStmt = this.getConnection().prepareStatement(ContaDAOSQL.SELECIONAR_CONTA);

        pStmt.setInt(1, id);

        ResultSet rSet = pStmt.executeQuery();

        retornada = createConta(rSet); //Recupera a conta

        if (retornada == null) {
            throw new Exception("ID invalido");
        }

        retornada.sacar(valor); //Saca valor da conta

        updateConta(retornada); //Update da conta

        return retornada;

    }

    private void updateConta(Conta conta) throws SQLException {

        //UPDATE conta SET saldo = ? WHERE id = ?
        PreparedStatement pStmt = this.getConnection().prepareStatement(ContaDAOSQL.UPDATE_CONTA);

        pStmt.setFloat(1, conta.getSaldo());
        pStmt.setInt(2, conta.getId());

        pStmt.executeUpdate();

    }

    private Conta createConta(ResultSet rSet) throws SQLException {

        Conta temp = null;

        if (rSet.next()) {

            int id = rSet.getInt(1);
            float saldo = rSet.getFloat(2);

            temp = new Conta(id);
            temp.setSaldo(saldo);
        }
        return temp;
    }

}
