package blok.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainPluginUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	private static MainPluginUI SINGLETON = null;
	
	private String selectedSkin;
	
	JButton btnConfirmar = new JButton("Confirmar");
	
	JComboBox<String> jComboBoxSkins = new JComboBox<String>();
	
	public static MainPluginUI getInstance() {
		
		if( SINGLETON == null ) {
			
			SINGLETON = new MainPluginUI();
			
		}
		
		return SINGLETON;
		
	}
	
	private MainPluginUI() {
		
		this.initComponents();
		//this.addComponentsSkins();
			
		
	}

	private void initComponents() {

		setBounds( 100, 100, 450, 300 );
		setAlwaysOnTop( true );
		setVisible( true );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		jComboBoxSkins.setToolTipText("Selecione uma skin para o jogo.");
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		getContentPane().add( jComboBoxSkins );
		
		this.addComponentsSkins( jComboBoxSkins );		
		
		btnConfirmar.setToolTipText( "Aperte aqui para confirmar a seleção da skin." );
		getContentPane().add( btnConfirmar, BorderLayout.SOUTH );
		
	}
        

	
    private void addComponentsSkins( JComboBox<String> jComboBoxSkins ) {
    	
    	File currentDir = new File("plugins/");
    	String[] skins = currentDir.list();
    	
    	for( String s : skins ) {
    		
    		jComboBoxSkins.addItem( s.split("Skin.jar")[0] );
    		
    	}
    		
    }
    
    
    public String getSelectedSkin() {

    	btnConfirmar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JButton arg = ( JButton ) arg0.getSource();

				if ( btnConfirmar.getModel().isPressed() == arg.getModel().isPressed() ) {
					
					selectedSkin = ( String ) jComboBoxSkins.getSelectedItem();
										
				}
				
			}
		});
    	
    	
    	return selectedSkin;        
    	
    }

}
