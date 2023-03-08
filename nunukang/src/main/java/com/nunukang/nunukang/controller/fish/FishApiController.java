package com.nunukang.nunukang.controller.fish;


import com.nunukang.nunukang.domain.fish.Fish;
import com.nunukang.nunukang.domain.fish.FishService;
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
    public List<Fish> getPictures(@RequestParam("email") String email){
        Optional<User> optionalUser = userService.findByEmail(email);


        if (optionalUser.isPresent()) {
            System.out.println(optionalUser.get().getFishs());
            System.out.println("hi");

            for (Fish f : optionalUser.get().getFishs()) {
                System.out.println(f.getFishSize());
                System.out.println(f.getPictureName());
            }
            
            return optionalUser.get().getFishs();
        } else {
        return null;
        }
    }

    @DeleteMapping("/picture")
    public Boolean deletePicture(@RequestParam("fishId") Long id){
        System.out.println(id);
        
        return fishService.deleteFish(id);
    }
}
