package com.nunukang.nunukang.domain.fish;

import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import com.nunukang.nunukang.domain.fish.species.FishSpecies;

import java.util.stream.Stream;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FishService {
    private final UserRepository userRepository;

    private final FishRepository fishRepository;


    public Optional<Fish> findById(Long id) {
        return fishRepository.findById(id);
    }

    
    public Boolean saveFish(Fish fish) {
        Optional<User> user =  userRepository.findByEmail(fish.getFishingUser().getEmail());

        if (user.isPresent()) {
            fish.setFishingUser(user.get());
            fishRepository.save(fish);
            
            return true;
        } else {
            return false;
        }
        
    }

    public List<Fish> getFishs(User user) {
        return fishRepository.getFishs(user.getId());
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

    public List<Fish> getFishRankList(Integer species, Integer rankCount){
        try {
            FishSpecies fs = FishSpecies.getSpeciesName(species);

            return fishRepository.findAllBySpeciesOrderByFishSizeDesc(fs);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
}
