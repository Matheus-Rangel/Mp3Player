package userManagement;

class User {
	private String username;
	private String password;
	private boolean vip;
	private int id;
	static int currentID = 1;

	public User(String username, String password, boolean vip) {
		super();
		this.vip = vip;
		this.username = username;
		this.password = password;
		this.id = currentID;
		currentID++;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}
	
	public boolean getVip() {
		return vip;
	}
	@Override

	public String toString() {
		String s = vip ? "Vip: yes\r\n" : "Vip: no\r\n";
		s = s + username + ":" + password;
		return s;
	}

	@Override
	public boolean equals(Object u) {
		try {
			return username.equals(((User)u).getUsername());
		} catch (ClassCastException e) {
			return false;
		}
	}
}
