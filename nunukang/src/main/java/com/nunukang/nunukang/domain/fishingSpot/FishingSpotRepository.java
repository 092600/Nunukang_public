package com.nunukang.nunukang.domain.fishingSpot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.nunukang.nunukang.domain.fishingSpot.type.FishingType;

public interface FishingSpotRepository extends JpaRepository<FishingSpot, Long>{

    List<FishingSpot> findAllByType(FishingType type);
}
