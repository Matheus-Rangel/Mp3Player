package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginViews extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnLogin;
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblMpPlayer = new JLabel("MP3 Player ");
		lblMpPlayer.setBounds(114, 18, 93, 22);
		lblMpPlayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(17, 74, 56, 16);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(78, 71, 197, 22);
		textUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(17, 109, 56, 16);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(78, 106, 197, 22);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(178, 164, 97, 25);
		
		contentPane.setLayout(null);
		contentPane.add(btnLogin);
		contentPane.add(lblMpPlayer);
		contentPane.add(lblUsuario);
		contentPane.add(textUsuario);
		contentPane.add(lblSenha);
		contentPane.add(passwordField);
	
	
	}

	private void createEvents() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textUsuario.getText();
				passwordField.getPassword();
				
			}
		});
	}
	

	public LoginViews() {
		initComponents();
		createEvents();
	}
}
