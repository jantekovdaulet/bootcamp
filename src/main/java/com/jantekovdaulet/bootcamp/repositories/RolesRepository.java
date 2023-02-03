package com.jantekovdaulet.bootcamp.repositories;

import com.jantekovdaulet.bootcamp.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(String role);
}
