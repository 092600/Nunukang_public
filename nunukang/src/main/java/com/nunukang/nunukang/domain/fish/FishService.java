package com.nunukang.nunukang.domain.fish;

import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FishService {
    private final UserRepository userRepository;

    private final FishRepository fishRepository;

    public Boolean saveFish(Fish fish) {
        Optional<User> user =  userRepository.findByEmail(fish.getFishingUser().getEmail());

        if (user.isPresent()) {
            user.get().getFishs().add(fish);
            fish.setFishingUser(user.get());

            fishRepository.save(fish);
            
            return true;
        } else {
            return false;
        }
        
    }

    public Boolean deleteFish(Long id) {
        try {
            Optional<Fish> optionalFish = fishRepository.findById(id);

            if (optionalFish.isPresent()) {
                fishRepository.delete(optionalFish.get());

                return true;

            } else {
                
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
