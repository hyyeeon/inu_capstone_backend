package com.capstone.startmap.domain.result.location_search.service;

import com.capstone.startmap.domain.result.location_search.Location_search;
import com.capstone.startmap.domain.result.location_search.api.dto.LocationSearchResDto;
import com.capstone.startmap.domain.result.location_search.repository.LocationSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LocationSearchService {
    private LocationSearchRepository locationSearchRepository;

    public List<LocationSearchResDto> showLocationSearch(Long user_id) {
        List<Location_search> locationSearches = locationSearchRepository.findAllByUser_idAndFranchiseResultFalse(user_id);

        return locationSearches.stream()
                .map(LocationSearchResDto::fromLocationSearch)
                .collect(Collectors.toList());
    }
}
