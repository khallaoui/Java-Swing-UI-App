package GestionAdmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.LineBorder;

import Connection.connection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;

public class GestionUsers extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String usernam,usernamex;
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
	private JLabel label;
	private JLabel label_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JTable table_3;
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
					GestionUsers frame = new GestionUsers();
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
	public GestionUsers() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String sql="select password from utilisatereinterne where user=?";
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
		textField.setBounds(240, 68, 150, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(240, 112, 150, 23);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(135, 112, 95, 23);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(135, 66, 95, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql = "INSERT INTO utilisatereinterne (user,password) values(?,?)";
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
					UpdateTableInterne();}}
					else {
						JOptionPane.showMessageDialog(null, "Remplesez les chemps vide ! ");	
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton.setBounds(10, 192, 126, 30);
		contentPane.add(btnNewButton);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="delete from utilisatereinterne where user=? and password=?";
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
					UpdateTableInterne();}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setFont(new Font("Arial", Font.BOLD, 15));
		btnSupprimer.setBounds(321, 192, 126, 30);
		contentPane.add(btnSupprimer);
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="update utilisatereinterne set user=? ,password=? where user='"+usernam +"'";
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
					}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
					//
					e1.printStackTrace();
				}
				
			}
		});
		btnModifier.setFont(new Font("Arial", Font.BOLD, 15));
		btnModifier.setBounds(165, 192, 126, 30);
		contentPane.add(btnModifier);
		
		btnNewButton_1 = new JButton("Actualiser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateTableInterne();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(1053, 9, 105, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Menue");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AutentificationAdmin obj = new AutentificationAdmin();
				obj.setVisible(true);
                  fermer();
				obj.setLocationRelativeTo(null);
			
			}
		});
		btnNewButton_2.setBounds(10, 31, 126, 30);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("   la leste des username interne :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(514, 18, 213, 14);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		scrollPane.setBounds(508, 43, 677, 240);
		contentPane.add(scrollPane);
		
		/*table  = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		scrollPane.setViewportView(table_1);*/
		
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
		
		label = new JLabel("Username :");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(135, 393, 95, 23);
		contentPane.add(label);
		
		label_1 = new JLabel("password :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(135, 452, 95, 23);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(240, 393, 150, 23);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(240, 454, 150, 23);
		contentPane.add(textField_3);
		
		button = new JButton("Ajouter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "INSERT INTO utilisaterexterne (user,password) values(?,?)";
				try {
					pstm=cn.prepareStatement(sql);	
					pstm.setString(1,textField_2.getText().toString() );
					pstm.setString(2,textField_3.getText().toString() );
					if(!textField_2.getText().equals("")&& !textField_3.getText().equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter usernam ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
					JOptionPane.showMessageDialog(null, "User  ajouter ");
					textField_2.setText("");
					textField_3.setText("");
					UpdateTableExterne();}}
					else {
						JOptionPane.showMessageDialog(null, "Remplesez les chemps vide ! ");	
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBounds(10, 544, 126, 30);
		contentPane.add(button);
		
		button_1 = new JButton("Modifier");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="update utilisaterexterne set user=? ,password=? where user='"+usernamex +"'";
				try {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, textField_2.getText().toString());
					pstm.setString(2, textField_3.getText().toString());
					if(!textField_2.getText().equals("") && !textField_3.getText().equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  usernam ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
					JOptionPane.showMessageDialog(null, "la modifier ruessit");
					 textField_2.setText("");
					 textField_3.setText("");
					 UpdateTableExterne();}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
					//
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 15));
		button_1.setBounds(165, 544, 126, 30);
		contentPane.add(button_1);
		
		button_2 = new JButton("Supprimer");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="delete from utilisaterexterne where user=? and password=?";
				try {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1, textField_2.getText().toString());
					pstm.setString(2, textField_3.getText().toString());
					if(!textField_2.getText().equals("") && !textField_3.getText().equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  usernam ", JOptionPane.YES_NO_OPTION);
						
						if (a==0) {
						pstm.execute();
					JOptionPane.showMessageDialog(null,"User supprimer");
					textField_3.setText("");
					textField_2.setText("");
					UpdateTableExterne();}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		button_2.setFont(new Font("Arial", Font.BOLD, 15));
		button_2.setBounds(321, 544, 126, 30);
		contentPane.add(button_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(508, 401, 677, 240);
		contentPane.add(scrollPane_1);
		
		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int  ling = table_3.getSelectedRow();
				usernamex =table_3.getModel().getValueAt(ling, 0).toString();
				String pass=table_3.getModel().getValueAt(ling, 1).toString();
				textField_2.setText(usernamex);
				textField_3.setText(pass);
			}
		});
		scrollPane_1.setViewportView(table_3);
		
		JLabel lblLaLesteDes = new JLabel("   la leste des username externe  :");
		lblLaLesteDes.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblLaLesteDes.setBounds(503, 376, 224, 14);
		contentPane.add(lblLaLesteDes);
		
		JButton button_3 = new JButton("Actualiser");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTableExterne() ;
			}
		});
		button_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_3.setBounds(1071, 360, 105, 30);
		contentPane.add(button_3);
		
		JButton btnNewButton_3 = new JButton("Modifier lieur mote de passe ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChnagerMotePasseAdmin obj = new ChnagerMotePasseAdmin();
				fermer();
				obj.setVisible(true);
                  fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton_3.setBounds(65, 277, 241, 30);
		contentPane.add(btnNewButton_3);
		
	
		
	}
	public void UpdateTableExterne() {
		String sql ="select user as Username ,password as Password from utilisaterexterne";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			table_3.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public void UpdateTableInterne() {
		String sql ="select user as Username ,password as Password from utilisatereinterne";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
}
