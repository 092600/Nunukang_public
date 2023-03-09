package com.nunukang.nunukang.domain.fish;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.nunukang.nunukang.domain.fish.species.FishSpecies;


public interface FishRepository extends JpaRepository<Fish, Long> {

    List<Fish> findAllBySpecies(FishSpecies species);
    // List<Fish> findAllBySpeciesOrderByFishSizeDesc(FishSpecies speices);
    List<Fish> findAllBySpeciesOrderByFishSizeDesc(FishSpecies speices);

    @Query(value = "SELECT f FROM Fish f WHERE f.fishingUser.id = :id ORDER BY f.id DESC")
    List<Fish> getFishs(@Param(value = "id") Long id);
    
}
