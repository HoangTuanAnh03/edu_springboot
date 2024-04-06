package com.huce.edu.repositories;

import com.huce.edu.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepo extends JpaRepository<TopicEntity, Integer> {
    TopicEntity findByTid(Integer tid);
    TopicEntity findFirstByTid(Integer tid);

    boolean existsByTid(Integer tid);
    List<TopicEntity> findByLid(Integer lid);
    TopicEntity findByTopic(String id);
}