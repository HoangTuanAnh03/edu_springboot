package com.huce.edu.repositories;

import com.huce.edu.entities.WordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepo extends JpaRepository<WordsEntity, Integer> {
}

