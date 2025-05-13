package com.capstone.startmap.domain.building;

import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.exception.building.NotFoundBuildingException;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long building_id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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

    private Integer getDistance(String name){
        switch (name){
            case "parisbaguette": return distance_parisbaguette;
            case "touslesjours": return distance_touslesjours;
            case "dunkin": return distance_dunkin;
            case "theventi": return distance_theventi;
            case "mega": return distance_mega;
            case "pack": return distance_pack;
            case "compose": return distance_compose;
            case "ediya": return distance_ediya;
            case "twosome": return distance_twosome;
            default:
                throw new NotFoundBuildingException("프랜차이즈명이 잘못되었습니다: " + name);
        }
    }

    public PredictRequestDto toDto(String model_name){
        Integer category = 0;
        String name = "";
        if(model_name.contains("bakery")){
            category = this.similar_businesses_bakery;
            name = model_name.substring(7);
            System.out.println(name);
        } else if (model_name.contains("cafe")) {
            category = this.similar_businesses_cafe;
            name = model_name.substring(5);
            System.out.println(name);
        }
        return PredictRequestDto.builder()
                .building_sales(this.building_sales)
                .area_sales(this.area_sales)
                .resident_population(this.resident_population)
                .single_household(this.single_household)
                .subway_users(this.subway_users)
                .similar_businesses(category)
                .distance_same_franchise(this.getDistance(name))
                .nearby_schools(this.nearby_schools)
                .nearby_tourist_attractions(this.nearby_tourist_attractions)
                .nearby_buildings(this.nearby_buildings)
                .build();
    }
}
