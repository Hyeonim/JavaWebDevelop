package com.yi.spring.config;

import com.yi.spring.entity.User;
import com.yi.spring.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        User exampleUser = new User();
        exampleUser.setUserId( insertedUserId );
        exampleUser.setUserStartDate(null);
        Optional<User> findOne = userRepository.findOne( Example.of(exampleUser) );

        User member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getUserId())
                .password(member.getUserPassword())
                .roles(member.getUserAuth())
                .build();
    }
}