package com.nunukang.nunukang.domain.fish;

import com.nunukang.nunukang.domain.fish.species.FishSpecies;
import com.nunukang.nunukang.domain.fish.time.FishTime;
import com.nunukang.nunukang.domain.user.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Fish extends FishTime {
    @Id @GeneratedValue
    @Column(name = "fish_id")
    private Long id;

    private String pictureName;
    private String picturePath;

    @Enumerated(EnumType.ORDINAL)
    private FishSpecies species;

    private Double fishSize;
    private Double fishScore;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User fishingUser;
}
