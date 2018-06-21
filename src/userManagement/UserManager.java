package userManagement;

import java.io.File;
import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> users;
	
	public UserManager() {
		users = new ArrayList<User>();
		File f = new File("usuarios.txt");
		if(!f.exists()) {
			addUser("admin", "", true);
		}
	}
	public boolean login() {
		
	}
	public boolean addUser(String username, String password, boolean vip) {
		User u = new User(username, password, vip);
		
		users.add(u);
		Thread t = new Thread() {
			@Override
			public void run() {
				File f = new File("usuarios.txt");
			}
		};
		t.start();
		
	}
	public removeUser() {
		
	}
}
