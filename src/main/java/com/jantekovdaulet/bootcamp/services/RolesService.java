package com.jantekovdaulet.bootcamp.services;


import com.jantekovdaulet.bootcamp.models.Roles;

import java.util.List;

public interface RolesService {

    List<Roles> getAllRoles();
    Roles getRolesById(Long id);
}
