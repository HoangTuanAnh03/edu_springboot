package com.huce.edu.repositories;

import com.huce.edu.entities.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LevelRepo extends JpaRepository<LevelEntity, Integer> {
    LevelEntity findByLid(Integer lid);

}