package com.capstone.startmap.domain.shop;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long shop_id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_address")
    private String shop_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building buildingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchiseId;

    @Column(name="shop_sales")
    private Integer shop_sales;

    @Column(name="building_sales")
    private Integer building_sales;

    @Column(name="area_sales")
    private Integer area_sales;

    @Column(name="resident_population")
    private Integer resident_population;

    @Column(name="single_household")
    private Double single_household;

    @Column(name="subway_users")
    private Integer subway_users;


    private Integer similar_businesses;

    private Integer distance_same_franchise;

    private Integer nearby_schools;

    private Integer nearby_tourist_attractions;

    private Integer nearby_buildings;

}
