package com.jantekovdaulet.bootcamp.services.impl;


import com.jantekovdaulet.bootcamp.models.Roles;
import com.jantekovdaulet.bootcamp.repositories.RolesRepository;
import com.jantekovdaulet.bootcamp.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles getRolesById(Long id) {
        return rolesRepository.findById(id).orElseThrow();
    }
}
