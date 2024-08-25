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
		String query = "INSERT INTO User (userId, username, name, age, password,email,role) VALUES (?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getName());
			stmt.setInt(4, user.getAge());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getRole().name());
//			stmt.setString(5, user.getGender());
//			stmt.setString(6, user.getPassword());
//			stmt.setString(7, user.getEmail());

			stmt.executeUpdate();
		}
	}

	public User getUserById(String UserId) throws SQLException, ClassNotFoundException {
		User user = null;
		String query = "SELECT * FROM User WHERE UserId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, UserId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("UserId"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				Role role = Role.valueOf(rs.getString("role"));
				user.setRole(role);
			}
		}
		return user;
	}

	public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
		User user = null;
		String query = "SELECT * FROM User WHERE username = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("UserId"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				Role role = Role.valueOf(rs.getString("role"));
				user.setRole(role);
			}
		}
		return user;
	}

	public List<User> getClassDetails(int standard) throws SQLException, ClassNotFoundException {
		List<User> list = new ArrayList<>();
		String query = "SELECT * FROM User where standard = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, standard);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new User(rs.getString("UserId"), rs.getString("name"), rs.getString("email"), rs.getInt("age"),
						rs.getInt("standard")));
			}
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
				String id = resultSet.getString("UserId");
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");

				User user = new User(id, username, email, password);
				users.add(user);
			}
		}
		return users;
	}

	public void updateUser(User user, String userId, String columnToUpdate)
			throws SQLException, ClassNotFoundException {

		// String updateSQL = String.format("UPDATE User SET %s = ? WHERE %s = ?");
		String updateSQL = String.format("UPDATE User SET %s = ? WHERE %s = ?", columnToUpdate, userId);

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
			break;
		case UserConstants.EMAIL_COLUMN:
			preparedStatement.setString(1, user.getEmail());
			break;
		case UserConstants.USERNAME_COLUMN:
			preparedStatement.setString(1, user.getUsername());
			break;
		case UserConstants.PASSWORD_COLUMN:
			preparedStatement.setString(1, user.getPassword());
			break;
		case UserConstants.AGE_COLUMN:
			preparedStatement.setInt(1, user.getAge());
			break;
		case UserConstants.GENDER_COLUMN:
			preparedStatement.setString(1, user.getGender());
			break;
		case UserConstants.CONTACT_NO_COLUMN:
			preparedStatement.setString(1, user.getContactNumber());
			break;
		case UserConstants.ADDRESS_COLUMN:
			preparedStatement.setString(1, user.getAddress());
			break;
		case UserConstants.ROLE_COLUMN:
			preparedStatement.setString(1, user.getRole().name());
			break;
		case UserConstants.UPDATED_AT_COLUMN:
			// preparedStatement.setDate(1, new
			// java.sql.Date(user.getUpdatedAt().getTime()));
			break;
		default:
			break;

		}
		preparedStatement.setString(2, user.getUserId());
	}

}