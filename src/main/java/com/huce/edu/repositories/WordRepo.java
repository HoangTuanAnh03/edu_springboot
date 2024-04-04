package com.huce.edu.repositories;

import com.huce.edu.entities.WordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepo extends JpaRepository<WordsEntity, Integer> {
    WordsEntity findByWid(Integer wid);
    List<WordsEntity> findByWord(String word);
    List<WordsEntity> findByTid(Integer tid);
}

