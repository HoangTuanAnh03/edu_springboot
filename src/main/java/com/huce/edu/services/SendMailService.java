package com.huce.edu.services;


import com.huce.edu.entities.UsersEntity;
import org.springframework.stereotype.Service;


@Service
public interface SendMailService {

    void forgetPasswordUser(UsersEntity user, String otp);

    void registerUser(UsersEntity user, String verificationCode);
}
