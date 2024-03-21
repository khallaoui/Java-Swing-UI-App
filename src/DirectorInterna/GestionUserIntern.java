package DirectorInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Connection.connection;
import GestionInterne.GestionInterne;
import net.proteanit.sql.DbUtils;

public class GestionUserIntern extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String usernam = null;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JButton btnEtudiant;
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
					GestionUserIntern frame = new GestionUserIntern();
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
	public GestionUserIntern() {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(0, 0, 1400,740);
				contentPane = new JPanel();
				contentPane.setBackground(new Color(192, 192, 192));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				cn= connection.connextion();
				
				textField = new JTextField();
				textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						String sql="select password from utilisatermetre where user=?";
						try {
							pstm=cn.prepareStatement(sql);
							pstm.setString(1, textField.getText().toString());
							rst=pstm.executeQuery();
							if(rst.next()) {
							String passwor = rst.getString("password");
							textField_1.setText(passwor);}
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
					}
				});
				textField.setBounds(240, 212, 150, 30);
				contentPane.add(textField);
				textField.setColumns(10);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(240, 287, 150, 30);
				contentPane.add(textField_1);
				
				JLabel lblPassword = new JLabel("password :");
				lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblPassword.setBounds(117, 285, 95, 30);
				contentPane.add(lblPassword);
				
				JLabel lblNewLabel = new JLabel("Username :");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel.setBounds(117, 212, 95, 30);
				contentPane.add(lblNewLabel);
				
				JButton btnNewButton = new JButton("Ajouter");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String sql = "INSERT INTO utilisatermetre (user,password) values(?,?)";
						try {
							pstm=cn.prepareStatement(sql);	
							pstm.setString(1,textField.getText().toString() );
							pstm.setString(2,textField_1.getText().toString() );
							if(!textField.getText().equals("")&& !textField.getText().equals("")) {
								int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter usernam ", JOptionPane.YES_NO_OPTION);
								if(a==0) {
								pstm.execute();
							JOptionPane.showMessageDialog(null, "User  ajouter ");
							textField.setText("");
							textField_1.setText("");
							UpdateTable();}}
							else {
								JOptionPane.showMessageDialog(null, "Remplesez les chemps vide ! ");	
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						 
					}
				});
				btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
				btnNewButton.setBounds(40, 374, 126, 30);
				contentPane.add(btnNewButton);
				
				btnSupprimer = new JButton("Supprimer");
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sql ="delete from utilisatermetre where user=? and password=?";
						try {
							pstm=cn.prepareStatement(sql);
							pstm.setString(1, textField.getText().toString());
							pstm.setString(2, textField_1.getText().toString());
							if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
								int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  usernam ", JOptionPane.YES_NO_OPTION);
								
								if (a==0) {
								pstm.execute();
							JOptionPane.showMessageDialog(null,"User supprimer");
							textField.setText("");
							textField_1.setText("");
							UpdateTable();}}
							else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						
					}
				});
				btnSupprimer.setFont(new Font("Arial", Font.BOLD, 15));
				btnSupprimer.setBounds(325, 374, 126, 30);
				contentPane.add(btnSupprimer);
				
				btnModifier = new JButton("Modifier");
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sql ="update utilisatermetre set user=? ,password=? where user='"+usernam +"'";
						try {
							pstm=cn.prepareStatement(sql);
							pstm.setString(1, textField.getText().toString());
							pstm.setString(2, textField_1.getText().toString());
							if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
								int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  usernam ", JOptionPane.YES_NO_OPTION);
								if(a==0) {
								pstm.execute();
							JOptionPane.showMessageDialog(null, "la modifier ruessit");
							 textField_1.setText("");
							 textField.setText("");
							 UpdateTable();}}
							else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
						} catch (SQLException e1) {
							//
							e1.printStackTrace();
						}
						
					}
				});
				btnModifier.setFont(new Font("Arial", Font.BOLD, 15));
				btnModifier.setBounds(176, 374, 126, 30);
				contentPane.add(btnModifier);
				
				btnNewButton_1 = new JButton("Actualiser");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						UpdateTable();
					}
				});
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnNewButton_1.setBounds(1130, 194, 105, 30);
				contentPane.add(btnNewButton_1);
				
				btnNewButton_2 = new JButton("retoure :");
				btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						UserDirector obj = new UserDirector();
						fermer();
						obj.setVisible(true);
						obj.setLocationRelativeTo(null);
						
					
					}
				});
				btnNewButton_2.setBounds(10, 31, 126, 30);
				contentPane.add(btnNewButton_2);
				
				JLabel lblNewLabel_2 = new JLabel("   la leste des username :");
				lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(580, 212, 182, 14);
				contentPane.add(lblNewLabel_2);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
					}
				});
				scrollPane.setBounds(580, 235, 679, 360);
				contentPane.add(scrollPane);
				
				table_2 = new JTable();
				table_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int  ling = table_2.getSelectedRow();
						usernam =table_2.getModel().getValueAt(ling, 0).toString();
						String pass=table_2.getModel().getValueAt(ling, 1).toString();
						textField.setText(usernam);
						textField_1.setText(pass);
					}
				});
				scrollPane.setViewportView(table_2);
				
				btnEtudiant = new JButton("Etudiant  :");
				btnEtudiant.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestionEtudiantInterne obj = new GestionEtudiantInterne();
						fermer();
						obj.setVisible(true);
						obj.setLocationRelativeTo(null);
						
					}
				});
				btnEtudiant.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnEtudiant.setBounds(160, 31, 126, 30);
				contentPane.add(btnEtudiant);
				
			
				
			}
			public void UpdateTable() {
				String sql ="select user as Username ,password as Password from utilisatermetre";
				try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
