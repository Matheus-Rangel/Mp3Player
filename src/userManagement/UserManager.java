package userManagement;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class UserManager {
	private ArrayList<User> users;
	private User currentUser;
	
	public UserManager() {
		this.users = new ArrayList<User>();
		File f = new File("usuarios.txt");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addUser("admin", "", true);
		}
		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(bis));
			String username, password, line, vip;
			while(br.ready()) {
				vip = br.readLine();
				line = br.readLine();
				username = line.substring(0, line.indexOf(":"));
				password = line.substring(line.indexOf(":") + 1);
				users.add(new User(username, password, vip.equals("Vip: yes")));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public boolean login(String username, String password) {
		User u = findUser(username);
		if(u != null) {
			if(u.getPassword().equals(password)) {
				this.currentUser = u;
				return true;
			}
		}
		return false;
	}
	
	public boolean addUser(String username, String password, boolean vip) {
		User u = new User(username, password, vip);
		if(users.contains(u)) {
			return false;
		}
		users.add(u);
		try {
			File f = new File("usuarios.txt");
			FileWriter fw = new FileWriter(f, true);
			System.out.println(u.toString());
			fw.write(u.toString() + "\r\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private User findUser(String username) {
		for (User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
}
