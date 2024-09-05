package com.wg.Services;

import java.sql.SQLException;
import java.util.List;

import com.wg.DAO.NotificationDAO;
import com.wg.Model.Notification;

public class NotificationService {
	private NotificationDAO notificationDAO;

	public NotificationService() {
	}

	public NotificationService(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	public boolean sendNotification(Notification notification) {
		boolean sendStatus = false;
		try {
			sendStatus = notificationDAO.sendNotification(notification);
			return sendStatus;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return sendStatus;
	}

	public List<Notification> readNotifications(String userId) {
		List<Notification> notificationList = null;
		try {
			notificationList = notificationDAO.readNotifications(userId);
			return notificationList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return notificationList;
	}
}
