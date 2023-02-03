package com.jantekovdaulet.bootcamp.controllers;

import com.jantekovdaulet.bootcamp.models.Announcement;
import com.jantekovdaulet.bootcamp.models.Users;
import com.jantekovdaulet.bootcamp.services.AnnouncementService;
import com.jantekovdaulet.bootcamp.services.UsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private UsersService usersService;

    @Value("${file.annPicture.viewPath}")
    private String viewPath;

    @Value("${file.annPicture.uploadPath}")
    private String uploadPath;

    @Value("${file.annPicture.defaultPicture}")
    private String defaultPicture;

    @GetMapping(value = "/")
    @PreAuthorize("isAuthenticated()")
    public String getIndex(Model model) {

        model.addAttribute("currentUser", usersService.getUserData());

        List<Announcement> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);

        return "index";
    }

    @GetMapping(value = "/my-announcements")
    @PreAuthorize("isAuthenticated()")
    public String getMyAnnouncements(Model model) {

        Users currentUser = usersService.getUserData();
        model.addAttribute("currentUser", currentUser);

        List<Announcement> announcements = announcementService.getAnnouncementsByUser(currentUser);
        model.addAttribute("announcements", announcements);

        return "index";
    }

    @GetMapping(value = "/search-announcement")
    @PreAuthorize("isAuthenticated()")
    public String search(@RequestParam(name = "key", defaultValue = "", required = false) String key,
                         Model model) {

        model.addAttribute("currentUser", usersService.getUserData());

        List<Announcement> announcements = announcementService.getAnnouncementsByName(key);
        model.addAttribute("announcements", announcements);

        return "index";
    }

    @GetMapping(value = "/form")
    @PreAuthorize("isAuthenticated()")
    public String getForm(Model model) {

        model.addAttribute("currentUser", usersService.getUserData());
        return "form";
    }

    @PostMapping(value = "/add-announcement")
    @PreAuthorize("isAuthenticated()")
    public String addAnnouncement(Announcement announcement,
                                  @RequestParam(name = "userId") Long userId) {

        announcement.setCurrentPrice(announcement.getMinPrice() - 1);
        announcement.setUser(usersService.getUserById(userId));
        announcementService.addAnnouncement(announcement);
        return "redirect:/details/" + announcement.getId();
    }

    @GetMapping(value = "/details/{announcementId}")
    @PreAuthorize("isAuthenticated()")
    public String getDetails(@PathVariable(name = "announcementId") Long announcementId,
                             Model model) {

        model.addAttribute("currentUser", usersService.getUserData());

        Announcement announcement = announcementService.getAnnouncement(announcementId);
        model.addAttribute("announcement", announcement);

        return "details";
    }

    @PostMapping(value = "/delete-announcement")
    @PreAuthorize("isAuthenticated()")
    public String deleteAnnouncement(@RequestParam(name = "annId") Long annId){

        announcementService.deleteAnnouncementById(annId);
        return "redirect:/my-announcements";
    }

    @PostMapping(value = "/place-bet")
    @PreAuthorize("isAuthenticated()")
    public String placeBet(@RequestParam(name = "annId") Long annId,
                           @RequestParam(name = "bet") double bet){

        Announcement announcement = announcementService.getAnnouncement(annId);
        if(bet >= announcement.getCurrentPrice()){
            announcement.setCurrentPrice(bet);
            announcementService.saveAnnouncement(announcement);
        }
        return "redirect:/details/" + annId + "?succes-bet";
    }

    @PostMapping(value = "/upload-ann-picture")
    @PreAuthorize("isAuthenticated()")
    public String uploadAnnPicture(@RequestParam(name = "annId") Long annId,
                                   @RequestParam(name = "annAvatar") MultipartFile file) {

        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                Announcement announcement = announcementService.getAnnouncement(annId);

                String picName = DigestUtils.sha1Hex("announcement_" + announcement.getId() + "_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);

                announcement.setPictureUrl(picName);
                announcementService.saveAnnouncement(announcement);

                return "redirect:/details/" + announcement.getId() + "?success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/view-ann-picture/{url}", produces = {MediaType.IMAGE_JPEG_VALUE})
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody byte[] viewProfilePhoto(@PathVariable(name = "url") String url) throws IOException {
        return getPhoto(url, viewPath, defaultPicture);
    }

    static byte[] getPhoto(@PathVariable(name = "url") String url, String viewPath, String defaultPicture) throws IOException {
        String pictureUrl = viewPath + defaultPicture;

        if (url != null && !url.equals("null")) {
            pictureUrl = viewPath + url + ".jpg";
        }

        InputStream in;

        try {

            ClassPathResource resource = new ClassPathResource(pictureUrl);
            in = resource.getInputStream();

        } catch (Exception e) {

            ClassPathResource resource = new ClassPathResource(pictureUrl);
            in = resource.getInputStream();
            e.printStackTrace();
        }

        return IOUtils.toByteArray(in);
    }
}
