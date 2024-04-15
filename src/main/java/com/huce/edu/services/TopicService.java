package com.huce.edu.services;

import com.huce.edu.entities.TopicEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.dto.ListTopicByLevel;
import org.springframework.stereotype.Service;

@Service
public interface TopicService {
    ListTopicByLevel getTopicByLevel(int lid, UserEntity user);

    TopicEntity add(Integer lid, String name);

    TopicEntity edit(TopicEntity topicEntity);

    TopicEntity delete(Integer id);
}



