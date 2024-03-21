package GestionLycee;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.connection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GestionFelier extends JFrame {

	private JPanel contentPane;
	private JTextField txtnomFelier;
	private JTable table;
	Connection cn=null;
	ResultSet rst =null;
	PreparedStatement pstm =null;
	String id ;
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
					GestionFelier frame = new GestionFelier();
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
	public GestionFelier() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400,740);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn= connection.connextion();
		
		JLabel lblNewLabel = new JLabel("Nom fili\u00E8re :");
		lblNewLabel.setBounds(116, 165, 70, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(136, 223, 50, 20);
		contentPane.add(lblType);
		
		txtnomFelier = new JTextField();
		txtnomFelier.setBounds(200, 165, 103, 20);
		contentPane.add(txtnomFelier);
		txtnomFelier.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 196, 862, 320);
		contentPane.add(scrollPane);
		
		table = new JTable();
	
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Actualiser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(1187, 159, 103, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Tablaux des fili\u00E8re");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(550, 165, 187, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(119, 323, 110, 31);
		contentPane.add(btnNewButton_1);
		
		JButton btnModifier = new JButton("Modifier");
		
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setBounds(119, 378, 110, 31);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBounds(119, 453, 110, 31);
		contentPane.add(btnSupprimer);
		
		JComboBox commboBox = new JComboBox();
		commboBox.setModel(new DefaultComboBoxModel(new String[] {"Sientifique", "Technique ", "Litteraire"}));
		commboBox.setToolTipText("");
		commboBox.setEditable(true);
		commboBox.setForeground(Color.BLACK);
		commboBox.setBounds(200, 223, 103, 20);

		contentPane.add(commboBox);
		
		JButton btnNewButton_2 = new JButton("Retoure ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiant obj = new GestionEtudiant();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(26, 55, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 11, 1370, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("fechier");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ouvrire");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Fermer");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("edition");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		mnNewMenu_1.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("New menu");
		mnNewMenu_2.add(mnNewMenu_3);
		
		JButton btnMatier = new JButton("Matier");
		btnMatier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionMatiere obj = new GestionMatiere();
				obj.setVisible(true);
				fermer();
				obj.setLocationRelativeTo(null);
			}
		});
		btnMatier.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMatier.setBounds(136, 56, 89, 23);
		contentPane.add(btnMatier);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int  ling = table.getSelectedRow();
			      id =table.getModel().getValueAt(ling, 0).toString();
			     String sql="select * from felier where id_felier ='"+id+"'";
			     try {
					pstm=cn.prepareStatement(sql);
					rst=pstm.executeQuery();
					if(rst.next()) {
						txtnomFelier.setText(rst.getString("nomfelier"));
						commboBox.setSelectedItem(rst.getString("type"));
						
						 UpdateTable();
						
					}
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			    
				
			}
		});
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomfelier =txtnomFelier.getText().toString();
				String type =commboBox.getSelectedItem().toString();
				
				String sql ="update felier  set nomfelier =? ,type=?  where id_felier ='"+ id +"'";
				try {
					if( !nomfelier.equals("")) {
						pstm=cn.prepareStatement(sql);
						pstm.setString(1, nomfelier);
						pstm.setString(2,type);
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  modifier cet element ", "Modifier  felier ", JOptionPane.YES_NO_OPTION);
						if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "felier Modifier  ");
						UpdateTable();
						txtnomFelier.setText("");}
						
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch (SQLException e1) {
					//
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomfelier =txtnomFelier.getText().toString();
				
				String sql ="delete from felier  where id_felier ='"+ id +"'";
				try {
					if( !nomfelier.equals("")) {
						int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement suppremer cet element ", "suppremer  felier ", JOptionPane.YES_NO_OPTION);
						pstm=cn.prepareStatement(sql);
					if(a==0) {
						pstm.execute();
						JOptionPane.showMessageDialog(null, "felier Supprimer ");
						txtnomFelier.setText("");
					
					}UpdateTable();
						
						
						}else {JOptionPane.showMessageDialog(null, "Remplessez les chemps vide ! "); }
				} catch (SQLException e1) {
					//
					e1.printStackTrace();
				}
				
			}
		});
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String nomfil=txtnomFelier.getText().toString();
			String typefil =commboBox.getSelectedItem().toString();
		  
			String sql ="insert into felier(nomfelier,type) values(?,?)";
			try {
				pstm = cn.prepareStatement(sql);
				pstm.setString(1, nomfil);
				pstm.setString(2, typefil);
				if(!nomfil.equals("")) {
					int a = JOptionPane.showConfirmDialog(null, "voulez-vous vriement  ajouter cet element ", "Ajouter felier ", JOptionPane.YES_NO_OPTION);
					if(a==0) {
					pstm.execute();
				UpdateTable() ;
				txtnomFelier.setText("");
				JOptionPane.showMessageDialog(null, "felier ajouter ");}
				}
				else {JOptionPane.showMessageDialog(null, "Remplessez le chomps vide !");}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	});
}
	public void UpdateTable() {
		String sql ="select id_felier ,nomfelier as Nom_Felier , type  as Type_Felier from felier";
		try {
			pstm=cn.prepareStatement(sql);
			rst=pstm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
}