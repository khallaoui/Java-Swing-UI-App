package MetreInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import GestionInterne.GestionInterne;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class MetreInterna extends JFrame {

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
					MetreInterna frame = new MetreInterna();
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
	public MetreInterna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setBounds(64, 80, 81, 19);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(155, 79, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(64, 137, 81, 19);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Connection :");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user =textField.getText().toString();
				String password =textField_1.getText().toString();
				String sql="select user ,password from utilisatermetre ";
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
				   GestionAbsenceInterne obj = new GestionAbsenceInterne();
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
		btnNewButton.setBounds(319, 194, 115, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblMoteDePasse = new JLabel("mote de passe oublier ");
		lblMoteDePasse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IdentificationMetre obj = new IdentificationMetre();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
			
			
		});
		lblMoteDePasse.setForeground(Color.BLUE);
		lblMoteDePasse.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblMoteDePasse.setBounds(238, 110, 115, 14);
		contentPane.add(lblMoteDePasse);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(155, 136, 107, 19);
		contentPane.add(textField_1);
		
		JButton btnRetoure = new JButton("Retoure  :");
		btnRetoure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionInterne obj = new GestionInterne();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnRetoure.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetoure.setBounds(181, 194, 115, 23);
		contentPane.add(btnRetoure);
		
		JButton btnQuiter = new JButton("Quiter  :");
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
			}
		});
		btnQuiter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuiter.setBounds(44, 194, 115, 23);
		contentPane.add(btnQuiter);
		
	}
}

