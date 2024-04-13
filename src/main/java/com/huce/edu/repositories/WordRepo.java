package com.huce.edu.repositories;

import com.huce.edu.entities.TopicEntity;
import com.huce.edu.entities.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WordRepo extends JpaRepository<WordEntity, Integer> {
    WordEntity findByWid(Integer wid);
    WordEntity findFirstByWid(Integer wid);
    List<WordId> findByTidIn(List<Integer> tids);
    boolean existsByWid(Integer wid);

    List<WordEntity> findByWord(String word);
    List<WordEntity> findByTid(Integer tid);
}

