package servidor;

import java.net.*;

public class Servidor {
	
	public static void main( String[] args ) {
		
		try{
			
			ServerSocket servidor = new ServerSocket( 1234 );
			
			System.out.println("Servidor no ar : " + servidor.getInetAddress().toString() + " Porta : " + servidor.getLocalPort() );
			
			while( true ) {
				
				Socket cliente = servidor.accept();
				
				System.out.println("Cliente chegou : " + cliente.getInetAddress().toString() );
				
				Thread t = new Thread( new DoStuffWith( cliente ) );
				
				t.start();
				
			}
			
		}
		
		catch( Exception e ) {
			
			e.printStackTrace();
			
		}
		
	}

}
