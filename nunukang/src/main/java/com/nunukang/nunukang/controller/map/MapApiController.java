package com.nunukang.nunukang.controller.map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nunukang.nunukang.config.authentication.userDetails.NunukangUserDetails;
import com.nunukang.nunukang.domain.fishingSpot.FishingSpot;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import com.nunukang.nunukang.domain.fishingSpot.FishingSpotService;
import com.nunukang.nunukang.domain.fishingSpot.type.FishingType;
import com.nunukang.nunukang.domain.user.UserService;
import com.nunukang.nunukang.domain.user.User;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/v4/map")
@RequiredArgsConstructor
public class MapApiController {
    
    private final UserService userService;
    private final FishingSpotService fishingSpotService;

    @GetMapping("/fisingSpot")
    public List<FishingSpot> getFishingSpots(@RequestParam("type") FishingType type) {
        
        return fishingSpotService.getFishingSpotByType(type);
    }

    @PostMapping("/fisingSpot/favorite")
    public boolean favoriteSpot(@RequestBody FishingSpot tmp, Authentication auth) {
        Optional<FishingSpot> spot = fishingSpotService.getFishingSpot(tmp);

        try {
            if (spot.isPresent()) {

                NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
                User user = userService.findById(nud.getUser().getId()).get();
    
                userService.favoriteSpot(spot.get().getId(), user);
                
                return true;
            }

            return false;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
                
    }


    @DeleteMapping("/fisingSpot/favorite")
    public boolean unfavoriteSpot(@RequestBody FishingSpot tmp, Authentication auth) {
        Optional<FishingSpot> spot = fishingSpotService.getFishingSpot(tmp);

        try {
            if (spot.isPresent()) {

                NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
                User user = userService.findById(nud.getUser().getId()).get();
    
                userService.unfavoriteSpot(spot.get().getId(), user);
                
                return true;
            }

            return false;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
                
    }


    @GetMapping(value = "/fishingSpot/favorite")
    public List<Long> getFavoriteSpots(Authentication auth) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User user = userService.findById(nud.getUser().getId()).get();
        
        return userService.getFavoriteSpots(user);
    }


}
