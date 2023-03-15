package com.nunukang.nunukang.domain.fishingSpot;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nunukang.nunukang.domain.fishingSpot.type.FishingType;

@Service
@RequiredArgsConstructor
public class FishingSpotService {

    private final FishingSpotRepository fishingSpotRepository;
    

    public List<FishingSpot> getFishingSpotByType(FishingType type) {

        return fishingSpotRepository.findAllByType(type);
    }

    public Optional<FishingSpot> getFishingSpot(FishingSpot tmp) {
        return fishingSpotRepository.findById(tmp.getId());

    }
}
