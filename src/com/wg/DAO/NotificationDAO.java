package com.wg.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.Notification;
import com.wg.config.DatabaseConnection;

public class NotificationDAO {

	private Connection connection;

	public NotificationDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean sendNotification(Notification notification) {
		String query = "INSERT INTO Notification (notificationId, userId, type ,description,dateIssued) VALUES (?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, notification.getNotificationId());
			stmt.setString(2, notification.getUserId());
			stmt.setString(3, notification.getType());
			stmt.setString(4, notification.getDescription());
			stmt.setDate(5, Date.valueOf(notification.getDateIssued()));

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	String notificationId;
//	String userId;
//	String description;
//	String type;
//	LocalDate dateIssued;

	public List<Notification> readNotifications1(String userId) {
		String query = "SELECT * FROM Notification WHERE userId = ?";
		List<Notification> notificationList = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, userId);
			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Notification notification = new Notification();
					notification.setNotificationId(resultSet.getString("notificationId"));
					notification.setUserId(resultSet.getString("userId"));
					notification.setType(resultSet.getString("type"));
					notification.setDescription(resultSet.getString("message"));
					notification.setDateIssued(resultSet.getDate("date").toLocalDate());

					notificationList.add(notification);
				}
			}
		} catch (SQLException e) {
			System.out.println("Cannot fetch notications");
		}
		return notificationList;
	}

	public List<Notification> readNotifications(String userId) {
		String query = "SELECT * FROM Notification WHERE userId = ?";
		List<Notification> notificationList = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, userId);

			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Notification notification = new Notification();

					// Make sure these column names match exactly with those in the Notification
					// table
					notification.setNotificationId(resultSet.getString("notificationId"));
					notification.setUserId(resultSet.getString("userId"));
					notification.setType(resultSet.getString("type"));
					notification.setDescription(resultSet.getString("description"));
					notification.setDateIssued(resultSet.getDate("dateIssued").toLocalDate());

					notificationList.add(notification);
				}
			}
		} catch (SQLException e) {
			// Improved error handling
			e.printStackTrace();
			System.out.println("Cannot fetch notifications: " + e.getMessage());
		}

		return notificationList;
	}

}