package com.capstone.startmap.domain.building;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long building_id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "sales")
    private Long sales;

    @Column(name = "address")
    private String address;
}
