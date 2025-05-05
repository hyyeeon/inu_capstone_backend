package com.capstone.startmap.domain.location_result.service;

import com.capstone.startmap.domain.location_result.Location_result;
import com.capstone.startmap.domain.location_result.api.dto.LocationResultResDto;
import com.capstone.startmap.domain.location_result.repository.LocationResultRepository;
import com.capstone.startmap.exception.location_result.NotFoundLocationResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LocationResultService {
    private LocationResultRepository locationResultRepository;

    public LocationResultResDto showLocationResult(Long search_id) {
        Location_result location_result = locationResultRepository.findById(search_id).orElseThrow(()->
                new NotFoundLocationResultException("존재하지 않는 검색결과입니다."));
        return LocationResultResDto.fromLocationResult(location_result);
    }
}
