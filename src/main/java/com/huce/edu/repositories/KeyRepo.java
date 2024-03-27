package com.huce.edu.repositories;

import com.huce.edu.entities.KeytokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeyRepo extends JpaRepository<KeytokenEntity, Integer> {
    KeytokenEntity findFirstByUid(int userId);
}
