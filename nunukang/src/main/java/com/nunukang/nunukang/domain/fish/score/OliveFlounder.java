package com.nunukang.nunukang.domain.fish.score;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
// @Table(name = "OliveFlounder")
public class OliveFlounder {
    

    @Id @GeneratedValue
    private Long id;

    private Integer species;

    private Double fishSize;
    private Double fishScore;

}
