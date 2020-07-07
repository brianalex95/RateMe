package de.swtp.Rateme.api.security;

import java.util.Random;

import de.swtp.Rateme.model.User;

public class AccountManager {

	public AccountManager() {
		super();
	}
	
	
	public User hashpassword(User user) {
		String salt = randomnumber();
		String temp = user.getPassword() + salt; 
		int hash = temp.hashCode();
		temp = Integer.toString(hash);
		User saltuser = user;
		saltuser.setPassword(temp);
		saltuser.setSalt(salt);
		return saltuser;
		
	}
	
	public User checkhash(User user, String hashvalue) {
		String  hashpassword = user.getPassword() + hashvalue;
		int hash = hashpassword.hashCode();
		hashpassword = Integer.toString(hash);
		User hshuser = user;
		hshuser.setPassword(hashpassword);
		return hshuser;
	}
	
	public String randomnumber() {
		int length = 5;
		Random rand = new Random();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = chars.charAt(rand.nextInt(chars.length()));
	    }
	    return new String(text);
	}
}
