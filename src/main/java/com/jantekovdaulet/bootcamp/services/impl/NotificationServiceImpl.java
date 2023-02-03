package com.jantekovdaulet.bootcamp.services.impl;

import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Notification;
import com.jantekovdaulet.bootcamp.models.Users;
import com.jantekovdaulet.bootcamp.repositories.AnnouncementRepository;
import com.jantekovdaulet.bootcamp.repositories.NotificationRepository;
import com.jantekovdaulet.bootcamp.services.AnnouncementService;
import com.jantekovdaulet.bootcamp.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotification(Long id) {
        return notificationRepository.getReferenceById(id);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotificationById(Long notId) {
        notificationRepository.deleteById(notId);
    }

    @Override
    public List<Notification> getNotificationsByToWhom(Users toWhom) {
        return notificationRepository.getNotificationByToWhom(toWhom);
    }

    @Override
    public void sendNotificationForBuyer(String text, Announcement announcement) {
        Notification notification = new Notification();
        notification.setText(text);
        notification.setToWhom(announcement.getBuyer());
        notification.setAnnouncement(announcement);
        notificationRepository.save(notification);
    }

    @Override
    public void sendNotificationForSalesman(String text, Announcement announcement) {
        Notification notification = new Notification();
        notification.setText(text);
        notification.setToWhom(announcement.getSalesman());
        notification.setAnnouncement(announcement);
        notificationRepository.save(notification);
    }
}
