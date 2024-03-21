package DirectorInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import GestionInterne.GestionInterne;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GestionEtudiantInterne extends JFrame {

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
	JLabel labimage;
	String id,s ;
	private JTextField txtlit;
	private JTextField txtplacard;
	private JTextField txtdortoire;
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
					GestionEtudiantInterne frame = new GestionEtudiantInterne();
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
	public GestionEtudiantInterne() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("  Prenom :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(33, 43, 84, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNom = new JLabel("    Nom :");
		lblNom.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNom.setBounds(38, 78, 70, 23);
		contentPane.add(lblNom);
		
		JLabel lblCin = new JLabel("     CIN :");
		lblCin.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCin.setBounds(38, 112, 70, 24);
		contentPane.add(lblCin);
		
		JLabel lblNumTel = new JLabel(" Num Tel  :");
		lblNumTel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNumTel.setBounds(33, 147, 75, 20);
		contentPane.add(lblNumTel);
		
		JLabel lblAdresse = new JLabel("  Adresse :");
		lblAdresse.setFont(new Font("Arial", Font.PLAIN, 13));
		lblAdresse.setBounds(57, 300, 70, 20);
		contentPane.add(lblAdresse);
		
		JLabel lblDateNaissense = new JLabel("Date Naissence :");
		lblDateNaissense.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDateNaissense.setBounds(33, 257, 103, 20);
		contentPane.add(lblDateNaissense);
		
		 combFelier = new JComboBox();
		combFelier.setBounds(133, 346, 186, 24);
		contentPane.add(combFelier);
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
		btnNewButton_1.setBounds(301, 204, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblFelier = new JLabel(" fili\u00E8re :");
		lblFelier.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFelier.setBounds(76, 347, 60, 21);
		contentPane.add(lblFelier);
		 remplerComboBox();
		txtPrenom = new JTextField();
		txtPrenom.setBounds(118, 43, 133, 24);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(118, 78, 133, 24);
		contentPane.add(textNom);
		
		txtCIN = new JTextField();
		txtCIN.setColumns(10);
		txtCIN.setBounds(118, 113, 133, 24);
		contentPane.add(txtCIN);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(118, 148, 133, 24);
		contentPane.add(txtTel);
		
		txtNaissence = new JTextField();
		txtNaissence.setColumns(10);
		txtNaissence.setBounds(133, 256, 186, 24);
		contentPane.add(txtNaissence);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(133, 299, 186, 24);
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
				String numerolit =txtlit.getText().toString();
				String numerodortoir=txtdortoire.getText().toString();
				String numeroplacard=txtplacard.getText().toString();
				String sql ="insert into etudiantinterne(prenom,nom,cin,tel,datenaissence,adresse,felier,numlit,numdortoir,numplacard,image) values(?,?,?,?,?,?,?,?,?,?,?) ";
				try {
				InputStream img = new FileInputStream(new File(s));
				if( !prenom.equals("")&&  !nom.equals("")&&  !cin.equals("")&&  !tel.equals("")&& 
							!naissance.equals("")&&  !adresse.equals("")&&  !numerolit.equals("")&& 
							!numerodortoir.equals("")&&  !numeroplacard.equals("")) {
					
						pstm=cn.prepareStatement(sql);
					pstm.setString(1, prenom);
					pstm.setString(2, nom);
					pstm.setString(3,cin);
					pstm.setString(4, tel);
					pstm.setString(5, naissance);
					pstm.setString(6, adresse);
					pstm.setString(7,combFelier.getSelectedItem().toString() );
					pstm.setString(8, numerolit);
					pstm.setString(9, numerodortoir);
					pstm.setString(10, numeroplacard);
					pstm.setBlob(11, img);
					
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
					txtNaissence.setText("");
					txtlit.setText("");
					txtdortoire.setText("");
					txtplacard.setText("");
					}
					}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
					
				} catch (Exception  e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(642, 532, 124, 35);
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
				String numerolit =txtlit.getText().toString();
				String numerodortoir=txtdortoire.getText().toString();
				String numeroplacard=txtplacard.getText().toString();
				String sql ="update etudiantinterne  set prenom =? ,nom=? ,cin=?,tel=?,datenaissence=?,adresse =? ,felier=?,numlit=?,numdortoir=?,numplacard=?,image=? where id_etudiantinterne ='"+ id +"'";
				try {
					InputStream img = new FileInputStream(new File(s));
					if( !prenom.equals("")&&  !nom.equals("")&&  !cin.equals("")&&  !tel.equals("")&& 
							!naissance.equals("")&&  !adresse.equals("")&&  !numerolit.equals("")&& 
							!numerodortoir.equals("")&&  !numeroplacard.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1, prenom);
						pstm.setString(2, nom);
						pstm.setString(3,cin);
						pstm.setString(4, tel);
						pstm.setString(5, naissance);
						pstm.setString(6, adresse);
						pstm.setString(7,combFelier.getSelectedItem().toString() );
						pstm.setString(8, numerolit);
						pstm.setString(9, numerodortoir);
						pstm.setString(10, numeroplacard);
						pstm.setBlob(11, img);
						
						
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  etudiant ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "Etudiant Modifier  ");
						UpdateTable();
						txtPrenom.setText("");
						textNom.setText("");
						txtCIN.setText("");
						txtTel.setText("");
						txtAdresse.setText("");
						txtNaissence.setText("");
						txtlit.setText("");
						txtdortoire.setText("");
						txtplacard.setText("");}
						 
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch ( Exception ex) {
					//
					ex.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModifier.setBounds(799, 532, 124, 35);
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
				String numerolit =txtlit.getText().toString();
				String numerodortoir=txtdortoire.getText().toString();
				String numeroplacard=txtplacard.getText().toString();
				String sql ="delete from etudiantinterne  where id_etudiantinterne ='"+id+"'";
				try {
					
					if(!prenom.equals("")&&  !nom.equals("")&&  !cin.equals("")&&  !tel.equals("")&& 
							!naissance.equals("")&&  !adresse.equals("")&&  !numerolit.equals("")&& 
							!numerodortoir.equals("")&&  !numeroplacard.equals("")) {
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
					txtNaissence.setText("");
					txtlit.setText("");
					txtdortoire.setText("");
					txtplacard.setText("");}
					}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSupprimer.setBounds(968, 532, 124, 35);
		contentPane.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Impprimer  :");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Document obj = new Document();
				String sql="select * from etudiantinterne ";
				
				try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					PdfWriter.getInstance(obj,new FileOutputStream("C:\\Users\\Cloud\\Pictures\\java 2019\\GestionEtudient\\Etudiantinterne.pdf") );
					obj.open();
					
					Image img =Image.getInstance("C:\\Users\\Cloud\\Pictures\\java 2019\\GestionEtudient\\3.png");
					img.scaleAbsoluteWidth(600);
					img.scaleAbsoluteHeight(92);
					img.setAlignment(img.ALIGN_CENTER);
					obj.add(img);
					obj.add(new Paragraph(" "));
					obj.add(new Paragraph("leste des etudient "));
					obj.add(new Paragraph(" "));
					PdfPTable table  =new PdfPTable(10);
					table.setWidthPercentage(100);
					PdfPCell cell ;
					/////////////////////////////////////////////////////////////////////
					cell=new PdfPCell(new Paragraph("Prenom", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Nom", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("CIN", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Tel", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Date Naissence", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Adresse", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Felier", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Numero de lit", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Numero de dortoire", FontFactory.getFont("Comec Sans  MS ",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph("Numero de placard", FontFactory.getFont("Comec Sans  MS ",11)));
				    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					///////////////////////////////////////////////////////////////////////////////////////////
					
					while(rst.next()) { 	cell=new PdfPCell(new Paragraph(rst.getString("prenom"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("nom"), FontFactory.getFont("Arial  ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("cin"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("tel"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("datenaissence"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell=new PdfPCell(new Paragraph(rst.getString("adresse"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell); 
					
					cell=new PdfPCell(new Paragraph(rst.getString("felier"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell); 
					
					
					cell=new PdfPCell(new Paragraph(rst.getString("numlit"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell); 

					cell=new PdfPCell(new Paragraph(rst.getString("numdortoir"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					cell=new PdfPCell(new Paragraph(rst.getString("numplacard"), FontFactory.getFont("Arial ",10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					}
					
				
					obj.add(table);
					obj.close();
					Desktop.getDesktop().open(new File("C:\\Users\\Cloud\\Pictures\\java 2019\\GestionEtudient\\Etudiantinterne.pdf"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnActualiser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualiser.setBounds(1138, 532, 124, 35);
		contentPane.add(btnActualiser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(415, 85, 934, 402);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int  ling = table.getSelectedRow();
			      id =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from etudiantinterne where id_etudiantinterne ='"+id+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						txtPrenom.setText(rst.getString("prenom"));
						textNom.setText(rst.getString("nom"));
						txtCIN.setText(rst.getString("cin"));
						txtTel.setText(rst.getString("tel"));
						txtNaissence.setText(rst.getString("datenaissence"));
						txtAdresse.setText(rst.getString("adresse"));
					    combFelier.setSelectedItem(rst.getString("felier"));
					    txtlit.setText(rst.getString("numlit"));
					    txtdortoire.setText(rst.getString("numdortoir"));
					    txtplacard.setText(rst.getString("numplacard"));
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
		button.setBounds(1217, 54, 118, 25);
		contentPane.add(button);
		
		JButton btnMenue = new JButton("Retoure :");
		btnMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionUserIntern obj = new GestionUserIntern();
				fermer();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
		});
		btnMenue.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMenue.setBounds(57, 617, 113, 25);
		contentPane.add(btnMenue);
		
		JLabel lblNewLabel_1 = new JLabel("  Table des Etudiant  d'internat");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(416, 54, 195, 30);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(278, 85, 124, 113);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,1));
		
		 labimage = new JLabel("");
		 panel_1.add(labimage);
		
		
		
		JLabel lblSexe = new JLabel("Num\u00E9ro lit :");
		lblSexe.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSexe.setBounds(52, 390, 75, 24);
		contentPane.add(lblSexe);
		
		txtlit = new JTextField();
		txtlit.setColumns(10);
		txtlit.setBounds(134, 391, 185, 24);
		contentPane.add(txtlit);
		
		txtplacard = new JTextField();
		txtplacard.setColumns(10);
		txtplacard.setBounds(133, 474, 186, 24);
		contentPane.add(txtplacard);
		
		JLabel lblNumPlacard = new JLabel("Num\u00E9ro placard :");
		lblNumPlacard.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNumPlacard.setBounds(14, 474, 103, 24);
		contentPane.add(lblNumPlacard);
		
		JLabel lblNumDortoire = new JLabel("Num\u00E9ro dortoire :");
		lblNumDortoire.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNumDortoire.setBounds(14, 429, 103, 24);
		contentPane.add(lblNumDortoire);
		
		txtdortoire = new JTextField();
		txtdortoire.setColumns(10);
		txtdortoire.setBounds(133, 430, 186, 24);
		contentPane.add(txtdortoire);
		
		JButton btnChangerMotDe = new JButton("Changer mot de passe  :");
		btnChangerMotDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangerMotePasseDerIna obj = new ChangerMotePasseDerIna();
				fermer();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnChangerMotDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChangerMotDe.setBounds(206, 617, 223, 25);
		contentPane.add(btnChangerMotDe);
	}
	public void UpdateTable() {
		String sql ="select  id_etudiantinterne as Id_Etudiant ,prenom as Prenom,nom as Nom"
				+ " ,cin as CIN ,tel as Tel,datenaissence as Date_Nessence ,adresse as Adresse,felier as Felier,"
				+ "numlit as N_lit,numdortoir as N_dortoire,numplacard as N_palacard from etudiantinterne";
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
