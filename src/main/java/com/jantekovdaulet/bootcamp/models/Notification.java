package com.jantekovdaulet.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
@Component
public class Notification extends BaseEntity{

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users toWhom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Announcement announcement;
}
