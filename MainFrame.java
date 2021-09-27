import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField edtName;
	private JTextField edtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(153, 11, 80, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(33, 90, 91, 36);
		contentPane.add(lblNewLabel_1);
		
		edtName = new JTextField();
		edtName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		edtName.setBounds(166, 90, 175, 36);
		contentPane.add(edtName);
		edtName.setColumns(10);
		
		edtPassword = new JTextField();
		edtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		edtPassword.setBounds(166, 150, 175, 36);
		contentPane.add(edtPassword);
		edtPassword.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(33, 150, 71, 36);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/database1",
	                        "root", "Passdata@26");
					
				String username = edtName.getText();
				String password = edtPassword.getText();
				
				Statement stm = con.createStatement();
				
				String sql ="select * from login where name='"+username+"' and password='"+password+"'";
				ResultSet rs = stm.executeQuery(sql);
				
				if (rs.next()) {
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
					edtName.setText("");
					edtPassword.setText("");
					
				}
				
				con.close();		
				
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(140, 214, 80, 36);
		contentPane.add(btnNewButton);
	}
}
