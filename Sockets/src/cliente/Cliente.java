package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Scanner;

public class Cliente {
	
	private static DataInputStream input = null;
	
	private static DataOutputStream output = null;
	
	private static Scanner teclado = new Scanner( System.in );
	
	private static String respostaServidor = "";
	
	private static int numeroSelecionado;
	
	public static void main( String[] args ) throws UnknownHostException, IOException {
				
		Socket cliente = new Socket("127.0.0.1", 1000 );
		
		input = new DataInputStream( cliente.getInputStream() );
		
		output = new DataOutputStream( cliente.getOutputStream() );
		
		System.out.println("Bem vindo ao jogo de adivinhacao de numero!");
			
		while( ( respostaServidor.equals(" G a m e O v e r ") == false ) || ( respostaServidor.equals(" V e n c e u ") == false ) ) {
			
//			if(( respostaServidor.equals(" V e n c e u "))== false)
				System.out.println("\nDigite um numero!");
			
			numeroSelecionado = teclado.nextInt();
			
			output.writeInt( numeroSelecionado );
			
			//TODO
			respostaServidor = input.readLine();
			
			System.out.println( respostaServidor );
			
			
		}
		
		input.close();
		output.close();
		
		cliente.close();
		teclado.close();
		
		
	}

}
