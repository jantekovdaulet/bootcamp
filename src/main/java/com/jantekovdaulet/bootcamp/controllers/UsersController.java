package com.jantekovdaulet.bootcamp.controllers;

import com.jantekovdaulet.bootcamp.models.Users;
import com.jantekovdaulet.bootcamp.services.AnnouncementService;
import com.jantekovdaulet.bootcamp.services.UsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AnnouncementService announcementService;

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.defaultPicture}")
    private String defaultPicture;

    @GetMapping(value = "/user-profile")
    @PreAuthorize("isAuthenticated()")
    public String getProfile(Model model) {

        model.addAttribute("currentUser", usersService.getUserData());
        return "profile";
    }

    @PostMapping(value = "/upload-avatar")
    @PreAuthorize("isAuthenticated()")
    public String uploadAvatar(@RequestParam(name = "userAvatar") MultipartFile file) {

        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                Users currentUser = usersService.getUserData();

                String picName = DigestUtils.sha1Hex("avatar_" + currentUser.getId() + "_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);

                currentUser.setPictureUrl(picName);
                usersService.saveUser(currentUser);

                return "redirect:/user-profile?success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "redirect:/";
    }

    @GetMapping(value = "/view-photo/{url}", produces = {MediaType.IMAGE_JPEG_VALUE})
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody byte[] viewProfilePhoto(@PathVariable(name = "url") String url) throws IOException {

        return AnnouncementController.getPhoto(url, viewPath, defaultPicture);
    }

    @GetMapping(value = "/login")
    @PreAuthorize("isAnonymous()")
    public String getLogin(Model model) {
        model.addAttribute("currentUser", usersService.getUserData());
        return "login";
    }

    @GetMapping(value = "/register")
    @PreAuthorize("isAnonymous()")
    public String getRegister(Model model) {
        model.addAttribute("currentUser", usersService.getUserData());
        return "register";
    }

    @PostMapping(value = "/register")
    @PreAuthorize("isAnonymous()")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String rePassword,
                             @RequestParam(name = "user_name") String name,
                             @RequestParam(name = "user_surname") String surname,
                             @RequestParam(name = "user_gender") boolean gender,
                             @RequestParam(name = "user_birthday") Date birthday) {

        if (password.equals(rePassword)) {

            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setGender(gender);
            newUser.setBirthday(birthday);

            if (usersService.createUser(newUser) != null) {
                return "redirect:/register?success";
            }
        }

        return "redirect:/register?error";
    }

    @GetMapping(value = "/403")
    @PreAuthorize("isAnonymous()")
    public String accessDenied(Model model) {
        model.addAttribute("currentUser", usersService.getUserData());
        return "403";
    }

}
