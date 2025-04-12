package com.capstone.startmap.domain.shop;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long shop_id;

    @Column(name = "shop_name")
    private String shop_name;

    @Column(name="shop_sales")
    private int shop_sales;

    private Integer building_sales;

    private Integer area_sales;

    private Integer resident_population;

    private Double single_household;

    private Integer subway_users;

    private Integer similar_businesses;

    private Integer distance_same_franchise;

    private Integer nearby_schools;

    private Integer nearby_tourist_attractions;

    private Integer nearby_buildings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building_id;
}
