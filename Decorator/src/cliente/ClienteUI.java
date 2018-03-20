package cliente;

import abstratos.*;
import concretos.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.*;

public class ClienteUI {
	
	private static JFrame janela = new JFrame("IBolo");
	
	private static JComboBox<String> decoradores = new JComboBox<String>();
	private static JComboBox<String> selecionados = new JComboBox<String>();
	
	private static JButton inserir = new JButton( "inserir" );
	private static JButton remover = new JButton( "remover" );
	private static JButton bake = new JButton("BAKE!");
	
	private static JPanel painel = new JPanel();
	
	private static URLClassLoader ulc;
	
	private static String[] plugins;
	
    public static void main(String[] args) throws Exception {
    	    	
    	init();
    	
    	addItemDecoradores();
	
    }
    
    private static void init() throws Exception {
    	
    	BorderLayout manager = new BorderLayout();

    	manager.layoutContainer( painel );
    	
    	//JPanel init
    	painel.setLayout( manager );
    	
    	//JButtons init
    	inserir.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				selecionados.addItem( ( String )  decoradores.getSelectedItem() );
				
			}
		} );
    	
    	remover.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				if( selecionados.getSelectedIndex() != -1 ) {
					
					selecionados.removeItemAt( selecionados.getSelectedIndex() );
	
				}
				
			}
		} );
    	
    	bake.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				//TODO
				
				if( selecionados.getItemCount() > 0 ) {
				
					try {
						
						createDecorators();
						
					}
					
					catch ( Exception e1 ) {

						e1.printStackTrace();
	
					}
					
				}
				
			}
		} );

    	//JFrame Init
    	janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    	janela.setSize( 350, 200 );
    	janela.setVisible( true );
    	
    	//JComboBox to JPanel
    	painel.add( decoradores, BorderLayout.PAGE_START );	
    	painel.add( selecionados, BorderLayout.CENTER );
    	
    	//JButton to JPanel
    	painel.add( inserir, BorderLayout.WEST );
    	painel.add( remover, BorderLayout.EAST );
    	painel.add( bake, BorderLayout.PAGE_END );
    	
    	manager.layoutContainer( painel );

    	//JPanel to JFrame
    	janela.add( painel );

    	
    }
    
    //Prepara o URLClassLoader e a lista de plugins!
    private static void addItemDecoradores() throws Exception {
    	
    	File currentDir = new File("./plugins");
    	
    	plugins = currentDir.list();
    	
    	URL[] jars = new URL[plugins.length];
    	
    	for( int i = 0 ; i < plugins.length ; i++ ) {
    		
    		decoradores.addItem( plugins[i].split("\\.")[0] );
    		
    		jars[i] = ( new File("./plugins/" + plugins[i] ) ).toURI().toURL();
    		
    	}
    	
    	ulc = new URLClassLoader( jars );

    }
    
    private static void createDecorators() throws Exception {
    	
    	int i;
    	
    	BoloSimples bolo = new BoloSimples();
    	
    	Object[] decoradores = new Object[selecionados.getItemCount()];
    	    	   	
    	String itemAtual = "";
    	
    	//Roda a lista de selecionados
    	for( i = 0 ; i < selecionados.getItemCount() ; i++ ) {
    		
    		//O item atual selecionado
    		itemAtual = selecionados.getItemAt( i );
    		
    		//Instancia esse item e joga no array de Object
    		decoradores[i] = ( Object ) Class.forName( "concretos." + itemAtual , true, ulc ).newInstance();
    		
    		//Se for o primeiro Object, seta como primeiro Decorated o BoloSimples
    		if( i == 0) {
	
    			( ( Decorator ) decoradores[i]).setDecorated( bolo );
    		
    		}
    		
    		//Caso contrario, seta sempre o Decorator anterior
    		else {	
    			
    			( ( Decorator ) decoradores[i]).setDecorated( ( Decorator ) decoradores[i - 1] );
    			
    		}
    		

    		
    	}
    	
    	//Assa todos a partir do ultimo
    	( ( Decorator ) decoradores[i - 1]).assar();
    	
    	selecionados.removeAllItems();
    	selecionados.revalidate();
    	selecionados.repaint();
    	
    	janela.revalidate();
    	janela.repaint();

    }

}
