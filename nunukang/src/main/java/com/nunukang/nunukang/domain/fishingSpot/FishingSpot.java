package com.nunukang.nunukang.domain.fishingSpot;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.nunukang.nunukang.domain.fishingSpot.gps.Gps;
import com.nunukang.nunukang.domain.fishingSpot.type.FishingType;

import lombok.Getter;


@Getter
@Entity
public class FishingSpot {
    @Id @GeneratedValue
    @Column(name = "fishing_spot_id")
    private Long id;
    
    @Column(length = 30)
    private String name;

    @Column(length = 70)
    private String address;
    @Column(length = 15)
    private String number;
    @Column(length = 100)
    private String price;
    
    private Integer capacity;

    @Embedded
    private Gps gps;

    @Enumerated(EnumType.ORDINAL)
    private FishingType type;

    
}
