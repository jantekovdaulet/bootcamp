package com.jantekovdaulet.bootcamp.services;

import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Users;

import java.util.List;

public interface AnnouncementService {

    List<Announcement> getAllAnnouncements();
    Announcement addAnnouncement(Announcement announcement);
    Announcement getAnnouncement(Long id);
    List<Announcement> getAnnouncementsByName(String key);
    Announcement saveAnnouncement(Announcement announcement);
    List<Announcement> getAnnouncementsBySalesman(Users salesman);
    void deleteAnnouncementById(Long annId);
}
