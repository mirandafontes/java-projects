package cliente;

import java.net.*;
import java.io.*;

public class Cliente {
	


	public static void main(String[] args) throws Exception, IOException {

		Socket cliente = new Socket( "127.0.0.1", 1234 );
		
		String inversa = "A1E5T7W8G";
		String resposta = "";
		
		DataInputStream input;
		DataOutputStream output;
		
		
		input = new DataInputStream( cliente.getInputStream() );
		output = new DataOutputStream( cliente.getOutputStream() );	
		
		System.out.println( "( Cliente ) Enviar : " + inversa );
		
		output.writeInt( inversa.length() );
		output.writeChars( inversa );
		
		int tam = input.readInt();
		
		for( int i = 0 ; i < tam ; i++ ) {
			
			resposta += input.readChar();
			
		}
		
		System.out.println( resposta );
		
		
	}

}
