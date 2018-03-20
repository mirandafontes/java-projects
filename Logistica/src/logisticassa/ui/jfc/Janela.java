package logisticassa.ui.jfc;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.ButtonGroup;
import logisticassa.manager.*;
import logisticassa.ui.vo.RotasVO;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;


public class Janela {

	private JFrame frmLogisticaSsa;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_Origem;
	private JTextField textField_Nome;
	private JSpinner spinnerId;
	private JComboBox<RotasVO> comboBoxRotasDisponiveis;
	private JComboBox<String> comboBoxOrigem;
	private JComboBox<String> comboBoxDestino;
	private JSpinner spinnerPeso;
	private JTextField textFieldValor;
	private JRadioButton rdbtnDireto;
	private JRadioButton rdbtnFracionario;
	
	private Session session = new Session();
	
	private ArrayList<String> origem = new ArrayList<String>();
	private ArrayList<String> destinoByOrigem = new ArrayList<String>();
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	
	private ArrayList<RotasVO> rotas = new ArrayList<RotasVO>();
	private JButton btnContratar;
	private JTextField textFieldContratarAviso;
	private JTextField textField_Destino;
	private JSpinner spinnerCapacidadeTotal;
	private JSpinner spinnerCustoGrama;
	private JSpinner spinnerTempoEntrega;
	private JTextField textFieldAvisos;
	private JTextField textFieldContratarAviso1;
	private JSpinner spinnerIDS;
	private JComboBox<String> comboBoxRotasCadastradas;
	private JButton btnAddId;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					Janela window = new Janela();
					
					window.origem = window.session.gerarOrigem();
					
					window.frmLogisticaSsa.setVisible( true );
					
					//window.session.gerarDestino( ( String )  window.comboBoxOrigem.getSelectedItem() );
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		
		
		frmLogisticaSsa = new JFrame();
		frmLogisticaSsa.setTitle("Logistica SSA");
		frmLogisticaSsa.setBounds(100, 100, 900, 655);
		frmLogisticaSsa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelCadastroMain = new JPanel();
		
		JPanel panelContratoMain = new JPanel();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setOrientation(SwingConstants.VERTICAL);
		GroupLayout groupLayout = new GroupLayout(frmLogisticaSsa.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelCadastroMain, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelContratoMain, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelContratoMain, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
						.addComponent(panelCadastroMain, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
		);
		
		JLabel lblNewLabel_1 = new JLabel("Cadastrar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelCadastroSub1 = new JPanel();
		GroupLayout gl_panelCadastroMain = new GroupLayout(panelCadastroMain);
		gl_panelCadastroMain.setHorizontalGroup(
			gl_panelCadastroMain.createParallelGroup(Alignment.LEADING)
				.addComponent(panelCadastroSub1, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
				.addGroup(gl_panelCadastroMain.createSequentialGroup()
					.addGap(178)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		gl_panelCadastroMain.setVerticalGroup(
			gl_panelCadastroMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastroMain.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCadastroSub1, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
					.addGap(1))
		);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblOrigem = new JLabel("Origem");
		lblOrigem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		spinnerId = new JSpinner();
		spinnerId.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		
		spinnerTempoEntrega = new JSpinner();
		spinnerTempoEntrega.setEnabled(false);
		spinnerTempoEntrega.setModel(new SpinnerNumberModel(1, 1, 365, 1));
		
		spinnerCustoGrama = new JSpinner();
		spinnerCustoGrama.setEnabled(false);
		spinnerCustoGrama.setModel(new SpinnerNumberModel(0.0, 0.0, 1000.0, 1.0));
		
		spinnerCapacidadeTotal = new JSpinner();
		spinnerCapacidadeTotal.setEnabled(false);
		spinnerCapacidadeTotal.setModel(new SpinnerNumberModel(1.0, 1.0, 74000.0, 1.0));
		
		rdbtnDireto = new JRadioButton("Direto");
		rdbtnDireto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if( rdbtnDireto.isSelected() ) {
					
					spinnerTempoEntrega.setEnabled(true);
					spinnerCustoGrama.setEnabled(true);
					spinnerCapacidadeTotal.setEnabled(true);
					
				}
				
				else {
					
					spinnerTempoEntrega.setEnabled(false);
					spinnerCustoGrama.setEnabled(false);
					spinnerCapacidadeTotal.setEnabled(false);
					
				}
				
			}
		});
		buttonGroup.add(rdbtnDireto);
		rdbtnDireto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnDireto.setMnemonic('D');
		
		spinnerIDS = new JSpinner();
		spinnerIDS.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		spinnerIDS.setEnabled(false);
		spinnerIDS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnAddId = new JButton("Adicionar ID");
		btnAddId.setEnabled(false);
		btnAddId.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
				
				if( rdbtnFracionario.isSelected() && btnAddId.getModel().isPressed() ) {
					
					int check = ( Integer ) spinnerIDS.getValue();
					
					if( ids.contains( ( Integer) check ) ) {
						
						SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
						
						textFieldAvisos.setText("Este ID ja foi adicionado");
						
					}
					
					else {
						
						SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
					
						ids.add( ( Integer ) spinnerIDS.getValue() );
						
						textFieldAvisos.setText("ID adicionado a Rota Fracionada");

					}
					
				}
				
			}
		});
		
		textField_Destino = new JTextField();
		textField_Destino.setColumns(1);
			
		textField_Origem = new JTextField();
		textField_Origem.setColumns(1);

		
		rdbtnFracionario = new JRadioButton("Fracionario");
		rdbtnFracionario.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if( rdbtnFracionario.isSelected() ) {
					
					spinnerIDS.setEnabled(true);
					btnAddId.setEnabled(true);
					textField_Origem.setEnabled(false);
					textField_Destino.setEnabled(false);
					
				}
				
				else {
					
					spinnerIDS.setEnabled(false);
					btnAddId.setEnabled(false);
					textField_Origem.setEnabled(true);
					textField_Destino.setEnabled(true);
					
				}
				
			}
		});
		
		buttonGroup.add(rdbtnFracionario);
		rdbtnFracionario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnFracionario.setMnemonic('F');
	
		textField_Nome = new JTextField();
		textField_Nome.setColumns(1);

		
		JLabel lblIdMax = new JLabel("ID MAX: 999");
		lblIdMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCapacidadeTotal = new JLabel("Tempo Entrega");
		lblCapacidadeTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCustoGrama = new JLabel("Custo Grama");
		lblCustoGrama.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCapacidadeTotal_1 = new JLabel("Capacidade Total");
		lblCapacidadeTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTempoEntrega = new JLabel("Tempo Entrega: Dias");
		lblTempoEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCustoGrama_1 = new JLabel("Custo Grama : R$ / g");
		lblCustoGrama_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCapacidadeMaxima = new JLabel("Capacidade Maxima: 74000 Kg");
		lblCapacidadeMaxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
				
				if( rdbtnDireto.isSelected() || rdbtnFracionario.isSelected() ) {
					
					int id = ( int ) spinnerId.getValue();
					String nome = textField_Nome.getText().toUpperCase();
					String destino = textField_Destino.getText().toUpperCase();
					String origem = textField_Origem.getText().toUpperCase();
					
					if( rdbtnDireto.isSelected() && btnCadastrar.getModel().isPressed() ) {
						
						int tempoEntrega = ( int ) spinnerTempoEntrega.getValue();
						double custoGrama = ( double ) spinnerCustoGrama.getValue();
						double capacidadeTotal = ( double ) spinnerCapacidadeTotal.getValue();
						
						try {
							
							session.registrarRotaDireta(id, nome, destino, origem, tempoEntrega, custoGrama, capacidadeTotal  );
							
							Janela.this.origem = session.gerarOrigem();
							 
							Janela.this.destinoByOrigem = session.gerarDestino( Janela.this.origem.get(0) );
							 
							SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
							 
							textFieldAvisos.setText("A rota foi cadastrada com sucesso.");
							
							spinnerId.setValue( ( Integer ) 0 );
							spinnerTempoEntrega.setValue( ( Integer ) 1 );
							spinnerCustoGrama.setValue( ( Double ) 0.0 );
							spinnerCapacidadeTotal.setValue( ( Double ) 1.0 );
							textField_Nome.setText("");
							textField_Destino.setText("");
							textField_Origem.setText("");
							 
							//Atualizar Rotas Cadastradas TODO
							ArrayList<RotasVO> allRotas = session.gerarRotas();
							 
							comboBoxRotasCadastradas.removeAllItems();
								
							for( RotasVO vo : allRotas ) {
									
								comboBoxRotasCadastradas.addItem( "ID : ( " + Integer.toString( vo.getId() ) + " ) " + vo.toString() + " Origem : [ " + vo.getOrigem() + " ] " + " Destino : [ " + vo.getDestino() + " ]"
										+ " Custo Grama : ( " + Double.toString( vo.getCustoGrama() ) + " )  Capacidade Total : ( " + Double.toString( vo.getCapacidadeTotal() )
										+ " )  Capacidade Alocada : ( " + Double.toString( vo.getCapacidadeAlocada()) + " ) " );									
							}
								
							//Origem TODO
								
							Janela.this.origem.clear();
								
							Janela.this.origem = Janela.this.session.gerarOrigem();
								
							comboBoxOrigem.removeAllItems();
								
							for( int i = 0 ; i < Janela.this.origem.size() ; i++ ) {
																		
								comboBoxOrigem.addItem( Janela.this.origem.get( i ) );
									
							}
								
							//DESTINO TODO
								
							destinoByOrigem.clear();
							destinoByOrigem = session.gerarDestino( ( String ) comboBoxOrigem.getSelectedItem() );
								
							comboBoxDestino.setEnabled(true);
							comboBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
							comboBoxDestino.setSelectedIndex( -1 );
							comboBoxDestino.removeAllItems();
								
							for( int i = 0 ; i < destinoByOrigem.size() ; i++ ) {
									
								comboBoxDestino.addItem( destinoByOrigem.get( i ) );
									
							}
							 
							 
							
							
						}
						
						catch( Exception ex ) {
							
							SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
							
							ex.printStackTrace();
							
							textFieldAvisos.setText("Nao foi possivel cadastrar a rota. Ex : " + ex.getMessage() );
							
						}
						
					}
					
					else if ( ( rdbtnFracionario.isSelected() && btnCadastrar.getModel().isPressed() ) && ids.size() >= 2 ) {
						
						
						try {
							
							SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
							
							int[] idlocal = new int[ ids.size() ];
							
							for( int i = 0 ; i < idlocal.length ; i++ ) {
								
								idlocal[i] = ids.get( i );
								
							}
							
							session.registrarRotaFracionada(id, nome, idlocal );
							
							ids.clear();
							
							textFieldAvisos.setText("A rota foi cadastrada com sucesso.");
							
							//Atualizar Rotas Cadastradas TODO
							ArrayList<RotasVO> allRotas = session.gerarRotas();
							 
							comboBoxRotasCadastradas.removeAllItems();
								
							for( RotasVO vo : allRotas ) {
									
								comboBoxRotasCadastradas.addItem( "ID : ( " + Integer.toString( vo.getId() ) + " ) " + vo.toString() + " Origem : [ " + vo.getOrigem() + " ] " + " Destino : [ " + vo.getDestino() + " ]"
										+ " Custo Grama : ( " + Double.toString( vo.getCustoGrama() ) + " )  Capacidade Total : ( " + Double.toString( vo.getCapacidadeTotal() )
										+ " )  Capacidade Alocada : ( " + Double.toString( vo.getCapacidadeAlocada()) + " ) " );									
							}
								
							//Origem TODO
								
							Janela.this.origem.clear();
								
							Janela.this.origem = Janela.this.session.gerarOrigem();
								
							comboBoxOrigem.removeAllItems();
								
							for( int i = 0 ; i < Janela.this.origem.size() ; i++ ) {
																		
								comboBoxOrigem.addItem( Janela.this.origem.get( i ) );
									
							}
								
							//DESTINO TODO
								
							destinoByOrigem.clear();
							destinoByOrigem = session.gerarDestino( ( String ) comboBoxOrigem.getSelectedItem() );
								
							comboBoxDestino.setEnabled(true);
							comboBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
							comboBoxDestino.setSelectedIndex( -1 );
							comboBoxDestino.removeAllItems();
								
							for( int i = 0 ; i < destinoByOrigem.size() ; i++ ) {
									
								comboBoxDestino.addItem( destinoByOrigem.get( i ) );
									
							}							
							
							
						}
						
						catch( Exception ex ) {
							
							SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
							
							ex.printStackTrace();
							
							textFieldAvisos.setText("Nao foi possivel cadastrar a rota. Ex : " + ex.getMessage() );
							
						}
						
					}
					
					else if (  ( rdbtnFracionario.isSelected() && btnCadastrar.getModel().isPressed() ) && ids.size() < 2 ) {
						
						SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
						
						textFieldAvisos.setText("ID's insuficientes. A rota fracionaria precisa de no minimo dois ID's.");
						
					}
					
				}
				
			}
		});
		
		textFieldAvisos = new JTextField();
		textFieldAvisos.setEditable(false);
		textFieldAvisos.setColumns(10);
		
		JLabel lblIds = new JLabel("ID's");
		lblIds.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblRotasCadastradas = new JLabel("Rotas Cadastradas");
		lblRotasCadastradas.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		comboBoxRotasCadastradas = new JComboBox<String>();
		comboBoxRotasCadastradas.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		ArrayList<RotasVO> allRotas = session.gerarRotas();
		
		for( RotasVO vo : allRotas ) {
			
			comboBoxRotasCadastradas.addItem( "ID : ( " + Integer.toString( vo.getId() ) + " ) " + vo.toString() + " Origem : [ " + vo.getOrigem() + " ] " + " Destino : [ " + vo.getDestino() + " ]"
					+ " Custo Grama : ( " + Double.toString( vo.getCustoGrama() ) + " )  Capacidade Total : ( " + Double.toString( vo.getCapacidadeTotal() )
					+ " )  Capacidade Alocada : ( " + Double.toString( vo.getCapacidadeAlocada()) + " ) " );			
		}	
		
		GroupLayout gl_panelCadastroSub1 = new GroupLayout(panelCadastroSub1);
		gl_panelCadastroSub1.setHorizontalGroup(
			gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastroSub1.createSequentialGroup()
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addComponent(lblDestino)
									.addGap(18)
									.addComponent(textField_Destino, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addComponent(rdbtnDireto)
									.addPreferredGap(ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
									.addComponent(rdbtnFracionario)
									.addGap(14))))
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOrigem)
								.addComponent(lblNome)
								.addComponent(lblId))
							.addGap(14)
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addComponent(spinnerId, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addGap(8)
									.addComponent(lblIdMax)
									.addGap(172))
								.addComponent(textField_Origem, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
								.addComponent(textField_Nome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)))
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addComponent(lblCapacidadeTotal)
									.addGap(18)
									.addComponent(spinnerTempoEntrega, GroupLayout.PREFERRED_SIZE, 82, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblIds))
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCapacidadeTotal_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCustoGrama, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelCadastroSub1.createSequentialGroup()
											.addComponent(spinnerCapacidadeTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(13))
										.addComponent(spinnerCustoGrama, 0, 0, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.TRAILING)
								.addComponent(spinnerIDS, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addComponent(btnAddId)
									.addGap(41)))))
					.addGap(0))
				.addGroup(gl_panelCadastroSub1.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldAvisos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelCadastroSub1.createSequentialGroup()
							.addComponent(comboBoxRotasCadastradas, 0, 419, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(Alignment.LEADING, gl_panelCadastroSub1.createSequentialGroup()
							.addGap(165)
							.addComponent(btnCadastrar)
							.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE))
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addGap(141)
							.addComponent(lblRotasCadastradas, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)))
					.addGap(4))
				.addGroup(gl_panelCadastroSub1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCapacidadeMaxima)
					.addContainerGap(248, Short.MAX_VALUE))
				.addGroup(gl_panelCadastroSub1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCustoGrama_1)
					.addContainerGap(303, Short.MAX_VALUE))
				.addGroup(gl_panelCadastroSub1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTempoEntrega)
					.addContainerGap(303, Short.MAX_VALUE))
		);
		gl_panelCadastroSub1.setVerticalGroup(
			gl_panelCadastroSub1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCadastroSub1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(spinnerId)))
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addGap(14)
							.addComponent(lblIdMax)))
					.addGap(15)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblNome)
						.addComponent(textField_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOrigem)
						.addComponent(textField_Origem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblDestino)
						.addComponent(textField_Destino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnDireto)
						.addComponent(rdbtnFracionario))
					.addGap(18)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblCapacidadeTotal)
						.addComponent(spinnerTempoEntrega, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIds)
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addGap(1)
							.addComponent(spinnerIDS, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCadastroSub1.createSequentialGroup()
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerCustoGrama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelCadastroSub1.createSequentialGroup()
									.addGap(6)
									.addComponent(lblCustoGrama, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panelCadastroSub1.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerCapacidadeTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCapacidadeTotal_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnAddId))
					.addGap(18)
					.addComponent(lblTempoEntrega)
					.addGap(12)
					.addComponent(lblCustoGrama_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCapacidadeMaxima)
					.addGap(24)
					.addComponent(lblRotasCadastradas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxRotasCadastradas, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(btnCadastrar)
					.addGap(18)
					.addComponent(textFieldAvisos, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
		);
		panelCadastroSub1.setLayout(gl_panelCadastroSub1);
		panelCadastroMain.setLayout(gl_panelCadastroMain);
		
		JLabel lblNewLabel = new JLabel("Contratar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelContratoSub1 = new JPanel();
		GroupLayout gl_panelContratoMain = new GroupLayout(panelContratoMain);
		gl_panelContratoMain.setHorizontalGroup(
			gl_panelContratoMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContratoMain.createSequentialGroup()
					.addGroup(gl_panelContratoMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContratoMain.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelContratoSub1, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
						.addGroup(gl_panelContratoMain.createSequentialGroup()
							.addGap(180)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 172, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelContratoMain.setVerticalGroup(
			gl_panelContratoMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContratoMain.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelContratoSub1, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
					.addGap(9))
		);
		
		JLabel lblOrigem_1 = new JLabel("Origem");
		lblOrigem_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDestino_1 = new JLabel("Destino");
		lblDestino_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxRotasDisponiveis = new JComboBox<RotasVO>();
		comboBoxRotasDisponiveis.setEnabled(true);
		
		comboBoxDestino = new JComboBox<String>();
		comboBoxDestino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				String destino = ( String ) comboBoxDestino.getSelectedItem();
				String origem = ( String ) comboBoxOrigem.getSelectedItem();
				
				SwingUtilities.updateComponentTreeUI(panelContratoSub1);
				
				if( ( e.getStateChange() == ItemEvent.SELECTED ) && ( comboBoxDestino.getSelectedIndex() != -1 ) ) {
					
					try {
							
						comboBoxRotasDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 11));
						comboBoxRotasDisponiveis.removeAllItems();
						
						
						rotas = session.findByOrigemAndDestino( origem, destino );

						
						for( RotasVO vo : rotas ) {
							
							comboBoxRotasDisponiveis.addItem( vo );
							
						}
								
						for( RotasVO vo : rotas ) {
						
							if( comboBoxRotasDisponiveis.getSelectedItem().equals( vo ) ) {
								
								textFieldValor.setText("");
															
								textFieldValor.setText( Double.toString( vo.getCustoGrama() * ( ( double ) spinnerPeso.getValue() * 1000.0 ) ) );
							
							}
						
						}
						
					}
	
					catch( Exception ex ) {
						
						SwingUtilities.updateComponentTreeUI( panelContratoSub1 );
						
						textFieldContratarAviso1.setText("Nao foi possivel contratar a rota. Ex : " + ex.getMessage() );
						
					}
					
				}
				
				
				
				
				
			}
		});
		comboBoxDestino.setEnabled(false);
		
		
		comboBoxOrigem = new JComboBox<String>();
		comboBoxOrigem.setSelectedItem(null);
		
		comboBoxOrigem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				SwingUtilities.updateComponentTreeUI(panelContratoSub1);
								
				String origem = ( String ) comboBoxOrigem.getSelectedItem();
				
				if( origem != null  ) {
						
					try {
						
						destinoByOrigem.clear();
						destinoByOrigem = session.gerarDestino( origem );
						
						comboBoxDestino.setEnabled(true);
						comboBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
						comboBoxDestino.setSelectedIndex( -1 );
						comboBoxDestino.removeAllItems();
						
						for( int i = 0 ; i < destinoByOrigem.size() ; i++ ) {
							
							comboBoxDestino.addItem( destinoByOrigem.get( i ) );
							
						}
						
					}
					
					catch( Exception e ) {
						
						SwingUtilities.updateComponentTreeUI( panelCadastroSub1 );
						
						textFieldAvisos.setText("Nao foi possivel cadastrar a rota. Ex : " + e.getMessage() );
						
					}
					
				}
				
			}
		});

		comboBoxOrigem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblMax = new JLabel("Max : 74000 Kg");
		lblMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		spinnerPeso = new JSpinner();
		spinnerPeso.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				SwingUtilities.updateComponentTreeUI(panelContratoSub1);
				
				for( RotasVO vo : rotas ) {
					
					
					if( comboBoxRotasDisponiveis.getSelectedItem().equals( vo ) ) {
						
						textFieldValor.setText("");
													
						textFieldValor.setText( Double.toString( vo.getCustoGrama() * ( ( double ) spinnerPeso.getValue() * 1000.0 ) ) );
					
					}
					
				}
				
				
				
			}
		});

		spinnerPeso.setModel(new SpinnerNumberModel(100.0, 1.0, 74000.0, 100.0));
		
		JLabel lblRotas = new JLabel("Rotas");
		lblRotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRotas.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textFieldValor = new JTextField();
		textFieldValor.setDropMode(DropMode.INSERT);
		textFieldValor.setEditable(false);
		textFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldValor.setColumns(10);
		
		textFieldContratarAviso = new JTextField();
		textFieldContratarAviso.setEditable(false);
		textFieldContratarAviso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldContratarAviso.setColumns(10);
		
		btnContratar = new JButton("Contratar");
		btnContratar.setEnabled(true);
		btnContratar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				
				RotasVO selected = ( RotasVO ) comboBoxRotasDisponiveis.getSelectedItem();
				double peso = ( double ) spinnerPeso.getValue(); 

				
				if( selected.podeAlocar(peso) ) {
		
					if( btnContratar.getModel().isPressed() ) {
						
						selected.alocar(peso);
						
						try {
							
							SwingUtilities.updateComponentTreeUI(panelContratoSub1);
							
							textFieldContratarAviso.setText("O transporte foi contratado.");
							
							session.gerarContratacaoDeTransporte( selected.getId() , peso );
							
							//Atualizar Rotas Cadastradas TODO
							ArrayList<RotasVO> allRotas = session.gerarRotas();
							 
							comboBoxRotasCadastradas.removeAllItems();
								
							for( RotasVO vo : allRotas ) {
									
								comboBoxRotasCadastradas.addItem( "ID : ( " + Integer.toString( vo.getId() ) + " ) " + vo.toString() + " Origem : [ " + vo.getOrigem() + " ] " + " Destino : [ " + vo.getDestino() + " ]"
																	+ " Custo Grama : ( " + Double.toString( vo.getCustoGrama() ) + " )  Capacidade Total : ( " + Double.toString( vo.getCapacidadeTotal() )
																	+ " )  Capacidade Alocada : ( " + Double.toString( vo.getCapacidadeAlocada()) + " ) " );
									
							}
								
							//Origem TODO
								
							Janela.this.origem.clear();
								
							Janela.this.origem = Janela.this.session.gerarOrigem();
								
							comboBoxOrigem.removeAllItems();
								
							for( int i = 0 ; i < Janela.this.origem.size() ; i++ ) {
																		
								comboBoxOrigem.addItem( Janela.this.origem.get( i ) );
									
							}
								
							//DESTINO TODO
								
							destinoByOrigem.clear();
							destinoByOrigem = session.gerarDestino( ( String ) comboBoxOrigem.getSelectedItem() );
								
							comboBoxDestino.setEnabled(true);
							comboBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
							comboBoxDestino.setSelectedIndex( -1 );
							comboBoxDestino.removeAllItems();
								
							for( int i = 0 ; i < destinoByOrigem.size() ; i++ ) {
									
								comboBoxDestino.addItem( destinoByOrigem.get( i ) );
									
							}
							
						}
						
						catch ( Exception ex ) {
							
							SwingUtilities.updateComponentTreeUI( panelContratoSub1 );
							
							ex.printStackTrace();
							
							textFieldContratarAviso.setText("Nao foi possivel contratar a rota. Ex : " + ex.getMessage() );
							
						}
					}
					
				}
				
				else if ( !selected.podeAlocar(peso) && btnContratar.getModel().isPressed() ) {
					
					SwingUtilities.updateComponentTreeUI(panelContratoSub1);
					
					textFieldContratarAviso.setText("Peso acima do permetido pela rota.");
					
				}

				
			}
		});
		
		textFieldContratarAviso1 = new JTextField();
		textFieldContratarAviso1.setEditable(false);
		textFieldContratarAviso1.setColumns(10);

		
		GroupLayout gl_panelContratoSub1 = new GroupLayout(panelContratoSub1);
		gl_panelContratoSub1.setHorizontalGroup(
			gl_panelContratoSub1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContratoSub1.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContratoSub1.createSequentialGroup()
							.addComponent(lblValor)
							.addGap(26)
							.addComponent(textFieldValor, 117, 117, 117)
							.addGap(229))
						.addComponent(lblOrigem_1)
						.addGroup(gl_panelContratoSub1.createSequentialGroup()
							.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxRotasDisponiveis, 0, 392, Short.MAX_VALUE)
								.addGroup(gl_panelContratoSub1.createSequentialGroup()
									.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDestino_1)
										.addComponent(lblPeso))
									.addGap(18)
									.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panelContratoSub1.createSequentialGroup()
											.addComponent(spinnerPeso, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
											.addGap(36)
											.addComponent(lblMax))
										.addGroup(gl_panelContratoSub1.createSequentialGroup()
											.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.TRAILING)
												.addComponent(comboBoxOrigem, Alignment.LEADING, 0, 323, Short.MAX_VALUE)
												.addComponent(comboBoxDestino, 0, 323, Short.MAX_VALUE))
											.addGap(9)))))
							.addGap(10)))
					.addGap(0))
				.addGroup(gl_panelContratoSub1.createSequentialGroup()
					.addGap(17)
					.addComponent(textFieldContratarAviso1, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelContratoSub1.createSequentialGroup()
					.addGap(104)
					.addComponent(lblRotas, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(101))
				.addGroup(gl_panelContratoSub1.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContratoSub1.createSequentialGroup()
							.addGap(129)
							.addComponent(btnContratar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panelContratoSub1.createSequentialGroup()
							.addComponent(textFieldContratarAviso, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addGap(38))))
		);
		gl_panelContratoSub1.setVerticalGroup(
			gl_panelContratoSub1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContratoSub1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrigem_1)
						.addComponent(comboBoxOrigem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestino_1)
						.addComponent(comboBoxDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeso)
						.addComponent(lblMax)
						.addComponent(spinnerPeso, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblRotas, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboBoxRotasDisponiveis, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelContratoSub1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnContratar)
					.addGap(18)
					.addComponent(textFieldContratarAviso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(219)
					.addComponent(textFieldContratarAviso1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		panelContratoSub1.setLayout(gl_panelContratoSub1);
		panelContratoMain.setLayout(gl_panelContratoMain);
		frmLogisticaSsa.getContentPane().setLayout(groupLayout);
		
		
		this.origem = this.session.gerarOrigem();
		
		for( int i = 0 ; i < this.origem.size() ; i++ ) {
			
			this.comboBoxOrigem.addItem( this.origem.get( i ) );
			
		}
		
	}
}
