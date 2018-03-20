package servidor;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Servicos implements Runnable {
	
	private Socket cliente;
	
	private DataInputStream input;
	private DataOutputStream output;
	
	public Servicos( Socket cliente ) {
		
		this.cliente = cliente;
		
	}

	@Override
	public void run() {
		
		int op = 0;
		
		try {
			
			input = new DataInputStream( cliente.getInputStream() );
			op = input.readInt();
			
			System.out.println("Op Recebido! Op : " + op );
			
			output = new DataOutputStream( cliente.getOutputStream() );
			
		}
		
		catch( Exception e ) {
			
			e.printStackTrace();
			
		}
		
		switch( op ) {
		
			//Pilha Inteiros
			case 1 : {
				
				int tamStack = 0;
				
				//O Stack 'pares' será enviado novamente ao cliente pois os valores estao na base da pilha
				Stack<Integer> pares = new Stack<Integer>();
				Stack<Integer> impares = new Stack<Integer>();
				
				try {
					
					tamStack = input.readInt();
					
					for( int i = 0, aux = 0 ; i < tamStack ; i++ ) {
						
						aux = input.readInt();
						
						if( aux % 2 == 0 ) {
							
							pares.push( aux );
							
						}
						
						else {
							
							impares.push( aux );
							
						}
						
					}
					
					//Preenche a pilha que sera enviada com os impares no topo
					for( int i = 0, aux = 0 ; i < impares.size() ; i++ ) {
						
						aux = impares.pop();
						
						pares.push( aux );
						
					}
					
					//Envia a pilha
					output.write( toByteArray( pares ) );

					
				}
				
				catch ( IOException e ) {

					e.printStackTrace();
					
				}
				
				pares = null;
				impares = null;
				
				
				break;
				
			}
			
			//Remove Par
			case 2 : {
				
				int tamVet = 0;
				
				//Vetor a ser enviado
				ArrayList<Integer> paresRemovidos = new ArrayList<Integer>();
				
				try {
					
					tamVet = input.readInt();
										
					for( int i = 0, aux = 0 ; i < tamVet ; i++ ) {
						
						aux = input.readInt();
						
						if( aux % 2 != 0) {
							
							paresRemovidos.add( aux );
							
						}						

					}
					
					//Ordenacao do ArrayList<Integer>
					Collections.sort( paresRemovidos );
					
					//Envia tamanho do arrayList
					output.writeInt( paresRemovidos.size() );

					//Envia o arrayList
					output.write( toByteArray( paresRemovidos ) );
					
					
					paresRemovidos = null;
					
				}
				
				catch( Exception e ) {
					
					e.printStackTrace();
					
				}
				
				break;
				
			}
			
			//Conta impar
			case 3 : {
				
				int tamVet = 0;
				int numerosImpares = 0;
				
				try {
					
					tamVet = input.readInt();
										
					for( int i = 0, aux = 0 ; i < tamVet ; i++ ) {
						
						aux = input.readInt();
						
						if( aux % 2 != 0 && aux != 0) {
							
							numerosImpares++;
							
						}						

					}
					
					//Envia numero de impares
					output.writeInt( numerosImpares );
					
				}
					
				catch( Exception e ) {
						
					e.printStackTrace();
						
				}
				
				break;
				
			}
			
			//Produto Escalar
			case 4 : {
				
				int tamVetX = 0;
				int tamVetY = 0;
				int resultado = 0;
				
				try {
					
					//Vet X
					tamVetX = input.readInt();
					
					int[] vetX = new int[tamVetX];
					
					for( int i = 0 ; i < tamVetX ; i++ ) {
						
						vetX[i] = input.readInt();					

					}
					
					//Vet Y
					tamVetY = input.readInt();

					int[] vetY = new int[tamVetY];
					
					for( int i = 0 ; i < tamVetY ; i++ ) {
						
						vetY[i] = input.readInt();					

					}
					
					//tam tam vet1 vet2
					// 4   5  { 1, 2, 3, 4 } , { 1, 2, 3, 4, 5 }
					// 5   4  { 1, 2, 3, 4, 5 } , { 1, 2, 3, 4 }
					
					//Determina qual tam sera usado para o produto escalar
					if( tamVetX <= tamVetY ) {
						
						for( int i = 0 ; i < tamVetX ; i++ ) {
							
							resultado += vetX[i] * vetY[i]; 
							
						}
						
					}
					
					else {
						
						for( int i = 0 ; i < tamVetY ; i++ ) {
							
							resultado += vetX[i] * vetY[i]; 
							
						}
						
					}
					
					//Envia resultado
					output.writeInt( resultado );
					
				}
					
				catch( Exception e ) {
						
					e.printStackTrace();
						
				}
				
				break;
				
			}
		
		
		}
				


	}
	
	public static byte[] toByteArray( Stack<Integer> arg ) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		for( int i = 0 ; i < arg.size() ; i++ ) {
			   
		    dos.writeInt( arg.pop() );
		        
		}
		
		return baos.toByteArray();
		
	} 
	
	public static byte[] toByteArray( ArrayList<Integer> arg ) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		for( int i = 0 ; i < arg.size() ; i++ ) {
			   
		    dos.writeInt( arg.get( i ) );
		        
		}
		
		return baos.toByteArray();
		
	} 

}
