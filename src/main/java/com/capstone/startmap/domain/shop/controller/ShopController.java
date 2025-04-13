package com.capstone.startmap.domain.shop.controller;

import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import com.capstone.startmap.domain.shop.service.ShopService;
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

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<ShopResDto> getShop(@PathVariable long shopId) {
        ShopResDto shopresdto = shopService.showShop(shopId);

        return new ResponseEntity<>(shopresdto, HttpStatus.OK);
    }



}
