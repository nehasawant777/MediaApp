package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer >  {
	
	List<Notification> findByNotifyToAndNotificationSeen(String notifyTo, String notificationSeen);
	long countByNotifyToAndNotificationSeen(String notifyTo, String notificationSeen);

}
