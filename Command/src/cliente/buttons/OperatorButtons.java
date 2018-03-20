package cliente.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class OperatorButtons extends JButton {
	
	public static JTextField textField;
	
	public OperatorButtons ( String text ) {
		
		super( text );
		
		this.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				textField.setText( text );
				
			}
		} );
	
	}

}
