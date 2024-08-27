package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wg.Constants.UserConstants;
import com.wg.Model.Role;
import com.wg.Model.User;
import com.wg.config.DatabaseConnection;

public class UserDAO {
	private Connection connection;

	public UserDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addUser(User user) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO User (UserId, username, name, age, password, email, role, dob, contactNumber, standard, gender, rollNo, mentorOf) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getName());
			stmt.setInt(4, user.getAge());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getRole().name()); // Assuming Role is an enum
			stmt.setDate(8, java.sql.Date.valueOf(user.getDOB())); // Convert LocalDate to SQL Date
			stmt.setString(9, user.getContactNumber());
			stmt.setInt(10, user.getStandard());
			stmt.setString(11, user.getGender());
			stmt.setString(12, user.getRollNo());
			stmt.setInt(13, user.getMentorOf());

			stmt.executeUpdate();
		}
	}

	public User getUserById(String userId) throws SQLException, ClassNotFoundException {
		User user = new User(); // Create a new User instance
		String query = "SELECT * FROM User WHERE UserId = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Set all fields using setters
					user.setUserId(rs.getString("UserId"));
					user.setUsername(rs.getString("username"));
					user.setName(rs.getString("name"));
					user.setAge(rs.getInt("age"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setRole(Role.valueOf(rs.getString("role"))); // Ensure Role is correctly mapped
					user.setDOB(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
					user.setStandard(rs.getInt("standard"));
					user.setGender(rs.getString("gender"));
					user.setRollNo(rs.getString("rollNo"));
					user.setContactNumber(rs.getString("contactNumber"));
					user.setAddress(rs.getString("address"));
					user.setMentorOf(rs.getInt("mentorOf"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error retrieving user by ID", e);
		}

		return user;
	}

	public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
		User user = new User(); // Create a new User instance
		String query = "SELECT * FROM User WHERE username = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Set all fields using setters
					user.setUserId(rs.getString("UserId"));
					user.setUsername(rs.getString("username"));
					user.setName(rs.getString("name"));
					user.setAge(rs.getInt("age"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setRole(Role.valueOf(rs.getString("role"))); // Ensure Role is correctly mapped
					user.setDOB(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
					user.setStandard(rs.getInt("standard"));
					user.setGender(rs.getString("gender"));
					user.setRollNo(rs.getString("rollNo"));
					user.setContactNumber(rs.getString("contactNumber"));
					user.setAddress(rs.getString("address"));
					user.setMentorOf(rs.getInt("mentorOf"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error retrieving user by username", e);
		}

		return user;
	}

	public List<User> getClassDetails(int standard) throws SQLException, ClassNotFoundException {
		List<User> list = new ArrayList<>();
		String query = "SELECT * FROM User WHERE standard = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, standard);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					User user = new User(); // Create a new User instance

					// Set all fields using setters
					user.setUserId(rs.getString("UserId"));
					user.setName(rs.getString("name"));
					user.setUsername(rs.getString("username"));
					user.setAge(rs.getInt("age"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setRole(Role.valueOf(rs.getString("role"))); // Ensure Role is correctly mapped
					user.setDOB(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
					user.setStandard(rs.getInt("standard"));
					user.setGender(rs.getString("gender"));
					user.setRollNo(rs.getString("rollNo"));
					user.setContactNumber(rs.getString("contactNumber"));
					user.setAddress(rs.getString("address"));
					user.setMentorOf(rs.getInt("mentorOf"));

					list.add(user); // Add the User instance to the list
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error retrieving class details", e);
		}

		return list;
	}

	public void deleteUser(String id) throws SQLException, ClassNotFoundException {
		String deleteSQL = String.format("DELETE FROM User WHERE userId = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		}
	}

	public List<User> getAllUser() throws SQLException, ClassNotFoundException {
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM User";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				User user = new User();

				user.setUserId(resultSet.getString("UserId"));
				user.setUsername(resultSet.getString("username"));
				user.setName(resultSet.getString("name"));
				user.setAge(resultSet.getInt("age"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setRole(Role.valueOf(resultSet.getString("role"))); // Ensure Role is correctly mapped
				user.setDOB(resultSet.getDate("dob") != null ? resultSet.getDate("dob").toLocalDate() : null);
				user.setStandard(resultSet.getInt("standard"));
				user.setGender(resultSet.getString("gender"));
				user.setRollNo(resultSet.getString("rollNo"));
				user.setContactNumber(resultSet.getString("contactNumber"));
				user.setAddress(resultSet.getString("address"));
				user.setMentorOf(resultSet.getInt("mentorOf"));

				users.add(user); // Add the User instance to the list
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error retrieving all users", e);
		}
		return users;
	}

	public void updateUser(User user, String userId, String columnToUpdate)
			throws SQLException, ClassNotFoundException {
		String updateSQL = String.format("UPDATE User SET %s = ? WHERE userId = ?", columnToUpdate);

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

			prepareStatementForUpdate(preparedStatement, user, columnToUpdate);
			preparedStatement.executeUpdate();
		}
	}

	protected void prepareStatementForUpdate(PreparedStatement preparedStatement, User user, String columnToUpdate)
			throws SQLException {
		switch (columnToUpdate) {
		case UserConstants.NAME_COLUMN:
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.EMAIL_COLUMN:
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.USERNAME_COLUMN:
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.PASSWORD_COLUMN:
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.AGE_COLUMN:
			preparedStatement.setInt(1, user.getAge());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.GENDER_COLUMN:
			preparedStatement.setString(1, user.getGender());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.CONTACT_NO_COLUMN:
			preparedStatement.setString(1, user.getContactNumber());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.ADDRESS_COLUMN:
			preparedStatement.setString(1, user.getAddress());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.ROLE_COLUMN:
			preparedStatement.setString(1, user.getRole().name());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.DOB_COLUMN:
			preparedStatement.setDate(1, java.sql.Date.valueOf(user.getDOB()));
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.STANDARD_COLUMN:
			preparedStatement.setInt(1, user.getStandard());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.ROLL_NO_COLUMN:
			preparedStatement.setString(1, user.getRollNo());
			preparedStatement.setString(2, user.getUserId());
			break;
		case UserConstants.MENTOR_OF_COLUMN:
			preparedStatement.setInt(1, user.getMentorOf());
			preparedStatement.setString(2, user.getUserId());
			break;
		default:
			throw new IllegalArgumentException("Unknown column: " + columnToUpdate);
		}
	}

}