package com.huce.edu.repositories;

import com.huce.edu.entities.AdminsEntity;
import com.huce.edu.entities.OrderdetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdminsRepo extends JpaRepository<AdminsEntity, Integer> {

    AdminsEntity findByEmail(String email);
    AdminsEntity findByAid(Integer aid);
    AdminsEntity findByName(String name);


}

