package GestionLycee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.connection;
import Interface.Interface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class AutentificationLycee extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	private JLabel label_1;
	private JPasswordField textField_1;
	private JButton btnRetour;
	private JButton btnQuiter;
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
					AutentificationLycee frame = new AutentificationLycee();
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
	public AutentificationLycee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
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
		
		JButton btnNewButton = new JButton("Connecter :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user =textField.getText().toString();
				String password =textField_1.getText().toString();
				String sql="select user ,password from utilisaterexterne ";
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
					if(use.equals(user) &&  pas.equals(password) )
						
					if(use.equals(user) &&  pas.equals(password) ) {
						
						JOptionPane.showMessageDialog(null,"Connection reussi :" );
						i=1;
						GestionEtudiant obj = new GestionEtudiant();
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
		btnNewButton.setBounds(330, 195, 107, 35);
		contentPane.add(btnNewButton);
		
		label_1 = new JLabel("Mot de pass oublier ");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Indicationpass obj = new Indicationpass();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
		});
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 9));
		label_1.setBounds(266, 98, 100, 14);
		contentPane.add(label_1);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(183, 114, 113, 23);
		contentPane.add(textField_1);
		
		btnRetour = new JButton("Retour :");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface obj = new Interface();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnRetour.setBounds(189, 195, 107, 35);
		contentPane.add(btnRetour);
		
		btnQuiter = new JButton("Quiter :");
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
			}
		});
		btnQuiter.setBounds(38, 195, 107, 35);
		contentPane.add(btnQuiter);
	}

}
