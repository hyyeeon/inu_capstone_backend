package com.capstone.startmap.domain.shop.controller;

import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import com.capstone.startmap.domain.shop.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShopController {
    private final ShopService shopService;
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @Operation(summary = "매장 정보 조회",
            description = "shopId를 이용해 특정 매장의 상세 정보를 조회합니다.")
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<ShopResDto> getShop(
            @Parameter(description = "조회할 매장의 ID")
            @PathVariable long shopId) {
        ShopResDto shopresdto = shopService.showShop(shopId);

        return new ResponseEntity<>(shopresdto, HttpStatus.OK);
    }



}
