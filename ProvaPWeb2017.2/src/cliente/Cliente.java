package cliente;

import java.net.*;
import java.util.Stack;
import java.io.*;

public class Cliente {
	
	public static void main(String[] args) throws Exception, IOException {
		
		Socket cliente = new Socket( "127.0.0.1", 1234 );
		
		int op;
		
		op = 4;
		
		Stack<Integer> stackInteiros = new Stack<Integer>();
		Stack<Integer> stackResposta = new Stack<Integer>();
		
		int[] vetInteirosX = new int[10];
		int[] vetInteirosY = new int[9];

		DataInputStream input;
		DataOutputStream output;
		
		input = new DataInputStream( cliente.getInputStream() );
		output = new DataOutputStream( cliente.getOutputStream() );	
		
		switch( op ) {
		
			//Pilha Inteiros
			case 1 : {
				
				//Preenchimento
				for( int i = 0 ; i < 10 ; i++ ) {
					
					stackInteiros.push( i );
					
				}
				
				int tamStack = stackInteiros.size();
				
				//Op
				output.writeInt( op );
				
				//Tamanho do stack
				output.writeInt( stackInteiros.size() );
				
				//byte[]
				output.write( toByteArray( stackInteiros ) );
				
				//Leitura
				for( int i = 0 ; i < tamStack ; i++ ) {
					
					stackResposta.push( input.readInt() );
					
				}
				
				for( int i = 0 ; i < tamStack ; i++ ) {
					
					System.out.println("I = " + i + " ; Value : " + stackResposta.pop() );
					
				}
				
				break;
				
			}
			
			//Remove Par
			case 2 : {
				
				//Preenchimento
				for( int i = 0 ; i < vetInteirosX.length ; i++ ) {
					
					vetInteirosX[i] = i;
					
				}
				
				//Op
				output.writeInt( op );
				
				//Tamanho do vet
				output.writeInt( vetInteirosX.length );
				
				//byte[]
				output.write( toByteArray( vetInteirosX ) );

				
				//Leitura
				
				int tam = input.readInt();

				for( int i = 0 ; i < tam ; i++ ) {
					
					System.out.println("Vet[" + i +"] : " + input.readInt() );
					
				}
				
				break;
				
			}
			
			//Conta impar
			case 3 : {
				
				//Preenchimento
				for( int i = 0 ; i < vetInteirosX.length ; i++ ) {
					
					vetInteirosX[i] = i;
					
				}
				
				//Op
				output.writeInt( op );
				
				//Tamanho do vet
				output.writeInt( vetInteirosX.length );
				
				//byte[]
				output.write( toByteArray( vetInteirosX ) );
				
				//Leitura
				System.out.println("Numero de impares recebido : " + input.readInt() );
				
				break;
				
			}
			
			//Produto escalar
			case 4 : {
				
				//Preenchimento do vetor X
				for( int i = 0 ; i < vetInteirosX.length ; i++ ) {
					
					vetInteirosX[i] = i;
					
				}
				
				//Preenchimento do vetor Y
				for( int i = 0 ; i < vetInteirosY.length ; i++ ) {
					
					vetInteirosY[i] = i;
					
				}
				
				//Op
				output.writeInt( op );
				
				//Tamanho do vet X
				output.writeInt( vetInteirosX.length );
								
				//byte[] de vet X
				output.write( toByteArray( vetInteirosX ) );
				
				
				//Tamanho do vet Y
				output.writeInt( vetInteirosY.length );
				
				//byte[] de vet Y
				output.write( toByteArray( vetInteirosY ) );
				
				//Leitura
				System.out.println("Produto escalar : " + input.readInt() );
				
				break;
				
			}
			
			default : {
				
				System.out.println("Opcao invalida!");
				
			}
		
		}
		
		cliente.close();
		
		
	}
	
	public static byte[] toByteArray( Stack<Integer> arg ) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		for( int i = 0 ; i < arg.size() ; i++ ) {
			   
		    dos.writeInt( arg.pop() );
		        
		}
		
		return baos.toByteArray();
		
	} 
	
	public static byte[] toByteArray( int[] arg ) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		for( int i = 0 ; i < arg.length ; i++ ) {
			   
		    dos.writeInt( arg[i] );
		        
		}
		
		return baos.toByteArray();
		
	} 

}
