package userManagement;

class UserTree {
	private UserNode root;
	private int size;
	private UserNode currentNode;
	public UserTree(User u) {
		root = new UserNode(u);
		currentNode = root;
		size = 1;
	}
	public User find(User u) {
		UserNode current = root;
		while(!u.equals(current)) {
			if(current == null) {
				return null;
			}
			current = current.compare(u);
		}
		return current.getUser();
	}
	public void add() {
		
	}
	
	public void remove() {
		
	}
}

