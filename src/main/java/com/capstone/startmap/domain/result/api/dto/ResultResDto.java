package com.capstone.startmap.domain.result.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResDto {
    @Schema(description = "검색 ID")
    private Long result_id;

    @Schema(description = "검색 날짜")
    private LocalDate date;

    @Schema(description = "상가 ID")
    private Long building_id;

    @Schema(description = "프랜차이즈 ID")
    private Long franchise_id;

//    public ResultResDto(Result result) {
//
//    }
//
//    public static ResultResDto fromResult(Result result) {
//        return new ResultResDto(result);
//    }
}
