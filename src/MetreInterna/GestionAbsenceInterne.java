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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import com.toedter.calendar.JDateChooser;

import Connection.connection;

import net.proteanit.sql.DbUtils;
import javax.swing.DefaultComboBoxModel;

public class GestionAbsenceInterne extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JDateChooser dateChooser;
	String    id;
	private JTextField textField;
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
					GestionAbsenceInterne frame = new GestionAbsenceInterne();
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
	public GestionAbsenceInterne() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Nom etudiant : ");
		lblNewLabel.setBounds(60, 138, 104, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblDateDabsence = new JLabel("Date d'absence :");
		lblDateDabsence.setBounds(60, 185, 104, 22);
		contentPane.add(lblDateDabsence);
		
		JLabel lblRaison = new JLabel(" La justification  :");
		lblRaison.setBounds(60, 308, 104, 22);
		contentPane.add(lblRaison);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(163, 138, 184, 22);
		contentPane.add(comboBox);
		remplerComboBox();
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 308, 184, 22);
		contentPane.add(textField_1);
		
		
		JButton btnNewButton = new JButton("Ajouter :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomet =comboBox.getSelectedItem().toString();
				String date =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String justification =textField_1.getText().toString();
				String numdortoir =textField.getText().toString();
				String type =comboBox_1.getSelectedItem().toString();
			
				String sql ="insert into absenceinterne(nom,date,numdortoir ,type,justification) values(?,?,?,?,?) ";
				try {
					if(  !numdortoir.equals("") &&  !date.equals("") ) {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, nomet);
					pstm.setString(2,date);
					pstm.setString(3,numdortoir);
					pstm.setString(4,type);
					pstm.setString(5,justification);
				
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter absence ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Absence ajouter ");
					UpdateTable();
					textField_1.setText("");
					textField.setText("");
					
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
				
				String nomet =comboBox.getSelectedItem().toString();
				String date =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String justification =textField_1.getText().toString();
				String numdortoir =textField.getText().toString();
				String type =comboBox_1.getSelectedItem().toString();
				String sql ="update absenceinterne  set nom=?,date=?,numdortoir =?,type=?,justification=? where id_absenceintenre='"+ id +"'";
				try {
					if( !numdortoir.equals("") &&  !date.equals("") ) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1, nomet);
						pstm.setString(2,date);
						pstm.setString(3,numdortoir);
						pstm.setString(4,type);
						pstm.setString(5,justification);
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  absence ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Absence Modifier  ");
						UpdateTable();
						textField_1.setText("");
						textField.setText("");
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
				String date =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String numdortoir =textField.getText().toString();
				String sql ="delete from absenceinterne  where id_absence ='"+id+"'";
				try {
					
					if(!numdortoir.equals("") &&  !date.equals("")) {
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
		scrollPane.setBounds(426, 188, 891, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
	
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("  Table absece d'internat  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(476, 155, 172, 22);
		contentPane.add(lblNewLabel_1);
		
		JButton btnActuliser = new JButton("Actuliser :");
		btnActuliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
			}
		});
		btnActuliser.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActuliser.setBounds(1151, 147, 104, 30);
		contentPane.add(btnActuliser);
		
		JButton btnMenue = new JButton("Retoure :");
		btnMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 MetreInterna obj = new MetreInterna();
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
		dateChooser.setBounds(163, 185, 184, 22);
		contentPane.add(dateChooser);
		
		JLabel lblJestifierOuNon = new JLabel("Type   :");
		lblJestifierOuNon.setBounds(60, 264, 104, 22);
		contentPane.add(lblJestifierOuNon);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(163, 218, 184, 22);
		contentPane.add(textField);
		
		JLabel lblNumeroDortoire = new JLabel("Numero dortoire  :");
		lblNumeroDortoire.setBounds(28, 218, 113, 22);
		contentPane.add(lblNumeroDortoire);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"justifie", "injustifie"}));
		comboBox_1.setBounds(163, 264, 184, 22);
		contentPane.add(comboBox_1);
		
		JButton btnLesAretes = new JButton("Les Aretes  :");
		btnLesAretes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 GestionEleveArrêtés obj = new GestionEleveArrêtés();
					obj.setVisible(true);
					fermer();
					obj.setLocationRelativeTo(null);
			}
		});
		btnLesAretes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLesAretes.setBounds(217, 11, 130, 30);
		contentPane.add(btnLesAretes);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int  ling = table.getSelectedRow();
			      id =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from absenceinterne where id_absenceinterne ='"+id+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						textField_1.setText(rst.getString("justification"));
					    comboBox.setSelectedItem(rst.getString("nom"));
					    dateChooser.setDateFormatString(rst.getString("date"));
					    textField.setText(rst.getString("numdortoir"));
					    comboBox_1.setSelectedItem(rst.getString("type"));
					}
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			}
		});
	}
	public void UpdateTable() {
		String sql ="select id_absenceinterne as Id_Absence ,nom as Nom_Etudiant,date as Date,numdortoir as Num_Dortoire ,type as Type ,justification as Justification  from absenceinterne";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public void remplerComboBox() {
		String sql="select* from etudiantinterne";
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
