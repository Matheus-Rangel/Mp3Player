package views;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mp3player.PlayerManager;
import userManagement.UserManager;
public class App {
	
	static UserManager userManager = new UserManager();
	static PlayerManager playerManager = new PlayerManager();
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		loginScreen();
	}
	
	static void loginScreen() {
		LoginViews loginViews = new LoginViews(userManager);
		loginViews.setVisible(true);
		loginViews.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				playerScreen();
			}
		});
	}
	static void playerScreen() {
		PlayerViews playerViews = new PlayerViews(playerManager, userManager);
		playerViews.setVisible(true);
		playerViews.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				loginScreen();
			}
		});
	}
}
