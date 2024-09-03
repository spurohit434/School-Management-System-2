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
		;
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
		if (notificationList.isEmpty()) {
			System.out.println("No notifications");
			return notificationList;
		}
		return notificationList;
		// else {
////			for (Notification notification : notificationList) {
////				System.out.println("------------------------");
////				System.out.println("Notification Id: " + notification.getNotificationId());
////				System.out.println("Notification: " + notification.getDescription());
////				System.out.println("Message: " + notification.getType());
////				System.out.println("Date: " + Date.valueOf(notification.getDateIssued()));
////				System.out.println("------------------------");
////			}
//			// return true;
//		}
	}
}
