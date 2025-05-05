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

    @Column(name =  "count_pack", nullable = false)
    private Integer count_pack;

    @Column(name =  "count_two", nullable = false)
    private Integer count_two;

    @Column(name =  "count_ediya", nullable = false)
    private Integer count_ediya;

    @Column(name =  "count_mega", nullable = false)
    private Integer count_mega;

    @Column(name =  "count_com", nullable = false)
    private Integer count_com;

    @Column(name =  "count_the", nullable = false)
    private Integer count_the;

    @Column(name =  "count_tous", nullable = false)
    private Integer count_tous;

    @Column(name =  "count_paris", nullable = false)
    private Integer count_paris;

    @Column(name =  "count_dun", nullable = false)
    private Integer count_dun;
}
