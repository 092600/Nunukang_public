package com.nunukang.nunukang.config.authentication;



import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NunukangAuthenticationManager implements AuthenticationManager {
    
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println(email +", "+ password);

        System.out.println("authenticate");
        UserDetails user = userDetailsService.loadUserByUsername(email);

        if (passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("hi");
            return new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
        } else {
            System.out.println("not matched");
            throw new BadCredentialsException("isn't authenticated");
        }
    }

}
