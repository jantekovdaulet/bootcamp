package com.jantekovdaulet.bootcamp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
