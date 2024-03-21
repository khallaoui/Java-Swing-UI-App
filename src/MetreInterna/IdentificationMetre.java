package MetreInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Connection.connection;

public class IdentificationMetre extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificationMetre frame = new IdentificationMetre();
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
	public IdentificationMetre() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 203);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				
				String username  =textField.getText().toString();
				String sql ="select password from utilisatermetre where user=?";
				try {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1,username);
					rst=pstm.executeQuery();
					if(rst.next()) {
					String pass =rst.getString("password");
					String passn =pass.substring(0,3);
					textField_1.setText("les 3 premieres lettres du mote de passe sont : "+passn+"********");}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}	
				
				
			}
		});
		textField.setBounds(145, 41, 135, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username  :");
		lblNewLabel.setBounds(73, 41, 75, 21);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.RED);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setEditable(false);
		textField_1.addKeyListener(new KeyAdapter() {
		
			
		});
		textField_1.setBounds(56, 73, 376, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
