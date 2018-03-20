package server;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Mensagens implements Runnable {

	private Socket socket;
	private String[] strings = new String[3];
	
	
	public Mensagens ( Socket sckt ) {
		
		this.socket = sckt;
		
		this.strings[0] = "Batata eh o alimento do dia";
		this.strings[1] = "Evite agua";
		this.strings[2] = "Meuparceiro";
		
	}
	
	
	@Override
	public void run() {
		
		try {
			
			PrintWriter print = new PrintWriter( this.socket.getOutputStream() , true );
			
			print.println( this.boaSorte() );			
			
		}
		
		catch( Exception e ) {
			
			e.printStackTrace();
			
		}	

	}
	
	
	private String boaSorte() {
		
		Random rnd = new Random();
		
		return this.strings[ rnd.nextInt( 3 ) ];
		
	}
	

}
