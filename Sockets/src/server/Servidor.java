package server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import jogo.AdivinhaMono;

public class Servidor {
	
	public static void main( String[] args ) throws IOException {
			
		ServerSocket servidor = new ServerSocket( 1000 );
		
		System.out.println("Servidor rodando!");
		
		while( true ) {
			
			Socket cliente = servidor.accept();
			
			Thread t = new Thread( new AdivinhaMono( cliente ) );
			
			t.run();
			
		}
		
		
	}

}
