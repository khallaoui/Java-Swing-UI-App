package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import GestionInterne.*;
import GestionLycee.*;
import GestionAdmin.*;


public class Interface extends JFrame {

	private JPanel contentPane;
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
					Interface frame = new Interface();
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
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000,650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Interna");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GestionInterne obj = new GestionInterne();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton.setBounds(50, 150, 262, 170);
		contentPane.add(btnNewButton);
		
		JButton btnLycee = new JButton("Lycee");
		btnLycee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutentificationLycee obj = new AutentificationLycee();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
				
			}
		});
		btnLycee.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnLycee.setBounds(377, 150, 262, 170);
		contentPane.add(btnLycee);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutentificationAdmin obj = new AutentificationAdmin();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnAdmin.setBounds(694, 150, 262, 170);
		contentPane.add(btnAdmin);
	}
}
