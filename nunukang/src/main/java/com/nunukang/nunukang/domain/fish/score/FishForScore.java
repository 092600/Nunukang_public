package com.nunukang.nunukang.domain.fish.score;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class FishForScore {

    @Id @GeneratedValue
    private Long id;

    private Double fishSize;
    private Integer fishScore;

}
