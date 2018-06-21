package views;
import mp3player.PlayerManager;
import userManagement.UserManager;
public class App {
	
	static UserManager userManager = new UserManager();
	static PlayerManager playerManager = new PlayerManager();
	
	public static void main(String[] args) {
		LoginViews loginViews = new LoginViews();
		loginViews.setVisible(true);
	}
	
	void LoginScreen() {
		
	}
	void playerScreen() {
		
	}
}
