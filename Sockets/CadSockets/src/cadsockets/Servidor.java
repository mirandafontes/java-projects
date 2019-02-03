package cadsockets;

import daosql.ContaDAOSQL;
import java.io.DataInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class Servidor {

    public static void main(String[] args) {

        try {

            ServerSocket servidor = new ServerSocket(7070);

            System.out.println("Servidor no ar : localhost ;" + " Porta : " + servidor.getLocalPort());

            while (true) {

                Socket cliente = servidor.accept();

                System.out.println("Cliente chegou : " + cliente.getInetAddress().toString());

                Thread t = new Thread(new ServidorThread(cliente));

                t.start();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
