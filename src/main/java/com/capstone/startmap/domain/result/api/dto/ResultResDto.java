package com.capstone.startmap.domain.result.api.dto;

import com.capstone.startmap.domain.result.Result;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultResDto {
    private Long result_id;
    private Long franchise_id;
    private Date date;
    private Long building_id;

    public ResultResDto(Result result) {

    }

    public static ResultResDto fromResult(Result result) {
        return new ResultResDto(result);
    }
}
