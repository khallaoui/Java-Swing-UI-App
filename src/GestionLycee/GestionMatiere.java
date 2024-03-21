package GestionLycee;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Connection.connection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GestionMatiere extends JFrame {

	

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	Connection cn=null;
	ResultSet rst =null;
	String    matricule;
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
					GestionMatiere frame = new GestionMatiere();
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
	public GestionMatiere() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Matricule :");
		lblNewLabel.setBounds(61, 134, 113, 22);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(184, 134, 162, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAppelation = new JLabel("Appelation :");
		lblAppelation.setBounds(61, 193, 113, 22);
		contentPane.add(lblAppelation);
		
		JLabel lblFelier = new JLabel("Felier :");
		lblFelier.setBounds(61, 257, 113, 22);
		contentPane.add(lblFelier);
		
		JLabel lblNiveau = new JLabel("Niveau :");
		lblNiveau.setBounds(61, 319, 113, 22);
		contentPane.add(lblNiveau);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(184, 194, 162, 22);
		contentPane.add(textField_1);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(184, 257, 162, 22);
		contentPane.add(comboBox);
		remplerComboBox();
	
		 comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Trancomane", "1Bac", "2Bac"}));
		comboBox_1.setBounds(184, 320, 162, 22);
		contentPane.add(comboBox_1);
		
		
		
		JButton btnNewButton = new JButton("Ajouter  :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String matriculee  =textField.getText().toString();
				String appellation =textField_1.getText().toString();
				String felier =comboBox.getSelectedItem().toString();
				String niveau=comboBox_1.getSelectedItem().toString();
			
				String sql ="insert into matier(matricule,appellation,felier,niveau) values(?,?,?,?) ";
				try {
					if( !matriculee.equals("")&&  !appellation.equals("")) {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, matriculee);
					pstm.setString(2, appellation);
					pstm.setString(3,felier);
					pstm.setString(4, niveau);
				
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter matiere ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Matiere ajouter ");
					UpdateTable();
					textField.setText("");
					textField_1.setText("");
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
		scrollPane.setBounds(471, 196, 801, 362);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int  ling = table.getSelectedRow();
				matricule =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from matier where matricule ='"+matricule+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						textField.setText(rst.getString("matricule"));
						textField_1.setText(rst.getString("appellation"));
						  comboBox.setSelectedItem(rst.getString("felier"));
					    comboBox_1.setSelectedItem(rst.getString("niveau"));
					  
						 UpdateTable();
						
					}
				} catch (Exception ex) {
			
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Table des matiere :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(508, 159, 132, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMatiere = new JLabel("  Matiere ");
		lblMatiere.setForeground(Color.BLUE);
		lblMatiere.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMatiere.setBounds(100, 51, 132, 36);
		contentPane.add(lblMatiere);
		
		JButton btnModifier = new JButton("Modifier  :");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matriculee  =textField.getText().toString();
				String appellation =textField_1.getText().toString();
				String felier =comboBox.getSelectedItem().toString();
				String niveau=comboBox_1.getSelectedItem().toString();
				String sql ="update matier  set matricule=?,appellation=?,felier=?,niveau=? where matricule ='"+ matricule +"'";
				try {
					
					if( !matriculee.equals("")&&  !appellation.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1,  matriculee);
						pstm.setString(2, appellation);
						pstm.setString(3,felier);
						pstm.setString(4, felier);
						
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  matier ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Matiere Modifier  ");
						UpdateTable();
						textField.setText("");
						textField_1.setText("");
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
				String id_matier  =textField.getText().toString();
				String appellation =textField_1.getText().toString();
				String sql ="delete from matier  where matricule ='"+matricule+"'";
				try {
					
					if(!id_matier.equals("") && !appellation.equals("") ) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  matiere ", JOptionPane.YES_NO_OPTION);
					  pstm=cn.prepareStatement(sql);
						
					  if (a==0) {pstm.execute();
					JOptionPane.showMessageDialog(null," Matiere  supprimer");
					UpdateTable();
					
					textField.setText("");
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
		btnActualiser.setBounds(1124, 156, 125, 29);
		contentPane.add(btnActualiser);
		
		JButton btnNewButton_1 = new JButton("Retoure  :");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionFelier obj = new  GestionFelier();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(23, 17, 112, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnAbsence = new JButton("Absence :");
		btnAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAbsence obj = new  GestionAbsence();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnAbsence.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAbsence.setBounds(143, 18, 115, 23);
		contentPane.add(btnAbsence);
	}
	public void UpdateTable() {
		
		String sql ="select  matricule as Matricule ,appellation as Appellation ,felier as Felier ,niveau as Niveau from matier";
			
			try {
				pstm=cn.prepareStatement(sql);
				rst=pstm.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rst));
			} catch (Exception e) {
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


