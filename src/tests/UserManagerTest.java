package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import userManagement.UserManager;

class UserManagerTest {
	private static UserManager u;
	@BeforeAll
	static void begin() {
		u = new UserManager();
	}
	@Test
	void addSameUser() {
		assert(u.addUser("admin", "", true));
		assert(!u.addUser("admin", "1q34", false));
	}
	@Test
	void loginTest() {
		assert(!u.login("teste", ""));
		assert(!u.login("admin", "1234"));
		assert(u.login("admin", ""));
		assert(u.getUsername().equals("admin"));
		assert(u.getVip());
	}
}
