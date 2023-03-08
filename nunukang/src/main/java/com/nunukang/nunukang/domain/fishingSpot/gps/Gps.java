package com.nunukang.nunukang.domain.fishingSpot.gps;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Gps {
    
    @Column(length = 50)
    
    private String latitude;
    @Column(length = 50)
    private String longitude;

}
