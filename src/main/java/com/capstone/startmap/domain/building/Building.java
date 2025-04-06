package com.capstone.startmap.domain.building;

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
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long building_id;

    private String zone;

    private String address;

    private Integer building_sales;

    private Integer area_sales;

    private Integer resident_population;

    private Double single_household;

    private String subway_name;

    private Integer subway_users;

    private Integer similar_businesses_cafe;

    private Integer similar_businesses_bakery;

    private Integer nearby_schools;

    private Integer nearby_tourist_attractions;

    private Integer nearby_buildings;

    private Integer distance_parisbaguette;

    private Integer distance_touslesjours;

    private Integer distance_dunkin;

    private Integer distance_theventi;

    private Integer distance_mega;

    private Integer distance_pack;

    private Integer distance_compose;

    private Integer distance_ediya;

    private Integer distance_twosome;
}
