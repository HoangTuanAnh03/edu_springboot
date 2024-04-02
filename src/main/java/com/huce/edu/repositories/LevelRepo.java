package com.huce.edu.repositories;

import com.huce.edu.entities.LevelsEntity;
import com.huce.edu.entities.OrderdetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LevelRepo extends JpaRepository<LevelsEntity, Integer> {
    LevelsEntity findByLid(Integer lid);

}