package com.huce.edu.repositories;

import com.huce.edu.entities.TesthistoryEntity;
import com.huce.edu.entities.TopicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends JpaRepository<TopicsEntity, Integer> {
    TopicsEntity findByTid(Integer tid);
    TopicsEntity findByLid(Integer lid);
    TopicsEntity findByTopic(String id);
}