package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int notificationId;
	
	private String notifyTo;
	
	private String notifyFrom;
	
	private String notifyPostUrl;
	
	private String notificationSeen;
	
	private String notificationType;

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotifyTo() {
		return notifyTo;
	}

	public void setNotifyTo(String notifyTo) {
		this.notifyTo = notifyTo;
	}

	public String getNotifyFrom() {
		return notifyFrom;
	}

	public void setNotifyFrom(String notifyFrom) {
		this.notifyFrom = notifyFrom;
	}

	public String getNotifyPostUrl() {
		return notifyPostUrl;
	}

	public void setNotifyPostUrl(String notifyPostUrl) {
		this.notifyPostUrl = notifyPostUrl;
	}

	public String getNotificationSeen() {
		return notificationSeen;
	}

	public void setNotificationSeen(String notificationSeen) {
		this.notificationSeen = notificationSeen;
	}
	
	
	
}
