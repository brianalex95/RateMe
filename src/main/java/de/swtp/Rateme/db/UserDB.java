package de.swtp.Rateme.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.swtp.Rateme.model.User;

public class UserDB {
	private UserDB() {
	}

	private static UserDB instance;

	public static UserDB getInstance() {
		if (instance == null) {
			instance = new UserDB();
		}
		return instance;
	}

	private final String createuser = "INSERT INTO rateme_user(username, E_Mail, firstname, lastname, street, streetNr, zip, city, password, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String listuserid = "SELECT * FROM rateme_user WHERE user_id=?;";
	private final String listusername = "SELECT * FROM rateme_user WHERE username=?;";
	private final String passwordcheck = "SELECT username,password FROM rateme_user WHERE username=?;";
	private DBConnection dbConnection = DBConnection.getInstance();

	public int createUser(User user) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(createuser);) {

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getFirstname());
			pstmt.setString(4, user.getLastname());
			pstmt.setString(5, user.getStreet());
			pstmt.setInt(6, user.getStreetnr());
			pstmt.setInt(7, user.getZip());
			pstmt.setString(8, user.getCity());
			pstmt.setString(9, user.getPassword());
			pstmt.setString(10, user.getSalt());

			int i = pstmt.executeUpdate();
			return i;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public User loadUser(int userid) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(listuserid);) {
			pstmt.setInt(1, userid);
			try (ResultSet rs = pstmt.executeQuery();) {
				User user = null;

				while (rs.next()) {
					if (user == null) {
						user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9),
								rs.getString(10), rs.getDate(11), rs.getDate(12), rs.getString(13));
					}
				}
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User loadUser(String username) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(listusername);) {
			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery();) {
				User user = null;

				while (rs.next()) {
					if (user == null) {
						user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9),
								rs.getString(10), rs.getDate(11), rs.getDate(12), rs.getString(13));
					}
				}
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean validatePassword(String username, String password) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(passwordcheck);) {
			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery();) {
				boolean check = false;
				while (rs.next()) {
					check = rs.getString(2).contentEquals(password);
				}
				return check;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
