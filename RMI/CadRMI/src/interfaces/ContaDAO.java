/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import cadrmi.Conta;
import java.rmi.*;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public interface ContaDAO extends Remote {

    public Conta criarConta(Conta conta) throws Exception;

    public Conta verificarSaldo(int id) throws Exception;

    public Conta sacarValor(int id, float valor) throws Exception;

    public Conta depositarValor(int id, float valor) throws Exception;

}
