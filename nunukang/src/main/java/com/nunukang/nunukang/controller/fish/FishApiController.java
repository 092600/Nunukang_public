package com.nunukang.nunukang.controller.fish;


import com.nunukang.nunukang.domain.fish.Fish;
import com.nunukang.nunukang.domain.fish.FishService;
import com.nunukang.nunukang.domain.fish.species.FishSpecies;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4/fish")
public class FishApiController {
    private final UserService userService;
    private final FishService fishService;

    @PostMapping("/picture")
    public Boolean fishPicture(@RequestBody Fish fish) {
       return fishService.saveFish(fish);
    }

    @GetMapping(value = "/pictures")
    public List<Fish> getPictures(@RequestParam("id") Long id){
        Optional<User> optionalUser = userService.findById(id);
        
        if (optionalUser.isPresent()) {
            return fishService.getFishs(optionalUser.get());
        } else {
            return null;
        }
    }

    @DeleteMapping("/picture")
    public Boolean deletePicture(@RequestParam("fishId") Long id){

        return fishService.deleteFish(id);
    }

    @GetMapping("/ranking")
    public List<Fish> getPictures(@RequestParam(name = "rankCount") Integer rankCount,
                                    @RequestParam("species") Integer species) {

        System.out.println(rankCount);
        System.out.println(species);
        List<Fish> fishs = fishService.getFishRankList(species, rankCount);

        for (Fish f : fishs) {
            System.out.println(f.getPictureName());
        }
        
        return fishs;
    }

}
