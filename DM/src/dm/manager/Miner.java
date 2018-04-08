package dm.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Miner {
	
	private File pathToWrite;
	
	private File read;
	
	private int rowCount;
	
	private int columnCount;
	
	public Miner( File pathToWrite, File read, int rowCount, int columnCount ) {
		
		this.pathToWrite = pathToWrite;
		this.read = read;
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		
	}
	
	public void mining() {
		
		boolean[][] data = new boolean[rowCount][columnCount];
		
		String[] items = new String[columnCount];
		
		FileWriter fw;
		PrintWriter pw;
		
		FileReader fr;
		BufferedReader br;
		
		JOptionPane.showMessageDialog(null, "Mining! Please, click OK.", "Mining process", JOptionPane.INFORMATION_MESSAGE);
		
		try {
	
			fw = new FileWriter(pathToWrite);
			pw = new PrintWriter(fw);
			
			fr = new FileReader(read);
			br = new BufferedReader(fr);

			
			pw.println("Numero total de registros : " + (rowCount * columnCount) );
			pw.println("\n\n");
				
			String aux = "";
			
			String[] arrayAux;
			
			int[] qtdItem = new int[columnCount];
			
			for( int i = 0 ; i < columnCount ; i++ ) {
				
				qtdItem[i] = 0;
				
			}
			
			if( (aux = br.readLine() ) != null ) {
				
				items = aux.split(",");
				
			}
		
			for( int i = 0 ; i < rowCount && aux != null ; i++ ) {
				
				aux = br.readLine();
				
				arrayAux = aux.split(",");
				
							
				for( int j = 0 ; j < columnCount && aux != null ; j++ ) {
					
					if( arrayAux[j].equals("0") ) {
						
						data[i][j] = false;
						
					}
					
					else {
					
						data[i][j] = true;
						
						qtdItem[j]++;
					
					}
					
				}
				
			}
			
			pw.println("\nQuantidade de itens por item :" );
			pw.println("\n\n");
			
			for( int i = 0 ; i < columnCount ; i++ ) {
				
				pw.println( items[i] + " : " + qtdItem[i] );
				
			}
			
			pw.println("\n\n");
			pw.println("\nSuportes :");
			pw.println("\n\n");
		
			for( int i = 0; i < columnCount ; i++  ) {
				
				double dividend =  qtdItem[i];
				
				pw.println( "S( " + items[i] + " ) = " + dividend / rowCount );
				
			}
			
			pw.println("\n\n");
			
			ArrayList<Integer> compndAmnt = new ArrayList<Integer>();
			
			for( int  j = 0, x = 0, qtd = 0 ; j < columnCount ; j++ ) {
				
				x = j + 1;
				
				for( int i = 0 ;  ; i++ ) {
					
					if( i == rowCount ) {
						
						compndAmnt.add(qtd);
						
						double dividend = qtd;
						
						pw.println( "S( " + items[j] + " , " + items[x] + " ) = " + dividend / rowCount  );
						
						i = 0;
						x++;
						
						qtd = 0;
						
					}
					
					if( x == columnCount ) {
						
						break;
						
					}
					
					if( ( data[i][j] == true ) && ( data[i][x] == true ) ) {
						
						qtd++;
						
					}
					
					
				}
				
			}
			
			pw.println("\n\n");
			pw.println("\nConfianca :");
			pw.println("\n\n");
			
			for( int  j = 0, x = 0, currentComSup = 0 ; j < columnCount ; j++ ) {
				
				x = j + 1;
				
				for( int i = 0 ;  ; i++ ) {
					
					if( i == rowCount ) {
						
						double dividend = compndAmnt.get(currentComSup); //compndAmnt[currentComSup]
						
						currentComSup++;
						
						pw.println( "C( " + items[j] + " , " + items[x] + " ) = " + dividend / qtdItem[j]  );
						
						i = 0;
						x++;

						
					}
					
					if( x == columnCount ) {
						
						break;
						
					}
					
					
				}
				
			}
			
			for( int  j = 0, x = 0, currentComSup = 0 ; j < columnCount ; j++ ) {
				
				x = j + 1;
				
				for( int i = 0 ;  ; i++ ) {
					
					if( i == rowCount ) {
						
						double dividend = compndAmnt.get(currentComSup);
						
						currentComSup++;
						
						pw.println( "C( " + items[x] + " , " + items[j] + " ) = " + dividend / qtdItem[x]  );
						
						i = 0;
						x++;

						
					}
					
					if( x == columnCount ) {
						
						break;
						
					}
					
					
				}
				
			}
			
			JOptionPane.showMessageDialog(null, "Mining complete! This program will close.", "Mining process", JOptionPane.INFORMATION_MESSAGE);	
			
			System.gc();
			
			fw.close();
			
		}
		
		catch( Exception e ) {
			
			e.printStackTrace();
			
		}
		
	}

}
