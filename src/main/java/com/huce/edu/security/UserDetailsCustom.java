package com.huce.edu.security;

import com.huce.edu.entities.AdminsEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.repositories.AdminsRepo;
import com.huce.edu.repositories.UserAccountRepo;
import com.huce.edu.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component("userDetailsService")
@AllArgsConstructor
public class UserDetailsCustom implements UserDetailsService {
    private final UserAccountRepo userAccountRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userAccountRepo.findFirstByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username/password không hợp lệ");
        }

        return new User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>());
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}