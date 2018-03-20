package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Stack;

public class DoStuffWith implements Runnable {
	
	private Socket cliente;
	
	private DataInputStream input;
	private DataOutputStream output;
	
	public DoStuffWith( Socket cliente ) {
		
		this.cliente = cliente;
		
	}

	@Override
	public void run() {
		
		try {
			
			input = new DataInputStream( cliente.getInputStream() );
			output = new DataOutputStream( cliente.getOutputStream() );
			
		}
		
		catch( Exception e ) {
			
			e.printStackTrace();
			
		}
		
		String enviar = "";
		
		char aux;
	
		Stack<Character> ints = new Stack<Character>();

		
		try {
			
			int tam = input.readInt();
			
			System.out.println( "Tam do Input : " + tam );
			
			for( int i = 0 ; i < tam ; i++ ) {
				
				aux = input.readChar();
				
				System.out.println( "I : " + i + " Aux : " + aux );
				
				if( aux >= '0' && aux <= '9' ) {
					
					ints.add( aux );
					
				}
				
				else {
					
					enviar += aux;
					
				}
					
			}
			
			for( Character c : ints ) {
				
				enviar += c;
				
			}
			
			output.writeInt( enviar.length() );
			output.writeChars( enviar );
			
		}
		
		catch (IOException e) {

			e.printStackTrace();
			
		}	
		


	}

}
