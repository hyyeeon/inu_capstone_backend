package com.capstone.startmap.domain.result;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import jakarta.persistence.*;
import lombok.Getter;
import com.capstone.startmap.domain.user.User;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Entity
@Getter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id", nullable = false)
    private Long result_id;

    @Column(name = "bookmark", nullable = false)
    private Boolean bookmark;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building_id;

}
