package br.com.ConexaoBanco;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.protocol.Resultset;

import br.com.ConexaoBanco.ConexaoMySQL;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

public class Primeiro {

	private JFrame frame;
	private JTextField campoNome;
	private JTextField campoNascimento;
	private JTable table;
	private JScrollPane scrollPane;
	String sexo = "Selecione o Sexo";
	public static Point point = new Point();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Primeiro window = new Primeiro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Primeiro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 520, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuPrincipalSuperior = new JMenuBar();
		menuPrincipalSuperior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menuPrincipalSuperior.setBounds(0, 40, 520, 35);
		frame.getContentPane().add(menuPrincipalSuperior);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuPrincipalSuperior.add(menuArquivo);
		
		JMenuItem itemSalvar = new JMenuItem("Salvar");
		menuArquivo.add(itemSalvar);
		
		JMenuItem itemSair = new JMenuItem("Sair");
		menuArquivo.add(itemSair);
		
		JMenu menuEditar = new JMenu("Editar");
		menuPrincipalSuperior.add(menuEditar);
		
		JMenuItem itemCadastrar = new JMenuItem("Cadastrar");
		
		menuEditar.add(itemCadastrar);
		
		JMenuItem itemAtualizar = new JMenuItem("Atualizar");
		menuEditar.add(itemAtualizar);
		
		JMenuItem itemExcluir = new JMenuItem("Excluir");
		menuEditar.add(itemExcluir);
		
		JMenuItem itemListar = new JMenuItem("Listar");
		
		menuEditar.add(itemListar);
		
		JPanel painelDeCadastro = new JPanel();
		painelDeCadastro.setBounds(50, 128, 420, 290);
		frame.getContentPane().add(painelDeCadastro);
		painelDeCadastro.setLayout(null);
		
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblCadastro.setBounds(168, 11, 76, 26);
		painelDeCadastro.add(lblCadastro);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNome.setBounds(10, 73, 42, 19);
		painelDeCadastro.add(lblNome);
		
		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNascimento.setBounds(240, 73, 78, 19);
		painelDeCadastro.add(lblNascimento);
		
		campoNome = new JTextField();
		campoNome.setBounds(54, 72, 176, 20);
		painelDeCadastro.add(campoNome);
		campoNome.setColumns(10);
		
		campoNascimento = new JTextField();
		campoNascimento.setColumns(10);
		campoNascimento.setBounds(316, 72, 94, 20);
		painelDeCadastro.add(campoNascimento);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSexo.setBounds(10, 121, 34, 19);
		painelDeCadastro.add(lblSexo);
		
		JComboBox comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel<>(new String[] {"Selecione o Sexo", "Masculino", "Feminino"}));
		comboBoxSexo.setToolTipText("");
		comboBoxSexo.setBounds(47, 120, 131, 20);
		painelDeCadastro.add(comboBoxSexo);
		comboBoxSexo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				sexo = (String) comboBoxSexo.getSelectedItem();				
			}
		});
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(74, 234, 89, 23);
		painelDeCadastro.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(267, 234, 89, 23);
		painelDeCadastro.add(btnLimpar);
		painelDeCadastro.setVisible(false);
		
		JPanel painelDeListagem = new JPanel();
		painelDeListagem.setBounds(50, 128, 420, 290);
		frame.getContentPane().add(painelDeListagem);
		painelDeListagem.setLayout(null);
		
		JLabel lblListagemDeDados = new JLabel("Listagem de Dados");
		lblListagemDeDados.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblListagemDeDados.setBounds(124, 11, 161, 26);
		painelDeListagem.add(lblListagemDeDados);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 400, 164);
		painelDeListagem.add(scrollPane);
		
//		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"ID", "Nome", "Nascimento", "Sexo"
//			}
//		) {
//			boolean[] columnEditables = new boolean[] {
//				false, false, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
//		table.getColumnModel().getColumn(0).setPreferredWidth(30);
//		table.getColumnModel().getColumn(1).setPreferredWidth(130);
//		table.getColumnModel().getColumn(3).setPreferredWidth(35);
//		scrollPane.setViewportView(table);
		
		JButton botaoListaDados = new JButton("Listar Dados");
		botaoListaDados.setFont(new Font("Calibri", Font.PLAIN, 15));
		botaoListaDados.setBounds(156, 252, 107, 27);
		painelDeListagem.add(botaoListaDados);
		
		JPanel painelDaBarraSuperior = new JPanel();
		painelDaBarraSuperior.setLayout(null);
		painelDaBarraSuperior.setBackground(Color.WHITE);
		painelDaBarraSuperior.setBounds(0, 0, 520, 40);
		frame.getContentPane().add(painelDaBarraSuperior);
		
		JButton botaoFechar = new JButton("");		
		botaoFechar.setIcon(new ImageIcon(Primeiro.class.getResource("/resources/images/botaoFecharok.png")));
		botaoFechar.setOpaque(false);
		botaoFechar.setBorder(null);
		botaoFechar.setBounds(485, 0, 35, 40);
		painelDaBarraSuperior.add(botaoFechar);
		
		JButton botaoMaximizar = new JButton("");
		botaoMaximizar.setIcon(new ImageIcon(Primeiro.class.getResource("/resources/images/botaoMaximizar_35x40.jpg")));
		botaoMaximizar.setOpaque(false);
		botaoMaximizar.setBorder(null);
		botaoMaximizar.setBounds(450, 0, 35, 40);
		painelDaBarraSuperior.add(botaoMaximizar);
		
		JButton botaoMinimizar = new JButton("");
		botaoMinimizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		botaoMinimizar.setIcon(new ImageIcon(Primeiro.class.getResource("/resources/images/botaoMinimizarok.png")));
		botaoMinimizar.setOpaque(false);
		botaoMinimizar.setBorder(null);
		botaoMinimizar.setBounds(415, 0, 35, 40);
		painelDaBarraSuperior.add(botaoMinimizar);
		
		JLabel labelIconeDaBarraSuperior = new JLabel("");
		labelIconeDaBarraSuperior.setIcon(new ImageIcon(Primeiro.class.getResource("/resources/images/iconeprincipal35x40.png")));
		labelIconeDaBarraSuperior.setBounds(0, 0, 35, 40);
		painelDaBarraSuperior.add(labelIconeDaBarraSuperior);
		
		JLabel labelTituloBarraSuperior = new JLabel("Tela Inicial");
		labelTituloBarraSuperior.setFont(new Font("Calibri", Font.PLAIN, 15));
		labelTituloBarraSuperior.setBounds(45, 11, 201, 19);
		painelDaBarraSuperior.add(labelTituloBarraSuperior);
		painelDeListagem.setVisible(false);
		
		
		botaoListaDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				Connection connection = ConexaoMySQL.getConexaoMySQL();
				String sql = "select * from inciso1;";
				PreparedStatement smtp;
				try {					
					smtp = connection.prepareStatement(sql);
					ResultSet rs = smtp.executeQuery(sql);
//					DefaultTableModel tablemodel = new DefaultTableModel(new String[] {}, 0){};
					
					int qtdColunas = rs.getMetaData().getColumnCount();
					DefaultTableModel tablemodel = new DefaultTableModel(new String [] {"ID","NOME","NASCIMENTO","SEXO"}, 0);
//					System.out.println(qtdColunas);
//					System.out.println(tablemodel);
					
					table = new JTable(tablemodel);
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//					System.out.println(dtm);
//					table.setBounds(10, 50, 400, 164);
					
					while(rs.next()){
						
						String [] dados = new String [qtdColunas];
//						System.out.println(qtdColunas);
						for(int i = 1; i <=qtdColunas; i++){
							dados[i-1] = rs.getString(i);
//							System.out.println(rs.getString(i));
//							System.out.println(dados[i]);
							
						}
						dtm.addRow(dados);
//						System.out.println(dados);
//						System.out.println("\n");
						
						
					}
//					scrollPane.add(dtm);
					scrollPane.setViewportView(table);
//					painelListar.add(table);
//					System.out.println(table);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				connection.close();
//				smtp.close();
//				rs.close();
				
				
			}
		});
		
			btnCadastrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(campoNome.getText() == "" || campoNascimento.getText() == "" || sexo == "Selecione o Sexo"){
						
						JOptionPane.showMessageDialog(painelDeCadastro, "Preencha todos os campos!", "Um ou mais campos vazios", JOptionPane.ERROR_MESSAGE, null);
						
					}else{
					
					Connection connection = ConexaoMySQL.getConexaoMySQL();
					String sql= "insert into inciso1 values (default, ?, ?, ?)";
					PreparedStatement smtp;
					
					try {
						smtp = connection.prepareStatement(sql);
						
						smtp.setString(1, campoNome.getText());
						smtp.setString(2, campoNascimento.getText());
						smtp.setString(3, sexo);
						smtp.execute();	
						smtp.close();//
						connection.close();
						int opcao = JOptionPane.showConfirmDialog(painelDeCadastro, "Funcionario Cadastrado com Sucesso!\n\nDeseja Cadastrar Outro usuário e limpar os campos escritos?\n");
						System.out.println(opcao);
						if(opcao == 0){
							campoNome.setText("");
							campoNascimento.setText("");
							comboBoxSexo.setSelectedItem("Selecione o Sexo");
							
						}else if(opcao == 1){
							
							
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				}
			});
			
			painelDaBarraSuperior.addMouseListener(new MouseAdapter() {
				
			      public void mousePressed(MouseEvent e) {
			    	  
			        point.x = e.getX();
			        point.y = e.getY();
			      }
			    });
			
			painelDaBarraSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			      public void mouseDragged(MouseEvent e) {
			        Point p = frame.getLocation();
			        frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			      }
			    });
		
			btnLimpar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					campoNome.setText("");
					campoNascimento.setText("");
					comboBoxSexo.setSelectedItem("Selecione o Sexo");					
				}
			});
			
			itemCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					painelDeListagem.setVisible(false);
					painelDeCadastro.setVisible(true);
				}
			});
			
			botaoFechar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.exit(0);
				}
			});
			
			itemListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					painelDeCadastro.setVisible(false);
					painelDeListagem.setVisible(true);
				}
			});
	
	}
}
