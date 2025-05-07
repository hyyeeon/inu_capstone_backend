package com.capstone.startmap.domain.franchise.service;

import com.capstone.startmap.domain.building.api.dto.BuildingResDto;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.franchise.repository.FranchiseRepository;
import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import com.capstone.startmap.exception.franchise.NotFoundFranchiseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class FranchiseService {
    private final FranchiseRepository franchiseRepository;
    private final ShopRepository shopRepository;

    public FranchiseResDto showFranchise(Long franchise_id) {
        Franchise franchise = franchiseRepository.findById(franchise_id).orElseThrow(()->
                new NotFoundFranchiseException("존재하지 않는 프랜차이즈입니다."));
        List<Shop> shops = shopRepository.findByFranchiseId(franchise);
        List <ShopResDto> shopdtos = shops.stream()
                .map(ShopResDto::fromShop)  // fromFranchise 메서드를 그대로 활용
                .toList();
        return FranchiseResDto.fromFranchise(franchise, shopdtos);
    }
    public List<FranchiseResDto> showAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();

        return franchises.stream()
                .map(FranchiseResDto::fromFranchise)  // fromFranchise 메서드를 그대로 활용
                .collect(Collectors.toList());
    }
}
