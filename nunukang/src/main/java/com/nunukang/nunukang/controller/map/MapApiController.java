package com.nunukang.nunukang.controller.map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nunukang.nunukang.domain.fishingSpot.FishingSpot;

import lombok.RequiredArgsConstructor;

import java.util.List;
import com.nunukang.nunukang.domain.fishingSpot.FishingSpotService;
import com.nunukang.nunukang.domain.fishingSpot.type.FishingType;

@RestController
@RequestMapping("/api/v4/map")
@RequiredArgsConstructor
public class MapApiController {

    private final FishingSpotService fishingSpotService;

    @GetMapping("/fisingSpot")
    public List<FishingSpot> getFishingSpots(@RequestParam("type") FishingType type) {
        
        return fishingSpotService.getFishingSpotByType(type);
    }
}
