package com.capstone.startmap.domain.franchise;

import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id", nullable = false)
    private Long franchise_id;

    @Column(name = "franchise_name",nullable = false)
    private String franchise_name;

    @Column(name="franchise_type",nullable = false)
    private String franchise_type;


    public FranchiseResDto toDto(){
        return FranchiseResDto.builder()
                .franchise_name(this.franchise_name)
                .franchise_type(this.franchise_type)
                .franchise_shop(null)
                .build();
    }
    public FranchiseResDto toDto(List<ShopResDto> shops){
        return FranchiseResDto.builder()
                .franchise_name(this.franchise_name)
                .franchise_type(this.franchise_type)
                .franchise_shop(shops)
                .build();
    }
}
