package com.capstone.startmap.domain.result.location_search.service;

import com.capstone.startmap.domain.result.location_search.Location_search;
import com.capstone.startmap.domain.result.location_search.api.dto.LocationSearchResDto;
import com.capstone.startmap.domain.result.location_search.repository.LocationSearchRepository;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.repository.UserRepository;
import com.capstone.startmap.exception.user.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LocationSearchService {
    private LocationSearchRepository locationSearchRepository;
    private UserRepository userRepository;

    public List<LocationSearchResDto> showLocationSearch(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(()->
                new NotFoundUserException("User not found"));
        List<Location_search> locationSearches = locationSearchRepository.findAllByUserAndFranchiseResultFalse(user);

        return locationSearches.stream()
                .map(LocationSearchResDto::fromLocationSearch)
                .collect(Collectors.toList());
    }
}
