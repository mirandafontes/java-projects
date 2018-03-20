package client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Cliente {

	public static void main(String[] args) throws Exception {
		
		Socket local = new Socket( "127.0.0.1", 1234 );
		
		String sorte;
		
		Scanner read = new Scanner( local.getInputStream() );
		Scanner tcld = new Scanner( System.in );
		
		PrintWriter write = new PrintWriter( local.getOutputStream(), true );
		
		System.out.println("Cliente Rodando " + local.toString() );
		
		write.println( tcld.nextLine() );
		
		sorte = read.nextLine();
		
		System.out.println("Sua sorte foi : " + sorte );
		
		write.close();
		tcld.close();
		read.close();
		local.close();		

	}

}
