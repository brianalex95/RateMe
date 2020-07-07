package de.swtp.Rateme.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import de.swtp.Rateme.db.UserDB;

@Singleton
public class UserManager {
	private Map<String, User> users = new HashMap<>();
	
	private UserDB userdb;

	public UserManager() {
		super();
	}

	public Optional<User> lookupUser(String username) {
		System.out.println(username);
		User loaduser = userdb.loadUser(username);
		System.out.println(loaduser);
		
		return Optional.ofNullable(loaduser);
	}

	public void register(User user) {
		if (this.lookupUser(user.getUsername()).isPresent()) {
			RuntimeException exce = new RuntimeException("User " + user + " exists");
			throw exce;
		} else {
			String username = user.getUsername();
			this.users.put(username, user);
			return;
		}
	}
}
