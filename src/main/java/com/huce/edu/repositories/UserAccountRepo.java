package com.huce.edu.repositories;

import com.huce.edu.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepo extends JpaRepository<UsersEntity, Integer> {
    boolean existsByEmail(String email);
    UsersEntity findFirstByUid(int id);
    UsersEntity findFirstByEmail(String email);

    Optional<UsersEntity> findByEmail(String email);

}
