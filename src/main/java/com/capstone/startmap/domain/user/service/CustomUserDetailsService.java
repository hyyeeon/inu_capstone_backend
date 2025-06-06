package com.capstone.startmap.domain.user.service;


import com.capstone.startmap.config.CustomUserDetails;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> DB에 존재하지 않습니다."));
        System.out.println("User loaded: " + user.getEmail());
        return new CustomUserDetails(user.getUser_id(), user.getEmail(), user.getPassword(), new ArrayList<>());
    }

}
