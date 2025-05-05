package com.capstone.startmap.domain.town.api.dto;

import com.capstone.startmap.domain.town.Town;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//동별 매출액, 각 매장 개수
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TownResDto {
    private Long town_sales;
    private String town_name;
    private Integer count_pack;
    private Integer count_two;
    private Integer count_ediya;
    private Integer count_mega;
    private Integer count_com;
    private Integer count_the;
    private Integer count_tous;
    private Integer count_paris;
    private Integer count_dun;

    public TownResDto(Town town) {

    }
    public static TownResDto fromTown(Town town) { return new TownResDto(town); }
}
