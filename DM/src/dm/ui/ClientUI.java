package dm.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dm.manager.Miner;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ClientUI {

	private JFrame mainFrame;
	
	private JFileChooser chooser = new JFileChooser();
	
	private File csv;
	
	private File save;
	
	private int rowCount;
	
	private int columnCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI window = new ClientUI();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setFont(new Font("Oswald Light", Font.PLAIN, 14));
		mainFrame.setTitle("Data Mining");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		rowCount = 0;
		columnCount = 0;

		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
		JPanel filePanel = new JPanel();
		filePanel.setToolTipText("Files");
		mainFrame.getContentPane().add(filePanel, BorderLayout.NORTH);
		filePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//tablePanel
		JPanel tablePanel = new JPanel();
		mainFrame.getContentPane().add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		JTextArea dataText = new JTextArea();
		dataText.setToolTipText("Data Area");
		dataText.setEditable(false);
		tablePanel.add(dataText, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane (dataText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tablePanel.add( scroll );
				
			
		JPanel subPanel = new JPanel();
		tablePanel.add(subPanel, BorderLayout.SOUTH);
		subPanel.setLayout(new BorderLayout(0, 0));
		
		JButton doItButton = new JButton("Do It!");
		doItButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.gc();
				
				new Miner(save, csv, rowCount, columnCount).mining();
				
				mainFrame.dispose();
				
				System.exit(1);
				
			}
		});
		
		doItButton.setToolTipText("Do a data mining, saving the information in a txt file.");
		doItButton.setIcon(new ImageIcon(ClientUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		doItButton.setEnabled(false);
		subPanel.add(doItButton, BorderLayout.EAST);
	
		//FilePanel
		JButton saveButton = new JButton("Create");
		saveButton.setToolTipText("After selected a CSV file, create a txt file to output.");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					JOptionPane.showMessageDialog(null, "File created at: " + System.getProperty("user.dir") , "Save Button", JOptionPane.INFORMATION_MESSAGE);
					
					save = new File( System.getProperty("user.dir"), "DM.txt" );
					
					doItButton.setEnabled(true);
					
					saveButton.setEnabled(false);
					
					System.gc();
					
				}
				
				catch ( Exception e ) {
					
					e.printStackTrace();
					
				}
				
			}
		});
		saveButton.setIcon(new ImageIcon(ClientUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		saveButton.setEnabled(false);

		JButton openButton = new JButton("Open");
		openButton.setToolTipText("Select a CSV file.");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int returnedValue = chooser.showOpenDialog(null);

				if( ( returnedValue == JFileChooser.APPROVE_OPTION ) && ( chooser.getSelectedFile().getPath().endsWith(".csv") == true )  ) {
															
					csv = chooser.getSelectedFile();
					
					FileReader fr;
					BufferedReader br;
	
					String aux = null;
					String toTextArea = new String();
						
					try {
						
						openButton.setEnabled(false);
							
						fr = new FileReader(csv);
						br = new BufferedReader(fr);
							
						JOptionPane.showMessageDialog(null, "Loading file... Please, click OK.", "Open Button", JOptionPane.INFORMATION_MESSAGE);
												
						if( ( aux = br.readLine() ) != null ) {
							
							toTextArea += aux;
							toTextArea += "\n\n";
							
							columnCount = aux.split(",").length;

							
						}
							
						while( ( aux = br.readLine() ) != null ) {
								
							toTextArea += aux;
							toTextArea += "\n";
							
							rowCount++;
								
						}
						
						JOptionPane.showMessageDialog(null, "Complete! Please, click OK.", "Open Button", JOptionPane.INFORMATION_MESSAGE);
						
						saveButton.setEnabled(true);
						
						System.gc();

							
					}
						
					catch (FileNotFoundException e) {
	
						e.printStackTrace();
							
					}
						
					catch(IOException ex) {
							
						ex.printStackTrace();
							
					}
						
						
					dataText.setText( toTextArea );

						
				}
				
				else {
					
					JOptionPane.showMessageDialog(null, "This file is not a .csv! Try again.", "Open Button", JOptionPane.WARNING_MESSAGE);
					
				}

			}
		});
		openButton.setIcon(new ImageIcon(ClientUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		filePanel.add(openButton);
		
		filePanel.add(saveButton);
	}
}
