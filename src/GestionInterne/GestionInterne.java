package GestionInterne;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DirectorInterna.*;
import Interface.Interface;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import MetreInterna.*;

public class GestionInterne extends JFrame {

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
					GestionInterne frame = new GestionInterne();
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
	public GestionInterne() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800,550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("M\u00E8tre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   MetreInterna	obj = new MetreInterna();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnNewButton.setBounds(430, 97, 269, 145);
		contentPane.add(btnNewButton);
		
		JButton btnDerectore = new JButton("Director");
		btnDerectore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDirector obj = new UserDirector();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnDerectore.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnDerectore.setBounds(71, 97, 269, 145);
		contentPane.add(btnDerectore);
		
		JButton btnNewButton_1 = new JButton("Retoure");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface	obj = new Interface();
					obj.setVisible(true);
					fermer();
					obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setBounds(508, 336, 96, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnQuiter = new JButton("Quiter ");
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
			}
		});
		btnQuiter.setBounds(148, 336, 96, 29);
		contentPane.add(btnQuiter);
	}
}
