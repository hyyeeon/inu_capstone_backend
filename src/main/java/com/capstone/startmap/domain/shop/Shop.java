package com.capstone.startmap.domain.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shop_id;

    private String shop_name;

    private Integer shop_sales;

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
}
