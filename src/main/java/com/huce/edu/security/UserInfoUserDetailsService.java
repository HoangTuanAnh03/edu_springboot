package com.huce.edu.security;

import com.huce.edu.entities.UsersEntity;
import com.huce.edu.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAccountRepo userAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsersEntity> user = userAccountRepo.findByEmail(email);

        return user.map(UserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found " + email));
    }
}
