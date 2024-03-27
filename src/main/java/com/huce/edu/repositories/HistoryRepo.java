package com.huce.edu.repositories;

import com.huce.edu.entities.HistoryEntity;
import com.huce.edu.entities.KeytokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryEntity, Integer> {
}
