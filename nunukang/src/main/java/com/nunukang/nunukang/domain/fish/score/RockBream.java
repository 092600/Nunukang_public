package com.nunukang.nunukang.domain.fish.score;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
// @Table(name = "RockBream")
public class RockBream {

    @Id @GeneratedValue
    private Long id;
    
    private Integer species;

    private Double fishSize;
    private Double fishScore;

}
