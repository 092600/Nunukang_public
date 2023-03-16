package com.nunukang.nunukang.domain.fish.score;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@DiscriminatorValue("5")
public class RockBream extends FishForScore {
    
    private Integer species = 5;

}
