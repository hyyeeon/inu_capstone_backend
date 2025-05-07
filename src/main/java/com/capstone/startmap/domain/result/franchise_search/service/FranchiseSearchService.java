package com.capstone.startmap.domain.result.franchise_search.service;

import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import com.capstone.startmap.domain.result.franchise_search.api.dto.FranchiseSearchResDto;
import com.capstone.startmap.domain.result.franchise_search.repository.FranchiseSearchRepository;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.repository.UserRepository;
import com.capstone.startmap.exception.user.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class FranchiseSearchService {
    private FranchiseSearchRepository franchiseSearchRepository;
    private UserRepository userRepository;

    public List<FranchiseSearchResDto> showFranchiseSearch(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(()->
                new NotFoundUserException("User not found"));
        List<Franchise_search> franchiseSearches = franchiseSearchRepository.findAllByUserAndFranchiseResultTrue(user);

        return franchiseSearches.stream()
                .map(FranchiseSearchResDto::fromFranchiseSearch)  // fromFranchise 메서드를 그대로 활용
                .collect(Collectors.toList());
    }
}
