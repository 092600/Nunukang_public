package com.nunukang.nunukang.domain.fish.score;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@DiscriminatorValue("3")
public class OliveFlounder extends FishForScore {

    private Integer species = 3;

}
