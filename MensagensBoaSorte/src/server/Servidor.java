package server;

import java.net.*;

public class Servidor {

	public static void main(String[] args) throws Exception {
		
		ServerSocket server = new ServerSocket( 1234 );
		
		System.out.println("Servidor no ar : " + server.toString() );
		
		try {
			
			Socket cliente;
			
			while( true ) {
				
				cliente = server.accept();
				
				System.out.println("\nCliente : " + cliente.getInetAddress().getHostAddress() );
				
				Thread t = new Thread( new Mensagens( cliente ) );
				
				t.start();
				
			}
			
			
			
		}
		
		catch ( Exception e ) {
			
			System.out.print( e );
			e.printStackTrace();
			server.close();
			
		}
		
	}

}
