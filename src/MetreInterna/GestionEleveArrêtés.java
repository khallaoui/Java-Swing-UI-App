package MetreInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Connection.connection;

import net.proteanit.sql.DbUtils;

public class GestionEleveArrêtés extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;
	Connection cn=null;
	ResultSet rst =null;
	String    id_areter ;
	PreparedStatement pstm =null;
	JComboBox comboBox, comboBox_1;
	private JTextField textField_2;
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
					GestionEleveArrêtés frame = new GestionEleveArrêtés();
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
	public GestionEleveArrêtés() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Nom etudiant  :");
		lblNewLabel.setBounds(61, 134, 113, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAppelation = new JLabel("Numero de dortoire  :");
		lblAppelation.setBounds(49, 194, 125, 22);
		contentPane.add(lblAppelation);
		
		JLabel lblFelier = new JLabel("La felier :");
		lblFelier.setBounds(110, 257, 63, 22);
		contentPane.add(lblFelier);
		
		JLabel lblNiveau = new JLabel("La raison :");
		lblNiveau.setBounds(110, 319, 72, 22);
		contentPane.add(lblNiveau);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(184, 194, 162, 22);
		contentPane.add(textField_1);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(184, 257, 162, 22);
		contentPane.add(comboBox);
		remplerComboBox();
		
		
		
		JButton btnNewButton = new JButton("Ajouter  :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom  =comboBox_1.getSelectedItem().toString();
				String numdortoir =textField_1.getText().toString();
				String felier =comboBox.getSelectedItem().toString();
				String raison =textField_2.getText().toString();
			
				String sql ="insert into areter(nom,numdortoir,felier,raison) values(?,?,?,?) ";
				try {
					if( !nom.equals("")&&  !numdortoir.equals("")&&!raison.equals("")) {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, nom);
					pstm.setString(2, numdortoir);
					pstm.setString(3,felier);
					pstm.setString(4, raison);
				
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter aretes ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Aretes ajouter ");
					UpdateTable();
					
					textField_1.setText("");
					textField_2.setText("");
					}}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
						
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(22, 421, 113, 29);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(516, 147, 780, 359);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int  ling = table.getSelectedRow();
				id_areter  =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from areter where id_areter ='"+id_areter +"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						textField_2.setText(rst.getString("raison"));
						textField_1.setText(rst.getString("numdortoir"));
						  comboBox.setSelectedItem(rst.getString("felier"));
					    comboBox_1.setSelectedItem(rst.getString("nom"));
					  
						 UpdateTable();
						
					}
				} catch (Exception ex) {
			
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Table des  etudiant arr\u00EAt\u00E9s  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(546, 114, 168, 22);
		contentPane.add(lblNewLabel_1);
		
		JButton btnModifier = new JButton("Modifier  :");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom  =comboBox_1.getSelectedItem().toString();
				String numdortoir =textField_1.getText().toString();
				String felier =comboBox.getSelectedItem().toString();
				String raison =textField_2.getText().toString();
				String sql ="update areter  set nom=?,numdortoir=?,felier=?,raison=? where id_areter ='"+ id_areter +"'";
				try {
					
					if( !nom.equals("")&&  !numdortoir.equals("")&&!raison.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1, nom);
						pstm.setString(2, numdortoir);
						pstm.setString(3,felier);
						pstm.setString(4, raison);
						
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  aretes ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Aretes Modifier  ");
						UpdateTable();
						textField_1.setText("");
						textField_2.setText("");
						}
						 
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch ( Exception e1) {
					//
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setBounds(145, 421, 113, 29);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer  :");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom  =comboBox_1.getSelectedItem().toString();
				String numdortoir =textField_1.getText().toString();
				String raison =textField_2.getText().toString();
				String sql ="delete from areter  where id_areter  ='"+id_areter+"'";
				try {
					
					if(!nom.equals("")&&  !numdortoir.equals("")&&!raison.equals("") ) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  aretes ", JOptionPane.YES_NO_OPTION);
					  pstm=cn.prepareStatement(sql);
						
					  if (a==0) {pstm.execute();
					JOptionPane.showMessageDialog(null," Aretes  supprimer");
					UpdateTable();
					
					textField_2.setText("");
					textField_1.setText("");
					}
					}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBounds(268, 421, 125, 29);
		contentPane.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser   :");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
			}
		});
		btnActualiser.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActualiser.setBounds(829, 87, 125, 29);
		contentPane.add(btnActualiser);
		
		JButton btnNewButton_1 = new JButton("Retoure :");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAbsenceInterne obj = new GestionAbsenceInterne();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(23, 17, 112, 23);
		contentPane.add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 319, 162, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(184, 135, 162, 22);
		contentPane.add(comboBox_1);
		
		JButton btnChanger = new JButton("Changer  :");
		btnChanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangerMotePasseMetre obj = new ChangerMotePasseMetre();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnChanger.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChanger.setBounds(158, 17, 100, 23);
		contentPane.add(btnChanger);
		remplerComboBoxinterne();
	}
	public void UpdateTable() {
		
		String sql ="select  id_areter as Id_Arete ,nom as Nom_Etudiant,numdortoir as Num_Dortoire ,felier as Felier ,raison as Raison from areter";
			
			try {
				pstm=cn.prepareStatement(sql);
				rst=pstm.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rst));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		
	}
	public void remplerComboBoxinterne() {
		String sql="select* from etudiantinterne";
		try {
			pstm= cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			while(rst.next()) {
				String nom=rst.getString("nom").toString();
				String prenom =rst.getString("prenom").toString();
			 comboBox_1.addItem(nom+"   "+prenom);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}
	public void remplerComboBox() {
		String sql="select* from felier";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			while(rst.next()) {
				String nom=rst.getString("nomfelier").toString();
				 comboBox.addItem(nom);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		}

}

