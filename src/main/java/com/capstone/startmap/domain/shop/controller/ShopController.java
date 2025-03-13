package com.capstone.startmap.domain.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShopController {

    public ResponseEntity<ShopResDto> ShopSearch() {
        ShopResDto show = ShopService.search();

        return new ResponseEntity<>
    }
}
