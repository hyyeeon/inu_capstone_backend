package com.capstone.startmap.domain.location_result.service;

import com.capstone.startmap.domain.location_result.Location_result;
import com.capstone.startmap.domain.location_result.api.dto.LocationResultResDto;
import com.capstone.startmap.domain.location_result.repository.LocationResultRepository;
import com.capstone.startmap.exception.franchise_result.NotFoundFranchiseResultException;
import com.capstone.startmap.exception.location_result.NotFoundLocationResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LocationResultService {
    private LocationResultRepository locationResultRepository;

    public LocationResultResDto showLocationResult(Long search_id) {
        List<Location_result> location_result_list = locationResultRepository.findAllBySearchId(search_id);
        if (location_result_list.isEmpty()) {
            throw new NotFoundLocationResultException("존재하지 않는 검색 결과 입니다.");
        }

        return LocationResultResDto.fromLocationResultList(location_result_list);
    }
}
