package com.capstone.startmap.domain.shop;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long shop_id;

    @Column(name = "shop_name")
    private String shop_name;

    @Column(name="shop_sales")
    private int shop_sales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building_id;


}
