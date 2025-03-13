package com.capstone.startmap.domain.franchise;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id", nullable = false)
    private long franchise_id;

    @Column(name = "franchise_name",nullable = false)
    private String franchise_name;

    @Column(name="franchise_type",nullable = false)
    private String franchise_type;





}
