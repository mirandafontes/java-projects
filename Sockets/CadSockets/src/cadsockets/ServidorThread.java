/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadsockets;

import daosql.ContaDAOSQL;
import java.io.DataInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class ServidorThread implements Runnable {

    private Socket cliente;

    private int idConta;
    private float valor;
    
    private boolean canRun = true;

    private ContaDAOSQL contaDAO = new ContaDAOSQL();

    Conta a1 = new Conta();

    public ServidorThread(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {

        DataInputStream inVar = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            inVar = new DataInputStream(cliente.getInputStream());
            out = new ObjectOutputStream(cliente.getOutputStream());
            in = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            System.out.println("Aqui!");
            Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        do {

            byte op = 0;

            try {
                op = inVar.readByte();
            } catch (IOException ex) {
                Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            System.out.println(op);

            switch (op) {
                case 1: {
                    try {
                        a1 = (Conta) in.readObject();
                        this.criarConta(a1);
                        out.writeChars("Conta criada com sucesso.");
                        out.flush();
                    } catch (Exception ex) {
                        System.out.println("Op 1");
                        Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //inVar.close();
                    //cliente.close();
                    break;
                }

                case 2: {
                    try {
                        idConta = (Integer) in.readObject();
                        out.writeObject(this.verificarSaldo(idConta));
                        out.flush();
                    } catch (Exception ex) {
                        System.out.println("Op 2");
                        Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                //inVar.close();
                //cliente.close();
                //out.flush();
                case 3: {
                    try {
                        idConta = (Integer) in.readObject();
                        valor = (Float) in.readObject();
                        out.writeObject(this.sacarValor(idConta, valor));
                        out.flush();
                    } catch (Exception ex) {
                        System.out.println("Op 3");
                        Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                //inVar.close();
                //cliente.close();
                //out.flush();
                case 4: {
                    try {
                        idConta = (Integer) in.readObject();
                        valor = (Float) in.readObject();
                        out.writeObject(this.depositarValor(idConta, valor));
                        out.flush();
                    } catch (Exception ex) {
                        System.out.println("Op 4");
                        Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                case 0:
                    canRun = false;
                    return;
            }

        } while (canRun);
        
        try {
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    
        public static final int PORT = 7070;

    int idConta;
    float valor;

    private ContaDAOSQL contaDAO = new ContaDAOSQL();

    Conta a1 = new Conta();

    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        new Servidor().run();
    }

    public void run() throws IOException, ClassNotFoundException, Exception {
        ServerSocket srv = new ServerSocket(PORT);
        System.out.println("Servidor Conectado");

        while (true) {
            Socket cliente = srv.accept();

            DataInputStream inVar = new DataInputStream(cliente.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

            byte op = inVar.readByte();

            System.out.println(op);

            switch (op) {
                case 1:
                    a1 = (Conta) in.readObject();
                    this.criarConta(a1);

                    out.writeChars("Conta criada com sucesso.");
                    out.flush();

                    //inVar.close();
                    //cliente.close();

                    break;
                case 2:
                    idConta = (Integer)in.readObject();

                    out.writeObject(this.verificarSaldo(idConta));

                    out.flush();

                    //inVar.close();
                    //cliente.close();

                    //out.flush();

                    break;
                case 3:
                    idConta = (Integer)in.readObject();
                    valor =(Float)in.readObject();

                    out.writeObject(this.sacarValor(idConta, valor));
                    out.flush();

                    //inVar.close();
                    //cliente.close();

                    //out.flush();

                    break;

                case 4:
                    idConta = (Integer)in.readObject();
                    valor =(Float)in.readObject();

                    out.writeObject(this.depositarValor(idConta, valor));
                    out.flush();

                    //inVar.close();
                    //cliente.close();

                    //out.flush();

                    break;
            }

        }
    }

    public void criarConta(Conta conta) throws Exception {
        contaDAO.criarConta(conta);
    }

    public Conta verificarSaldo(int idConta) throws Exception {
        Conta conta = contaDAO.verificarSaldo(idConta);
        return conta;
    }

    public Conta sacarValor(int id, float valor) throws Exception {
        return contaDAO.sacarValor(id, valor);
    }

    public Conta depositarValor(int id, float valor) throws Exception {
        return contaDAO.depositarValor(id, valor);
    }*/
    public void criarConta(Conta conta) throws Exception {
        contaDAO.criarConta(conta);
    }

    public Conta verificarSaldo(int idConta) throws Exception {
        Conta conta = contaDAO.verificarSaldo(idConta);
        return conta;
    }

    public Conta sacarValor(int id, float valor) throws Exception {
        return contaDAO.sacarValor(id, valor);
    }

    public Conta depositarValor(int id, float valor) throws Exception {
        return contaDAO.depositarValor(id, valor);
    }

}
