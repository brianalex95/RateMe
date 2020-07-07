package de.swtp.Rateme.model;

import java.sql.Date;

public class User {
	private int userid;
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String street;
	private int streetnr;
	private int zip;
	private String city;
	private String password;
	private Date createdt;
	private Date modifydt;
	private String salt;

	public User() {
		super();
	}

	public User(int userid, String username, String email, String firstname, String lastname, String street,
			int streetnr, int zip, String city, String password, Date createdt, Date modifydt, String salt) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.streetnr = streetnr;
		this.zip = zip;
		this.city = city;
		this.password = password;
		this.createdt = createdt;
		this.modifydt = modifydt;
		this.salt = salt;
	}
	
	public User(int userid, String username, String email, String firstname, String lastname, String street,
			int streetnr, int zip, String city, String password, String salt) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.streetnr = streetnr;
		this.zip = zip;
		this.city = city;
		this.password = password;
		this.salt = salt;
	}

	public User(int userid, String username, String email, String firstname, String lastname, String street,
			int streetnr, int zip, String city, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.streetnr = streetnr;
		this.zip = zip;
		this.city = city;
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public int getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getStreet() {
		return street;
	}

	public int getStreetnr() {
		return streetnr;
	}

	public int getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public String getPassword() {
		return password;
	}

	public Date getCreatedt() {
		return createdt;
	}

	public Date getModifydt() {
		return modifydt;
	}
	
	

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetnr(int streetnr) {
		this.streetnr = streetnr;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
	}

	public void setModifydt(Date modifydt) {
		this.modifydt = modifydt;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", email=" + email + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", street=" + street + ", streetnr=" + streetnr + ", zip=" + zip
				+ ", city=" + city + ", password=" + password + ", createdt=" + createdt + ", modifydt=" + modifydt
				+ ", salt=" + salt + "]";
	}


}
