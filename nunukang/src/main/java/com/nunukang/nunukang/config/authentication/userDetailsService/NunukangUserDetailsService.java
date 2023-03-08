package com.nunukang.nunukang.config.authentication.userDetailsService;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nunukang.nunukang.config.authentication.userDetails.NunukangUserDetails;

import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NunukangUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("didn't found user");
        }

        return new NunukangUserDetails(user.get());
    }
}
