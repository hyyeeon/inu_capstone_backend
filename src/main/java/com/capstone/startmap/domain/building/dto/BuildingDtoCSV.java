package com.capstone.startmap.domain.building.dto;

import com.capstone.startmap.domain.building.Building;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDtoCSV {
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
    public Building toEntity() {
        return Building.builder()
                .zone(this.zone)
                .address(this.address)
                .building_sales(this.building_sales)
                .area_sales(this.area_sales)
                .resident_population(this.resident_population)
                .single_household(this.single_household)
                .subway_name(this.subway_name)
                .subway_users(this.subway_users)
                .similar_businesses_cafe(this.similar_businesses_cafe)
                .similar_businesses_bakery(this.similar_businesses_bakery)
                .nearby_schools(this.nearby_schools)
                .nearby_buildings(this.nearby_buildings)
                .nearby_tourist_attractions(this.nearby_tourist_attractions)
                .distance_dunkin(this.distance_dunkin)
                .distance_touslesjours(this.distance_touslesjours)
                .distance_parisbaguette(this.distance_parisbaguette)
                .distance_compose(this.distance_compose)
                .distance_ediya(this.distance_ediya)
                .distance_mega(this.distance_mega)
                .distance_pack(this.distance_pack)
                .distance_theventi(this.distance_theventi)
                .distance_twosome(this.distance_twosome)
                .build();
    }
}
