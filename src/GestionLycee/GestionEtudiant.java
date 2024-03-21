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

public class GestionEtudiant extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrenom;
	private JTextField textNom;
	private JTextField txtCIN;
	private JTextField txtTel;
	private JTextField txtNaissence;
	private JTextField txtAdresse;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	private JTable table;
	JComboBox combFelier;
	JComboBox comboBox ;
	JLabel labimage;
	String id,s ;
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
					GestionEtudiant frame = new GestionEtudiant();
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
	public GestionEtudiant() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("  Prenom :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(38, 73, 70, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNom = new JLabel("    Nom :");
		lblNom.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNom.setBounds(48, 110, 70, 24);
		contentPane.add(lblNom);
		
		JLabel lblCin = new JLabel("     CIN :");
		lblCin.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCin.setBounds(48, 145, 70, 24);
		contentPane.add(lblCin);
		
		JLabel lblNumTel = new JLabel(" Num Tel  :");
		lblNumTel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNumTel.setBounds(38, 180, 70, 24);
		contentPane.add(lblNumTel);
		
		JLabel lblAdresse = new JLabel("  Adresse :");
		lblAdresse.setFont(new Font("Arial", Font.PLAIN, 13));
		lblAdresse.setBounds(38, 272, 70, 24);
		contentPane.add(lblAdresse);
		
		JLabel lblDateNaissense = new JLabel("Date Naissence :");
		lblDateNaissense.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDateNaissense.setBounds(10, 226, 103, 24);
		contentPane.add(lblDateNaissense);
		
		 combFelier = new JComboBox();
		combFelier.setBounds(116, 381, 162, 24);
		contentPane.add(combFelier);
		
		
		JLabel lblFelier = new JLabel(" fili\u00E8re :");
		lblFelier.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFelier.setBounds(56, 380, 70, 24);
		contentPane.add(lblFelier);
		 remplerComboBox();
		txtPrenom = new JTextField();
		txtPrenom.setBounds(116, 75, 162, 23);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(118, 110, 160, 24);
		contentPane.add(textNom);
		
		txtCIN = new JTextField();
		txtCIN.setColumns(10);
		txtCIN.setBounds(118, 145, 160, 24);
		contentPane.add(txtCIN);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(116, 181, 162, 24);
		contentPane.add(txtTel);
		
		txtNaissence = new JTextField();
		txtNaissence.setColumns(10);
		txtNaissence.setBounds(116, 227, 162, 24);
		contentPane.add(txtNaissence);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(116, 273, 162, 24);
		contentPane.add(txtAdresse);
		
		JButton btnNewButton = new JButton("Ajouter :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom =txtPrenom.getText().toString();
				String nom =textNom.getText().toString();
				String cin  =txtCIN.getText().toString();
				String tel =txtTel.getText().toString();
				String naissance =txtAdresse.getText().toString();
				String adresse=txtNaissence.getText().toString();
			
				String sql ="insert into etudiant(prenom,nom,cin,Tel,datenaissense,adresse,sexe,felier,image) values(?,?,?,?,?,?,?,?,?) ";
				try {
					InputStream img = new FileInputStream(new File(s));
					if( !prenom.equals("")&&  !nom.equals("")&&  !cin.equals("")&&  !tel.equals("")&&  !naissance.equals("")&&  !adresse.equals("")) {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, prenom);
					pstm.setString(2, nom);
					pstm.setString(3,cin);
					pstm.setString(4, tel);
					pstm.setString(5, naissance);
					pstm.setString(6, adresse);
					pstm.setString(7,comboBox.getSelectedItem().toString() );
					pstm.setString(8,combFelier.getSelectedItem().toString() );
					pstm.setBlob(9, img);
					
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter etudiant ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Etudiant ajouter ");
					UpdateTable();
					txtPrenom.setText("");
					textNom.setText("");
					txtCIN.setText("");
					txtTel.setText("");
					txtAdresse.setText("");
					txtNaissence.setText("");}
					}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
					
				} catch (SQLException | FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(566, 514, 124, 35);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier :");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom =txtPrenom.getText().toString();
				String nom =textNom.getText().toString();
				String cin  =txtCIN.getText().toString();
				String tel =txtTel.getText().toString();
				String naissance =txtAdresse.getText().toString();
				String adresse=txtNaissence.getText().toString();
				String sql ="update etudiant  set prenom =? ,nom=? ,cin=?,Tel=?,datenaissense=?,adresse =? ,sexe=?,felier=?,image=? where id_etudiant ='"+ id +"'";
				try {
					InputStream img = new FileInputStream(new File(s));
					if( !prenom.equals("")&&  !nom.equals("")&&  !cin.equals("")&&  !tel.equals("")&&  !naissance.equals("")&&  !adresse.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1, prenom);
						pstm.setString(2, nom);
						pstm.setString(3,cin);
						pstm.setString(4, tel);
						pstm.setString(5, naissance);
						pstm.setString(6, adresse);
						pstm.setString(8,combFelier.getSelectedItem().toString() );
						pstm.setString(7,comboBox.getSelectedItem().toString() );
						pstm.setBlob(9, img);
						
						
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  felier ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Etudiant Modifier  ");
						UpdateTable();
						txtPrenom.setText("");
						textNom.setText("");
						txtCIN.setText("");
						txtTel.setText("");
						txtAdresse.setText("");
						txtNaissence.setText("");}
						 
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch (SQLException | FileNotFoundException e1) {
					//
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModifier.setBounds(719, 514, 124, 35);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer :");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom =txtPrenom.getText().toString();
				String nom =textNom.getText().toString();
				String cin  =txtCIN.getText().toString();
				String tel =txtTel.getText().toString();
				String naissance =txtAdresse.getText().toString();
				String adresse=txtNaissence.getText().toString();
				String sql ="delete from etudiant  where id_etudiant ='"+id+"'";
				try {
					
					if(!prenom.equals("") && !nom.equals("") && !cin.equals("") && !tel.equals("") && !naissance.equals("") && !adresse.equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  etudiant ", JOptionPane.YES_NO_OPTION);
					  pstm=cn.prepareStatement(sql);
						
					  if (a==0) {pstm.execute();
					JOptionPane.showMessageDialog(null,"Etudient  supprimer");
					UpdateTable();
					txtPrenom.setText("");
					textNom.setText("");
					txtCIN.setText("");
					txtTel.setText("");
					txtAdresse.setText("");
					txtNaissence.setText("");}
					}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSupprimer.setBounds(870, 514, 124, 35);
		contentPane.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Impprimer  :");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Document obj = new Document();
				String sql="select * from etudiant ";
				
				try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					PdfWriter.getInstance(obj,new FileOutputStream("C:\\Users\\Cloud\\Pictures\\java 2019\\GestionEtudient\\Etudiant.pdf") );
					obj.open();
					
					
					Image img =Image.getInstance("C:\\Users\\Cloud\\Pictures\\java 2019\\GestionEtudient\\3.png");
					img.scaleAbsoluteWidth(600);
					img.scaleAbsoluteHeight(92);
					img.setAlignment(img.ALIGN_CENTER);
					obj.add(img);
					obj.add(new Paragraph(" "));
					obj.add(new Paragraph("leste des etudient "));
					obj.add(new Paragraph(" "));
					PdfPTable table  =new PdfPTable(8);
					table.setWidthPercentage(100);
					PdfPCell cell ;
					/////////////////////////////////////////////////////////////////////
					cell=new PdfPCell(new Paragraph("Prenom", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Nom", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("CIN", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Tel", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Date Naissence", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Adresse", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Sexe", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Felier", FontFactory.getFont("Comec Sans  MS ",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					///////////////////////////////////////////////////////////////////////////////////////////
					
					while(rst.next()) { 	cell=new PdfPCell(new Paragraph(rst.getString("prenom"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("nom"), FontFactory.getFont("Arial  ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("cin"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("Tel"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("datenaissense"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("adresse"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell); 
					
					cell=new PdfPCell(new Paragraph(rst.getString("sexe"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell); 
					
					
					cell=new PdfPCell(new Paragraph(rst.getString("felier"), FontFactory.getFont("Arial ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell); 
					
					}
					
				
					obj.add(table);
					obj.close();
					Desktop.getDesktop().open(new File("C:\\Users\\Cloud\\Pictures\\java 2019\\GestionEtudient\\Etudiant.pdf"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnActualiser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualiser.setBounds(1024, 514, 124, 35);
		contentPane.add(btnActualiser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(393, 81, 930, 402);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int  ling = table.getSelectedRow();
			      id =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from etudiant where id_etudiant ='"+id+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						txtPrenom.setText(rst.getString("prenom"));
						textNom.setText(rst.getString("nom"));
						txtCIN.setText(rst.getString("cin"));
						txtTel.setText(rst.getString("Tel"));
						txtNaissence.setText(rst.getString("datenaissense"));
						txtAdresse.setText(rst.getString("adresse"));
					    combFelier.setSelectedItem(rst.getString("felier"));
					    comboBox.setSelectedItem(rst.getString("sexe"));
					    byte[] img = rst.getBytes("image");
					    ImageIcon mayImage =new ImageIcon(img) ;
						java.awt.Image image =mayImage.getImage() ;
						java.awt.Image newimg =image.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH) ;
						ImageIcon Finelmage =new ImageIcon(newimg) ;
						labimage.setIcon(Finelmage);
					  
						 UpdateTable();
						
					}
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			    
			
			}
		
		});
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Actualiser  :");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(1193, 45, 118, 25);
		contentPane.add(button);
		
		JButton btnMenue = new JButton("Retour :");
		btnMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutentificationLycee obj = new AutentificationLycee();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnMenue.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMenue.setBounds(10, 11, 113, 25);
		contentPane.add(btnMenue);
		
		JLabel lblNewLabel_1 = new JLabel("  Table des Etudiant ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(423, 40, 162, 30);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(127, 473, 124, 113);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,1));
		
		 labimage = new JLabel("");
		 panel_1.add(labimage);
		
		JButton btnNewButton_1 = new JButton("Parcourir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filcho = new JFileChooser();
				filcho.setCurrentDirectory(new File("C:\\Users\\Cloud\\Pictures"));
				FileNameExtensionFilter file = new 	FileNameExtensionFilter("IMAGE","jpg","png","gif");
				filcho.addChoosableFileFilter(file);
				int result= filcho.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					File selectedFile = filcho.getSelectedFile();
					String phet=selectedFile.getAbsolutePath();
					ImageIcon mayImage =new ImageIcon(phet) ;
					java.awt.Image img =mayImage.getImage() ;
					java.awt.Image newimg =img.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH) ;
					ImageIcon Finelmage =new ImageIcon(newimg) ;
					labimage.setIcon(Finelmage);
					s=phet;
				}
				else {
					if(result==JFileChooser.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "T'as rien choiser ");
				}
				
				
			}
		});
		btnNewButton_1.setBounds(147, 597, 89, 23);
		contentPane.add(btnNewButton_1);
		
		 comboBox = new JComboBox();
		 comboBox.setModel(new DefaultComboBoxModel(new String[] {"MASC", "FEM"}));
		comboBox.setBounds(116, 325, 162, 24);
		contentPane.add(comboBox);
		
		JLabel lblSexe = new JLabel("  Sexe :");
		lblSexe.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSexe.setBounds(43, 324, 70, 24);
		contentPane.add(lblSexe);
		
		JButton btnFelier = new JButton("felier  :");
		btnFelier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionFelier obj = new GestionFelier();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
				
			}
		});
		btnFelier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFelier.setBounds(138, 11, 113, 25);
		contentPane.add(btnFelier);
	}
	public void UpdateTable() {
		String sql ="select id_etudiant ,prenom as Prenom,nom as Nom ,cin as CIN ,Tel,datenaissense as Date_Nessence ,adresse as Adresse,sexe as Sexe ,felier as Felier from etudiant";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
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
			 combFelier.addItem(nom);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	
	
	
}
}
