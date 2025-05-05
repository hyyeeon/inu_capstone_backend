package com.capstone.startmap.domain.town;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id", nullable = false)
    private Long town_id;

    @Column(name = "town_name", nullable = false)
    private String town_name;

    @Column(name =  "town_sales", nullable = false)
    private Long town_sales;
}
