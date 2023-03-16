package com.nunukang.nunukang.domain.fish.score;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@DiscriminatorValue("4")
public class KoreaRockfish extends FishForScore {
    
    private Integer species = 4;


}
