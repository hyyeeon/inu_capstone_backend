package com.capstone.startmap.domain.ai.dto;

import com.capstone.startmap.domain.building.Building;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "매출 예상 요청")
public class PredictRequestDto {
    @Schema(description = "상가 매출")
    private Integer building_sales;
    @Schema(description = "상권 매출")
    private Integer area_sales;
    @Schema(description = "거주 인구")
    private Integer resident_population;
    @Schema(description = "1인 가구 비율")
    private Double single_household;
    @Schema(description = "주변 지하철역 이용자 수")
    private Integer subway_users;
    @Schema(description = "동종 업계 개수")
    private Integer similar_businesses;
    @Schema(description = "동일 프랜차이즈 최단거리")
    private Integer distance_same_franchise;
    @Schema(description = "주변 학교 수")
    private Integer nearby_schools;
    @Schema(description = "주변 관광지 유무")
    private Integer nearby_tourist_attractions;
    @Schema(description = "주변 빌딩 수")
    private Integer nearby_buildings;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("building_sales", building_sales);
        map.put("area_sales", area_sales);
        map.put("resident_population", resident_population);
        map.put("single_household", single_household);
        map.put("subway_users", subway_users);
        map.put("similar_businesses", similar_businesses);
        map.put("distance_same_franchise", distance_same_franchise);
        map.put("nearby_schools", nearby_schools);
        map.put("nearby_tourist_attractions", nearby_tourist_attractions);
        map.put("nearby_buildings", nearby_buildings);
        return map;
    }
}
