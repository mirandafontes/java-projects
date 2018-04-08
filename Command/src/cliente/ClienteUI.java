package cliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

import cliente.buttons.*;
import commands.*;
import invoker.*;
import receiver.*;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ClienteUI {

	private JFrame mainFrame;
	
	private JTextField mainTextField;
	
	private NumberButtons button_0;
	private NumberButtons button_1;
	private NumberButtons button_2;
	private NumberButtons button_4;
	private NumberButtons button_3;
	private NumberButtons button_5;
	private NumberButtons button_6;
	private NumberButtons button_7;
	private NumberButtons button_8;
	private NumberButtons button_9;
	private OperatorButtons button_10;// /
	private OperatorButtons button_11;// *
	private OperatorButtons button_12;// +
	private OperatorButtons button_13;// -
	
	private JButton btnUndo;
	private JButton btnEnter;
	private JButton btnRedo;	
	
	private JTextField factorTextField;
	private JTextField operatorTextField;
	
	private Calculadora calculator;
	
	private Invoker caller;
	
	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {
		
		EventQueue.invokeLater( new Runnable() {
			
			public void run() {
				
				try {
					
					ClienteUI window = new ClienteUI();
					
					window.mainFrame.setVisible( true );
					
				} catch ( Exception e ) {
					
					e.printStackTrace();
					
				}
			}
		} );
	}

	/**
	 * Create the application.
	 */
	public ClienteUI() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		calculator = new Calculadora();
		
		caller = new Invoker();
		
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 220, 270);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
		flowLayout.setAlignOnBaseline(true);
		mainFrame.getContentPane().setLayout(flowLayout);
		
		mainTextField = new JTextField();
		mainTextField.setColumns(15);
		mainTextField.setEditable(false);
		mainTextField.setText("0");
		mainTextField.setHorizontalAlignment(SwingConstants.TRAILING);
		mainFrame.getContentPane().add(mainTextField);
		
		factorTextField = new JTextField();
		factorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		factorTextField.setEditable(false);
		factorTextField.setText("0");
		mainFrame.getContentPane().add(factorTextField);
		factorTextField.setColumns(1);
		
		operatorTextField = new JTextField();
		operatorTextField.setText("+");
		operatorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		operatorTextField.setEditable(false);
		mainFrame.getContentPane().add(operatorTextField);
		operatorTextField.setColumns(1);
		
		JPanel numberPanel = new JPanel();
		mainFrame.getContentPane().add(numberPanel);
		GridBagLayout gbl_numberPanel = new GridBagLayout();
		gbl_numberPanel.rowHeights = new int[] {5};
		gbl_numberPanel.columnWidths = new int[] {5};
		gbl_numberPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_numberPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		numberPanel.setLayout(gbl_numberPanel);
		
		NumberButtons.textField = factorTextField;
		OperatorButtons.textField = operatorTextField;
		
		button_10 = new OperatorButtons("/");
		GridBagConstraints gbc_button_10 = new GridBagConstraints();
		gbc_button_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_10.insets = new Insets(0, 0, 5, 5);
		gbc_button_10.gridx = 0;
		gbc_button_10.gridy = 1;
		numberPanel.add(button_10, gbc_button_10);
		
		button_11 = new OperatorButtons("*");
		GridBagConstraints gbc_button_11 = new GridBagConstraints();
		gbc_button_11.insets = new Insets(0, 0, 5, 5);
		gbc_button_11.gridx = 1;
		gbc_button_11.gridy = 1;
		numberPanel.add(button_11, gbc_button_11);
		
		button_12 = new OperatorButtons("+");
		GridBagConstraints gbc_button_12 = new GridBagConstraints();
		gbc_button_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_12.insets = new Insets(0, 0, 5, 5);
		gbc_button_12.gridx = 2;
		gbc_button_12.gridy = 1;
		numberPanel.add(button_12, gbc_button_12);
		
		button_13 = new OperatorButtons("-");
		GridBagConstraints gbc_button_13 = new GridBagConstraints();
		gbc_button_13.anchor = GridBagConstraints.WEST;
		gbc_button_13.insets = new Insets(0, 0, 5, 0);
		gbc_button_13.gridx = 3;
		gbc_button_13.gridy = 1;
		numberPanel.add(button_13, gbc_button_13);
		
		button_7 = new NumberButtons("7");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_7.insets = new Insets(0, 0, 5, 5);
		gbc_button_7.gridx = 0;
		gbc_button_7.gridy = 2;
		numberPanel.add(button_7, gbc_button_7);
		
		button_8 = new NumberButtons("8");
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.insets = new Insets(0, 0, 5, 5);
		gbc_button_8.gridx = 1;
		gbc_button_8.gridy = 2;
		numberPanel.add(button_8, gbc_button_8);
		
		button_9 = new NumberButtons("9");
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_9.insets = new Insets(0, 0, 5, 5);
		gbc_button_9.gridx = 2;
		gbc_button_9.gridy = 2;
		numberPanel.add(button_9, gbc_button_9);
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed ( ActionEvent e ) {
				
				caller.unexecute();
				
				mainTextField.setText( Double.toString( calculator.getTotal() )  );
				
			}
		});
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.fill = GridBagConstraints.VERTICAL;
		gbc_btnUndo.anchor = GridBagConstraints.WEST;
		gbc_btnUndo.gridheight = 3;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
		gbc_btnUndo.gridx = 3;
		gbc_btnUndo.gridy = 2;
		numberPanel.add(btnUndo, gbc_btnUndo);
		
		button_4 = new NumberButtons("4");
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 0;
		gbc_button_4.gridy = 3;
		numberPanel.add(button_4, gbc_button_4);
		
		button_5 = new NumberButtons("5");
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 1;
		gbc_button_5.gridy = 3;
		numberPanel.add(button_5, gbc_button_5);
		
		button_6 = new NumberButtons("6");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 2;
		gbc_button_6.gridy = 3;
		numberPanel.add(button_6, gbc_button_6);
		
		button_1 = new NumberButtons("1");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 4;
		numberPanel.add(button_1, gbc_button_1);
		
		button_2 = new NumberButtons("2");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 4;
		numberPanel.add(button_2, gbc_button_2);
		
		button_3 = new NumberButtons("3");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 2;
		gbc_button_3.gridy = 4;
		numberPanel.add(button_3, gbc_button_3);
		
		button_0 = new NumberButtons("0");
		GridBagConstraints gbc_button_0 = new GridBagConstraints();
		gbc_button_0.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_0.insets = new Insets(0, 0, 0, 5);
		gbc_button_0.gridx = 0;
		gbc_button_0.gridy = 5;
		numberPanel.add(button_0, gbc_button_0);
		
		btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				String operator = operatorTextField.getText();
				
				int valor = Integer.parseInt( factorTextField.getText() );
				
				if( operator.equals("+") && valor != 0 ) {
					
					SomaSubCommand soma = new SomaSubCommand( calculator, valor );
					
					caller.storeAndExecute( soma );
					
					mainTextField.setText( Double.toString( calculator.getTotal() )  );
					
					
				}
				
				else if( operator.equals("-") && valor != 0 ) {
					
					SomaSubCommand sub = new SomaSubCommand( calculator, -valor );
					
					caller.storeAndExecute( sub );
					
					mainTextField.setText( Double.toString( calculator.getTotal() )  );
					
					
				}
				
				else if( operator.equals("*") && valor != 0 ) {
										
					MultDivCommand mult = new MultDivCommand( calculator, valor );
					
					caller.storeAndExecute( mult );
					
					mainTextField.setText( Double.toString( calculator.getTotal() )  );
					
				}
				
				else if( operator.equals("/") && valor != 0 ) {
					
					MultDivCommand div = new MultDivCommand( calculator, 1.0d / valor );
					
					caller.storeAndExecute( div );
					
					mainTextField.setText( Double.toString( calculator.getTotal() )  );
					
					
				}
				
			}
		});
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEnter.gridwidth = 2;
		gbc_btnEnter.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 5;
		numberPanel.add(btnEnter, gbc_btnEnter);
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				caller.reexecute();
				
				mainTextField.setText( Double.toString( calculator.getTotal() )  );
				
			}
		});
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.gridx = 3;
		gbc_btnRedo.gridy = 5;
		numberPanel.add(btnRedo, gbc_btnRedo);
		numberPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{button_10, button_11, button_12, button_13, button_7, button_8, button_9, btnUndo, button_4, button_5, button_6, button_1, button_2, button_3, button_0, btnEnter}));

	}

}
