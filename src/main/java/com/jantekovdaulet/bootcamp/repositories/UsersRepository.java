package com.jantekovdaulet.bootcamp.repositories;

import com.jantekovdaulet.bootcamp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
//    List<Users> findAllByNameContainingIgnoreCaseOrderByNameAsc(String key);
//    List<Users> findUsersByFacultyIdAndNameContainingIgnoreCaseOrderByNameAsc(Long facultyId, String key);
////    List<Users> findAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrderByNameAsc(String key);
}
