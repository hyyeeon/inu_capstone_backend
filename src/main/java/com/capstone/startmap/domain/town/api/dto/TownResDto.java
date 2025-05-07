package com.capstone.startmap.domain.town.api.dto;

import com.capstone.startmap.domain.town.Town;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//동별 매출액, 각 매장 개수
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TownResDto {

    @Schema(description = "동 이름")
    private String town_name;

    @Schema(description = "동 매출액")
    private Long town_sales;

    @Schema(description = "빽다방 개수")
    private Integer count_pack;

    @Schema(description = "투썸 플레이스 개수")
    private Integer count_two;

    @Schema(description = "이디야 개수")
    private Integer count_ediya;

    @Schema(description = "메가커피 개수")
    private Integer count_mega;

    @Schema(description = "컴포즈 개수")
    private Integer count_com;

    @Schema(description = "더 벤티 개수")
    private Integer count_the;

    @Schema(description = "뚜레쥬르 개수")
    private Integer count_tous;

    @Schema(description = "파리바게트 개수")
    private Integer count_paris;

    @Schema(description = "던킨 개수")
    private Integer count_dun;

    public TownResDto(Town town) {

    }
    public static TownResDto fromTown(Town town) {
        TownResDto dto = new TownResDto(town);
        dto.town_name = town.getTown_name();
        dto.town_sales = town.getTown_sales();
        dto.count_pack = town.getCount_pack();
        dto.count_two = town.getCount_two();
        dto.count_ediya = town.getCount_ediya();
        dto.count_mega = town.getCount_mega();
        dto.count_com = town.getCount_com();
        dto.count_the = town.getCount_the();
        dto.count_tous = town.getCount_tous();
        dto.count_paris = town.getCount_paris();
        dto.count_dun = town.getCount_dun();

        return dto;
    }
}
