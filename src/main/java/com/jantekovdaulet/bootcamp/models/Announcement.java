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
@Table(name = "announcements")
@Component
public class Announcement extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "min_price")
    private double minPrice;

    @Column(name = "current_price")
    private double currentPrice;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    //не знаю, как создать поле для обратного отсчета
}
