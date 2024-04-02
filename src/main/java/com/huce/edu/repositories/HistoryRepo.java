package com.huce.edu.repositories;

import com.huce.edu.entities.HistoryEntity;
import com.huce.edu.entities.KeytokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryEntity, Integer> {
    HistoryEntity findFirstById(Integer id);

    List<HistoryEntity> findByUid(Integer uid);
    HistoryEntity findByWid(Integer wid);


}
