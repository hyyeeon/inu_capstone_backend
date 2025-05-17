package com.capstone.startmap.domain.result.service;

import com.capstone.startmap.domain.ai.dto.PredictFranchiseResponseDto;
import com.capstone.startmap.domain.ai.dto.PredictLocationResponseDto;
import com.capstone.startmap.domain.franchise_result.service.FranchiseResultService;
import com.capstone.startmap.domain.location_result.service.LocationResultService;
import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.ResultType;
import com.capstone.startmap.domain.result.api.dto.ResultCreateDto;
import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import com.capstone.startmap.domain.result.repository.ResultRepository;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.service.UserService;
import com.capstone.startmap.exception.Result.NotFoundResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final UserService userService;
    private final LocationResultService locationResultService;
    private final FranchiseResultService franchiseResultService;

    public List<ResultResDto> getAllResult(Long user_id, Integer type) {
        User user = userService.findUserById(user_id);
        List<Result> result = resultRepository.findAllByUser(user);
        List<ResultResDto> response = new ArrayList<>();
        ResultType resultType;
        if (type==0)  resultType=ResultType.FRANCHISE;
        else resultType = ResultType.LOCATION;
        for (Result rst : result) {
            //위치결과만 조회해야하는데 프랜차이즈 결과일때 스킵
            if (rst.getType()== ResultType.FRANCHISE && resultType == ResultType.LOCATION){
                continue;
            } else if (rst.getType()==ResultType.LOCATION && resultType == ResultType.FRANCHISE) { //프랜차이즈결과 조회인데 위치결과면 스킵
                continue;
            }
            response.add(rst.toDto(resultType));
        }
        return response;
    }
    public List<PredictFranchiseResponseDto> getFranchiseResult(Long user_id, Long result_id) {
        User user = userService.findUserById(user_id);
        Result result = resultRepository.findByResultIdAndUser(result_id, user).orElseThrow(()->
                new NotFoundResultException("결과가 없습니다. 회원정보와 결과 id를 확인하세요."));

        if (result.getType() != ResultType.FRANCHISE){
            new NotFoundResultException("결과 분류가 잘못되었습니다. 프랜차이즈 검색 결과를 요청해 주세요.");
        }

        List<PredictFranchiseResponseDto> response = franchiseResultService.getAllFranchiseResult(result);
        return response;
    }
    public List<PredictLocationResponseDto> getLocationResult(Long user_id, Long result_id) {
        User user = userService.findUserById(user_id);
        Result result = resultRepository.findByResultIdAndUser(result_id, user).orElseThrow(()->
                new NotFoundResultException("결과가 없습니다. 회원정보와 결과 id를 확인하세요."));
        if (result.getType() != ResultType.FRANCHISE){
            new NotFoundResultException("결과 분류가 잘못되었습니다. 위치 추천 검색 결과를 요청해 주세요.");
        }
        List<PredictLocationResponseDto> response = locationResultService.getAllLocationResult(result);
        return response;
    }

    public Result createResult(ResultCreateDto dto){
        Result result = dto.toEntity();
        result = resultRepository.save(result);
        return result;
    }
}
