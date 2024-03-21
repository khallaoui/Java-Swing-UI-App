package DirectorInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Connection.connection;

public class ChangerMotePasseDerIna extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
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
					ChangerMotePasseDerIna frame = new ChangerMotePasseDerIna();
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
	public ChangerMotePasseDerIna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("L 'encien username :");
		lblNewLabel.setBounds(20, 109, 141, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(195, 109, 188, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblLencienPassword = new JLabel("L 'encien password :");
		lblLencienPassword.setBounds(20, 183, 141, 29);
		contentPane.add(lblLencienPassword);
		
		JLabel lblNevau = new JLabel("Nouvelle username :");
		lblNevau.setBounds(20, 257, 141, 29);
		contentPane.add(lblNevau);
		
		JLabel lblNouvellePassword = new JLabel("Nouvelle password :");
		lblNouvellePassword.setBounds(20, 313, 141, 29);
		contentPane.add(lblNouvellePassword);
		
		JButton btnNewButton = new JButton("Confirmer :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userencien =textField.getText().toString();
				String passencien =textField_1.getText().toString();
				String usernouve =textField_2.getText().toString();
				String passnouve =textField_3.getText().toString();
				String sql ="update utilisatereinterne set user=? ,password=? where user='"+userencien+"' and password ='"+passencien+"' ";
				try {
					pstm=cn.prepareStatement(sql);
					pstm.setString(1,usernouve);
					pstm.setString(2, passnouve);
					if(!userencien.equals("") && !passencien.equals("") && !usernouve.equals("") && !passnouve.equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  usernam ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
					JOptionPane.showMessageDialog(null, "la modifier ruessit");
					 textField_2.setText("");
					 textField_3.setText("");
					 textField.setText("");
					 textField_1.setText("");
					}}
					else {JOptionPane.showMessageDialog(null, "remplez les chemps vide !");}
				} catch (SQLException e1) {
					//
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(248, 414, 150, 29);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(195, 183, 188, 29);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(195, 257, 188, 29);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(195, 317, 188, 29);
		contentPane.add(textField_3);
		
		JButton btnRetoure = new JButton("Retoure :");
		btnRetoure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiantInterne obj = new GestionEtudiantInterne();
				fermer();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnRetoure.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRetoure.setBounds(50, 414, 150, 29);
		contentPane.add(btnRetoure);
	}
}

