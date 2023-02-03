package com.jantekovdaulet.bootcamp.services.impl;

import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Users;
import com.jantekovdaulet.bootcamp.repositories.AnnouncementRepository;
import com.jantekovdaulet.bootcamp.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcement addAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public Announcement getAnnouncement(Long id) {
        return announcementRepository.getById(id);
    }

    @Override
    public List<Announcement> getAnnouncementsByName(String key) {
        return announcementRepository.findAnnouncementsByNameContainingIgnoreCaseOrderByCurrentPriceDesc(key);
    }

    @Override
    public Announcement saveAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAnnouncementsByUser(Users user) {
        return announcementRepository.findAnnouncementsByUser(user);
    }

    @Override
    public void deleteAnnouncementById(Long annId) {
        announcementRepository.deleteById(annId);
    }


}
