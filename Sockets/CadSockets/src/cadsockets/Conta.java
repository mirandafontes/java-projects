package cadsockets;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class Conta implements Serializable {

    private int id;
    private float saldo;

    private static final long serialVersionUID = 1L;

    public Conta(int idConta) {
        this.id = idConta;
        this.saldo = 0f;
    }

    public Conta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int idConta) {
        this.id = idConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void sacar(float valor) {
        saldo -= valor;
    }

    public void depositar(float valor) {
        saldo += valor;
    }

    @Override
    public String toString() {
        return "Conta: " + id + "\tSaldo: " + saldo;
    }

}
