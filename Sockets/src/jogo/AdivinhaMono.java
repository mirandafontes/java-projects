package jogo;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

import java.util.Random;

public class AdivinhaMono implements Runnable {
	
	private Socket cliente;
	
	private int numeroSorteado;
	private int tentativas;
	
	private final int MAX_TENTATIVAS = 5;
	
	public AdivinhaMono( Socket cliente ) {
		
		this.setCliente(cliente);
		this.setTentativas( 0 );
		this.sortearNumero();
		
	}

	@Override
	public void run() {

		this.jogo();
		
	}
	
	private void setCliente( Socket cliente ) {
		
		this.cliente = cliente;
		
	}
	
	private void sortearNumero() {
		
		Random r = new Random();
		
		this.numeroSorteado = r.nextInt( 101 );
		
	}

	private void setTentativas( int tentativas ) {
		
		this.tentativas = tentativas;
		
	}

	public void jogo() {
		
		DataInputStream input;
		DataOutputStream output;
		
		int numeroLido = -1;
		
		try {
			
			input = new DataInputStream( cliente.getInputStream() );
			output = new DataOutputStream( cliente.getOutputStream() );
		
			while ( ( this.tentativas != this.MAX_TENTATIVAS ) || ( numeroLido != this.numeroSorteado ) ) {
				
				numeroLido = input.readInt();
				
				output.writeChars( this.regrasJogo( numeroLido) );
			
			}
			
			input.close();
			output.close();
			
			cliente.close();
		
		}
		
		catch( Exception e ) {
			
			e.printStackTrace();
			System.out.println("Problema ao capturar as Stream Input/Output");
			
		}
	
	}
	
	
	private String regrasJogo( int numeroLido ) {
		
		if( this.tentativas == MAX_TENTATIVAS ) {
			
			return "GameOver\n";
			
		}
		
		if( numeroLido > this.numeroSorteado ) {
			
			tentativas++;
			
			return "Maior. Tentativas :" + this.tentativas + "\n" ;
			
		}
		
		else if( numeroLido < this.numeroSorteado ) {
			
			tentativas++;
			
			return "Menor. Tentativas :" + this.tentativas + "\n";
			
		}
//		tentativas = MAX_TENTATIVAS; // Para encerrar
		return "Venceu\n";
		
		
	}
	

}
