package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userManagement.UserManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField PasswordFieldConfirm;
	private UserManager userManager;

	
	public AddUserDialog(UserManager u) {
		this.userManager = u;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAddUser = new JLabel("Add User");
		lblAddUser.setBounds(182, 12, 67, 21);
		contentPanel.add(lblAddUser);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 54, 78, 21);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(72, 101, 61, 21);
		contentPanel.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(89, 52, 301, 25);
		contentPanel.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 99, 200, 25);
		contentPanel.add(passwordField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(24, 134, 109, 21);
		contentPanel.add(lblConfirmPassword);
		
		PasswordFieldConfirm = new JPasswordField();
		PasswordFieldConfirm.setBounds(142, 132, 200, 25);
		contentPanel.add(PasswordFieldConfirm);
		
		JCheckBox chckbxVip = new JCheckBox("Vip");
		chckbxVip.setBounds(262, 165, 125, 29);
		contentPanel.add(chckbxVip);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(new String(passwordField.getPassword()).equals(new String(PasswordFieldConfirm.getPassword()))) {
							if(userManager.addUser(textUsername.getText(), new String(passwordField.getPassword()), chckbxVip.isSelected())) {
								setVisible(false);
							}else {
								lblUsername.setForeground(new Color(255, 0, 0));
								System.err.println("AddUser: Nome de Usuario ja esta em uso.");
							}
						}
						else {
							lblConfirmPassword.setForeground(new Color(255, 0, 0));
							System.err.println("AddUser: Por favor confirme a senha.");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
