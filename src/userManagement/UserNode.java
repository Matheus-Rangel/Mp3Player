package userManagement;

class UserNode {
	private UserNode left;
	private UserNode right;
	private User user;
	
	public UserNode(User user){
		this.user = user;
		left = null;
		right = null;
	}
	
	public UserNode compare(User user) {
		if(user.getId() < this.user.getId()) {
			return left;
		}else if(user.getId() > this.user.getId()){
			return right;
		}else
			return this;
	}
	
	public User getUser() {
		return this.user;
	}
}
