package com.nunukang.nunukang.domain.fish.score;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
// @Table(name = "KoreaRockfish")
public class KoreaRockfish {
    
    @Id @GeneratedValue
    private Long id;

    private Integer species;

    private Double fishSize;
    private Double fishScore;

}
