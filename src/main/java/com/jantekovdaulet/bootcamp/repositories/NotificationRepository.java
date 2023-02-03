package com.jantekovdaulet.bootcamp.repositories;


import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Notification;
import com.jantekovdaulet.bootcamp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationByToWhom(Users toWhom);
}
