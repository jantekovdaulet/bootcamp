package com.jantekovdaulet.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Component
public class Users extends BaseEntity{

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private boolean gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;
}
