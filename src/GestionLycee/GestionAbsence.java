package GestionLycee;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Connection.connection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class GestionAbsence extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	JComboBox comboBox,comboBox_1;
	JDateChooser dateChooser;
	String    id;
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
					GestionAbsence frame = new GestionAbsence();
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
	public GestionAbsence() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Nom etudiant : ");
		lblNewLabel.setBounds(60, 138, 104, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblDateDabsence = new JLabel("Date d'absence :");
		lblDateDabsence.setBounds(60, 201, 104, 22);
		contentPane.add(lblDateDabsence);
		
		JLabel lblRaison = new JLabel("Justification :");
		lblRaison.setToolTipText("");
		lblRaison.setBounds(79, 293, 92, 22);
		contentPane.add(lblRaison);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(163, 138, 184, 22);
		contentPane.add(comboBox);
		remplerComboBox();
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 293, 184, 22);
		contentPane.add(textField_1);
		
		
		JButton btnNewButton = new JButton("Ajouter :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomet =comboBox.getSelectedItem().toString();
				String date =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String raison =textField_1.getText().toString();
				String type =comboBox_1.getSelectedItem().toString();
				String sql ="insert into absence(nomet,date,type,raison) values(?,?,?,?) ";
				try {
					if( !nomet.equals("")&&   !date.equals("") ) {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, nomet);
					pstm.setString(2,date);
					pstm.setString(3,type);
					pstm.setString(4,raison);
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter absence ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Absence ajouter ");
					UpdateTable();
					textField_1.setText("");
					
					}
					}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(10, 396, 113, 30);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier :");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type =comboBox_1.getSelectedItem().toString();
				String nomet =comboBox.getSelectedItem().toString();
				String date =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String raison =textField_1.getText().toString();
				String sql ="update absence  set nomet=? ,date=?,type=? ,raison=? where id_absence='"+ id +"'";
				try {
					if(  !nomet.equals("")&&  !date.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1, nomet);
						pstm.setString(2,date);
						pstm.setString(3,type);
						pstm.setString(4,raison);
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  absence ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Absence Modifier  ");
						UpdateTable();
						textField_1.setText("");
						 }
						 
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch (SQLException e1) {
					//
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setBounds(133, 396, 113, 30);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer :");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomet =comboBox.getSelectedItem().toString();
				String date =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String raison =textField_1.getText().toString();
				String sql ="delete from absence  where id_absence ='"+id+"'";
				try {
					
					if(!nomet.equals("")&&   !date.equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  absence ", JOptionPane.YES_NO_OPTION);
					  pstm=cn.prepareStatement(sql);
						
					  if (a==0) {pstm.execute();
					JOptionPane.showMessageDialog(null,"Absence  supprimer");
					UpdateTable();
					textField_1.setText("");
					}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBounds(256, 396, 113, 30);
		contentPane.add(btnSupprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 182, 712, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
	
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("la table des absence");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(521, 137, 143, 22);
		contentPane.add(lblNewLabel_1);
		
		JButton btnActuliser = new JButton("Actuliser :");
		btnActuliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
			}
		});
		btnActuliser.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActuliser.setBounds(1020, 122, 104, 30);
		contentPane.add(btnActuliser);
		
		JButton btnMenue = new JButton("retoure  :");
		btnMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionMatiere obj = new GestionMatiere();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnMenue.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMenue.setBounds(91, 11, 104, 30);
		contentPane.add(btnMenue);
		
		 dateChooser = new JDateChooser();
		 dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(163, 201, 184, 22);
		contentPane.add(dateChooser);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"justifier", "injustifier"}));
		comboBox_1.setBounds(163, 247, 184, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setToolTipText("");
		lblType.setBounds(116, 247, 48, 22);
		contentPane.add(lblType);
		
		JButton btnNote = new JButton("note  :");
		btnNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionNote obj = new GestionNote();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNote.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNote.setBounds(215, 11, 104, 30);
		contentPane.add(btnNote);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int  ling = table.getSelectedRow();
			      id =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from absence where id_absence ='"+id+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						textField_1.setText(rst.getString("raison"));
					    comboBox.setSelectedItem(rst.getString("nomet"));
					    comboBox_1.setSelectedItem(rst.getString("type"));
					    dateChooser.setDateFormatString(rst.getString("date"));
					}
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			}
		});
	}
	public void UpdateTable() {
		String sql ="select id_absence as Id_Absence ,nomet as Nom_Etudiant,date as Date,type as Type ,raison as Raison from absence";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
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
		
		
		
		
		
	}
}
