package com.capstone.startmap.domain.result.api.dto;

import com.capstone.startmap.domain.result.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultResDto {

    @Schema(description = "프랜차이즈 ID")
    private Long franchise_id;

    @Schema(description = "검색 날짜")
    private Date date;

    @Schema(description = "상가 ID")
    private Long building_id;

    @Schema(description = "검색 ID")
    private Long result_id;

    public ResultResDto(Result result) {

    }

    public static ResultResDto fromResult(Result result) {
        return new ResultResDto(result);
    }
}
