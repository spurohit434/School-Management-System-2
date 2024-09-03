package com.wg.Controller;

import java.util.List;

import com.wg.Model.Notification;
import com.wg.Services.NotificationService;

public class NotificationController {
	private NotificationService notificationService;
	// private Scanner scanner = new Scanner(System.in);

	public NotificationController() {
	}

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public boolean sendNotification(Notification notification) {
		return notificationService.sendNotification(notification);
	}

	public List<Notification> readNotifications(String userId) {
		return notificationService.readNotifications(userId);
	}
}