package com.jantekovdaulet.bootcamp.services;

import com.jantekovdaulet.bootcamp.models.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {
    Users getUserByEmail(String email);
    Users getUserData();
//    List<Users> getUsersByFacultyId(Long facultyId);
//    List<Users> getUsersByNameLike(String key);

    List<Users> getAllUsers();
    void saveUser(Users user);
    Users getUserById(Long id);
    void deleteUserById(Long id);
    Users createUser(Users user);
}
