package com.jantekovdaulet.bootcamp.repositories;


import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAnnouncementsByNameContainingIgnoreCaseOrderByCurrentPriceDesc(String key);
    List<Announcement> findAnnouncementsByUser(Users user);
}
