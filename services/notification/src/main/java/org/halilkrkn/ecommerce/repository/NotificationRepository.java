package org.halilkrkn.ecommerce.repository;

import org.halilkrkn.ecommerce.entity.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
