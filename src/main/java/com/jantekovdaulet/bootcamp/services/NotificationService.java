package com.jantekovdaulet.bootcamp.services;

import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Notification;
import com.jantekovdaulet.bootcamp.models.Users;

import java.util.List;

public interface NotificationService {

    List<Notification> getAllNotification();
    Notification addNotification(Notification notification);
    Notification getNotification(Long id);
//    List<Notification> getAnnouncementsByName(String key);
    Notification saveNotification(Notification notification);
//    List<Notification> getNotificationsBySalesman(Users salesman);
    void deleteNotificationById(Long notId);
    List<Notification> getNotificationsByToWhom(Users toWhom);
    void sendNotificationForBuyer(String text, Announcement announcement);
    void sendNotificationForSalesman(String text, Announcement announcement);

}
