package com.capstone.startmap.domain.location_result.service;

import com.capstone.startmap.domain.ai.dto.PredictLocationResponseDto;
import com.capstone.startmap.domain.location_result.LocationResult;
import com.capstone.startmap.domain.location_result.api.dto.LocationResultCreateDto;
import com.capstone.startmap.domain.location_result.api.dto.LocationResultResDto;
import com.capstone.startmap.domain.location_result.repository.LocationResultRepository;
import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.repository.ResultRepository;
import com.capstone.startmap.exception.Result.NotFoundResultException;
import com.capstone.startmap.exception.location_result.NotFoundLocationResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationResultService {
    private LocationResultRepository locationResultRepository;
    private ResultRepository resultRepository;

    public LocationResultResDto showLocationResult(Long search_id) {
        Result result = resultRepository.findById(search_id).orElseThrow(()->
                new NotFoundResultException("Result not found"));
        List<LocationResult> location_result_list = locationResultRepository.findAllByResult(result);
        if (location_result_list.isEmpty()) {
            throw new NotFoundLocationResultException("존재하지 않는 검색 결과 입니다.");
        }
        return LocationResultResDto.fromLocationResultList(location_result_list);
    }
    public Long createLocationResult(PredictLocationResponseDto predictDto, LocationResultCreateDto dto){
        LocationResult locationResult = predictDto.toEntity(dto);
        locationResult = locationResultRepository.save(locationResult);
        return locationResult.getLocation_result_id();
    }
}
