package com.nunukang.nunukang.domain.fish.score.species;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


import com.nunukang.nunukang.domain.fish.score.FishForScore;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class BlackProgy extends FishForScore {


    private Integer species = 2;


}
