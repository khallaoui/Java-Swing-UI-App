package GestionLycee;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.connection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.awt.Color;

public class GestionNote extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	Connection cn=null;
	ResultSet rst =null;
	String     id;
	PreparedStatement pstm =null;
	JComboBox comboBox_1,comboBox;
	void fermer() {
		dispose();
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionNote frame = new GestionNote();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionNote() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Etudient ");
		lblNewLabel.setBounds(52, 99, 88, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblMatier = new JLabel("Matier :");
		lblMatier.setBounds(52, 143, 88, 20);
		contentPane.add(lblMatier);
		
		JLabel lblControle = new JLabel("Controle :");
		lblControle.setBounds(52, 190, 88, 20);
		contentPane.add(lblControle);
		
		JLabel lblExamane = new JLabel("Examen :");
		lblExamane.setBounds(52, 234, 88, 20);
		contentPane.add(lblExamane);
		
		JLabel lblTp = new JLabel("TP :");
		lblTp.setBounds(52, 283, 88, 20);
		contentPane.add(lblTp);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(165, 187, 157, 23);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(165, 231, 157, 23);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(165, 280, 157, 23);
		contentPane.add(textField_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(453, 186, 652, 381);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int  ling = table.getSelectedRow();
			      id =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from note where id_note ='"+id+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						 comboBox.setSelectedItem(rst.getString("id_etudiant"));
						 comboBox_1.setSelectedItem(rst.getString("id_matier"));
						textField_2.setText(rst.getString("examen"));
						textField_3.setText(rst.getString("controle"));
						textField_4.setText(rst.getString("tp"));
					   
					}
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Ajouter :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String etudiant  =comboBox.getSelectedItem().toString();
				String matier =comboBox_1.getSelectedItem().toString();
				String examen  =textField_2.getText().toString();
				String controle  =textField_3.getText().toString();
				String tp  =textField_4.getText().toString();
				
				
			
				String sql ="insert into note(id_etudiant,id_matier,examen,controle,tp) values(?,?,?,?,?) ";
				try {
					
					if( !etudiant.equals("")&&  !matier.equals("")&&  !examen.equals("")&&  !controle.equals("")&&  !tp.equals("")) {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, etudiant);
					pstm.setString(2, matier);
					pstm.setString(3,examen);
					pstm.setString(4, controle);
					pstm.setString(5, tp);
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter note ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Note ajouter ");
					UpdateTable();
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					}
					}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
					
				} catch (Exception  e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(10, 418, 98, 32);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier :");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String etudiant  =comboBox.getSelectedItem().toString();
				String matier =comboBox_1.getSelectedItem().toString();
				String examen  =textField_2.getText().toString();
				String controle  =textField_3.getText().toString();
				String tp  =textField_4.getText().toString();
				String sql ="update note  set id_etudiant=?,id_matier=?,examen=?,controle=? ,tp=? where id_note ='"+ id +"'";
				try {
					
					if(  !etudiant.equals("")&&  !matier.equals("")&&  !examen.equals("")&&  !controle.equals("")&&  !tp.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1,  etudiant);
						pstm.setString(2, matier);
						pstm.setString(3,examen);
						pstm.setString(4, controle);
						pstm.setString(5, tp);
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  note ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Note Modifier  ");
						UpdateTable();
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						}
						 
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch ( Exception e1) {
					//
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setBounds(118, 418, 98, 33);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer :");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String etudiant  =comboBox.getSelectedItem().toString();
				String matier =comboBox_1.getSelectedItem().toString();
				String examen  =textField_2.getText().toString();
				String controle  =textField_3.getText().toString();
				String tp  =textField_4.getText().toString();
				String sql ="delete from note  where id_note ='"+id+"'";
				try {
					
					if(!etudiant.equals("")&&  !matier.equals("")&&  !examen.equals("")&&  !controle.equals("")&&  !tp.equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  note ", JOptionPane.YES_NO_OPTION);
					  pstm=cn.prepareStatement(sql);
						
					  if (a==0) {pstm.execute();
					JOptionPane.showMessageDialog(null,"Note  supprimer");
					UpdateTable();
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBounds(226, 418, 112, 33);
		contentPane.add(btnSupprimer);
		
		comboBox = new JComboBox();
		comboBox.setBounds(165, 96, 157, 23);
		contentPane.add(comboBox);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(165, 140, 157, 23);
		contentPane.add(comboBox_1);
		
		JButton btnActualiser = new JButton("Actualiser :");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
			}
		});
		btnActualiser.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnActualiser.setBounds(970, 141, 112, 23);
		contentPane.add(btnActualiser);
		
		JLabel lblTableDesNotes = new JLabel("Table des notes ");
		lblTableDesNotes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTableDesNotes.setBounds(463, 152, 132, 23);
		contentPane.add(lblTableDesNotes);
		
		JButton btnNewButton_1 = new JButton("Retoure  :");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAbsence obj = new GestionAbsence();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(43, 11, 98, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnChangePasse = new JButton("Change passe  :");
		btnChangePasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangerMotePasseLycee obj = new ChangerMotePasseLycee();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnChangePasse.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChangePasse.setBounds(151, 11, 147, 23);
		contentPane.add(btnChangePasse);
		remplerComboBox1();
		remplerComboBox();
	}
	
public void UpdateTable() {
		
		String sql ="select  id_note as Id_note,id_etudiant as Etudiant  ,id_matier as Matiere ,examen as Examen ,controle as Controle ,tp as TP from note";
			
			try {
				pstm=cn.prepareStatement(sql);
				rst=pstm.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rst));
			} catch (Exception ex) {
				ex.printStackTrace();
			}}
			
		
			public void remplerComboBox1() {
				String sql="select* from matier group By appellation";
				try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					while(rst.next()) {
						String appellation=rst.getString("appellation").toString();
						String niveau =rst.getString("niveau").toString();
						
						 comboBox_1.addItem(appellation+"  de  "+niveau);
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
			public void remplerComboBox() {
				String sql="select* from etudiant";
				try {
					pstm= cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					while(rst.next()) {
						String nom=rst.getString("nom").toString();
						String prenom =rst.getString("prenom").toString();
					 comboBox.addItem(nom+"   "+prenom);
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				
				
				
				
			}	}

