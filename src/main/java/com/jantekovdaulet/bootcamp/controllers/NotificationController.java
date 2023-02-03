package com.jantekovdaulet.bootcamp.controllers;

import com.jantekovdaulet.bootcamp.models.Notification;
import com.jantekovdaulet.bootcamp.models.Users;
import com.jantekovdaulet.bootcamp.services.NotificationService;
import com.jantekovdaulet.bootcamp.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/notifications")
    @PreAuthorize("isAuthenticated()")
    public String getNotifications(Model model){

        Users currentUser = usersService.getUserData();
        model.addAttribute("currentUser", currentUser);

        List<Notification> notifications = notificationService.getNotificationsByToWhom(currentUser);
        model.addAttribute("notifications", notifications);

        return "notifications";
    }

}
