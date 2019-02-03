/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import cadrmi.Conta;
import daosql.ContaDAOSQL;
import interfaces.ContaDAO;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class Client {

    public static void main(String args[]) throws Exception {

        Registry registry;

        int op;
        int idConta;
        float valor;
        Conta conta;
        Scanner ler;
        ContaDAO stub = null;

        op = 0;
        idConta = 0;
        valor = 0f;
        conta = new Conta();
        ler = new Scanner(System.in);

        registry = LocateRegistry.getRegistry(5000);

        try {
            stub = (ContaDAO) registry.lookup("contadaosql");
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        do {

            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Criar nova conta.");
            System.out.println("2 - Visualizar saldo.");
            System.out.println("3 - Sacar da conta.");
            System.out.println("4 - Depositar na conta.");
            System.out.println("0 - Sair.");

            op = ler.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite ID da conta a ser criada");
                    ler.nextLine();

                    System.out.print(">");
                    conta.setId(Integer.parseInt(ler.nextLine()));

                    System.out.println(stub.criarConta(conta));

                    break;

                case 2:
                    System.out.print("Digite o ID da conta para verificação do saldo:");
                    ler.nextLine();

                    System.out.print(">");
                    idConta = Integer.parseInt(ler.nextLine());

                    System.out.println(stub.verificarSaldo(idConta));

                    break;

                case 3:
                    System.out.println("Digite o ID da conta para o saque:");
                    ler.nextLine();

                    System.out.print(">");
                    idConta = Integer.parseInt(ler.nextLine());

                    System.out.println("Digite valor a ser saque:");
                    ler.nextLine();

                    System.out.print(">");
                    valor = Float.parseFloat(ler.nextLine());

                    System.out.println(stub.sacarValor(idConta, valor));

                    break;

                case 4:
                    System.out.println("Digite o ID da conta para o deposito:");
                    ler.nextLine();

                    System.out.print(">");
                    idConta = Integer.parseInt(ler.nextLine());

                    System.out.println("Digite valor a ser deposito:");
                    ler.nextLine();

                    System.out.print(">");
                    valor = Float.parseFloat(ler.nextLine());

                    System.out.println(stub.depositarValor(idConta, valor));

                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!!!!");
            }

        } while (op != 0);

    }
}
