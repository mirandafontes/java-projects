/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import daosql.ContaDAOSQL;
import interfaces.ContaDAO;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class ServerRMI {

    public static void main(String args[]) {


        try {
            ContaDAO stub = new ContaDAOSQL();
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.bind("contadaosql", stub);
            System.out.println("Stub conectado!");
        } catch (Exception ex) {
            Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
