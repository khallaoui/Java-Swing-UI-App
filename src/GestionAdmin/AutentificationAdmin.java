package GestionAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Connection.connection;

import Interface.Interface;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class AutentificationAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	private JPasswordField textField_1;
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
					AutentificationAdmin frame = new AutentificationAdmin();
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
	public AutentificationAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setBounds(56, 72, 89, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(183, 72, 113, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(56, 122, 89, 23);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Connection :");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user =textField.getText().toString();
				String password =textField_1.getText().toString();
				String sql="select user ,password from utilisater ";
				try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
				 int 	i=0;
				 if ( user.equals("")||password.equals("")) {
					 JOptionPane.showMessageDialog(null,"Remplessez les chomps vide !" );
				 }else {
					while(rst.next()) {
						
					String use  =rst.getString("user")	;
					String pas  =rst.getString("password")	;
					
						
					if(use.equals(user) &&  pas.equals(password) ) {
						
						JOptionPane.showMessageDialog(null,"Connection reussi :" );
						i=1;
						GestionUsers obj = new GestionUsers();
						obj.setVisible(true);
						fermer();
						obj.setLocationRelativeTo(null);
					}
					}
				if (i==0) {
					JOptionPane.showMessageDialog(null,"Connection echoui , information incorect  :" );
				}
				 } 
				 
				} catch (Exception ex) {
					
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(330, 195, 130, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("mot de passe oublier ");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IdentificationAdmin obj = new IdentificationAdmin();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(264, 98, 145, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(183, 120, 113, 23);
		contentPane.add(textField_1);
		
		JButton btnRetoure = new JButton("Retoure :");
		btnRetoure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface  obj = new Interface();
				obj.setVisible(true);
                fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnRetoure.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetoure.setBounds(164, 195, 130, 35);
		contentPane.add(btnRetoure);
		
		JButton btnQuiter = new JButton("Quiter  :");
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
			}
		});
		btnQuiter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuiter.setBounds(24, 195, 130, 35);
		contentPane.add(btnQuiter);
	}
}
