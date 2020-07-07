package de.swtp.Rateme.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.swtp.Rateme.model.Rating;

public class RatingDB {
	private static RatingDB instance;

	public static RatingDB getInstance() {
		if (instance == null) {
			instance = new RatingDB();
		}
		return instance;
	}

	private final String createrating = "INSERT INTO rateme_rating(user_id, osm_id, rating_type, grade, txt, image_path) VALUES (?, ?, ?, ?, ?, ?);";
	private final String listbyratingid = "SELECT * FROM rateme_rating WHERE rating_id=?;";
	private final String listbyosmid = "SELECT * FROM rateme_rating WHERE osm_id=?;";
	private final String listbyuserid = "SELECT * FROM rateme_rating WHERE user_id=?;";
	private DBConnection dbConnection = DBConnection.getInstance();

	public int createRating(Rating rating) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(createrating);) {

			pstmt.setInt(1, rating.getUserid());
			pstmt.setLong(2, rating.getOsmid());
			pstmt.setString(3, rating.getRatingtype());
			pstmt.setInt(4, rating.getGrade());
			pstmt.setString(5, rating.getTxt());
			pstmt.setString(6, rating.getImagepath());

			int i = pstmt.executeUpdate();
			return i;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public Rating loadRating(int ratingId) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(listbyratingid);) {
			pstmt.setInt(1, ratingId);

			try (ResultSet rs = pstmt.executeQuery();) {
				Rating rating = null;

				while (rs.next()) {
					if (rating == null) {
						rating = new Rating(rs.getInt(1), rs.getInt(2), rs.getLong(3), rs.getString(4), rs.getInt(5),
								rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9));
					}
				}
				return rating;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Collection<Rating> loadRatingsForPoi(long osmId) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(listbyosmid);) {

			pstmt.setLong(1, osmId);

			try (ResultSet rs = pstmt.executeQuery();) {
				List<Rating> loadedRatings = new ArrayList<>();
				while (rs.next()) {

					Rating rating = new Rating(rs.getInt(1), rs.getInt(2), rs.getLong(3), rs.getString(4), rs.getInt(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9));
					loadedRatings.add(rating);

				}
				return loadedRatings;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Collection<Rating> loadRatingsForUser(int userId) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(listbyuserid);) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery();) {
				List<Rating> loadedRatings = new ArrayList<>();
				while (rs.next()) {
					Rating rating = new Rating(rs.getInt(1), rs.getInt(2), rs.getLong(3), rs.getString(4), rs.getInt(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9));
					loadedRatings.add(rating);

				}
				return loadedRatings;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
