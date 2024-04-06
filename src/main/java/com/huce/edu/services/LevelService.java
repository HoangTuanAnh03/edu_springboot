package com.huce.edu.services;

import com.huce.edu.entities.TopicEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.dto.LevelDto;
import com.huce.edu.models.dto.ListTopicByLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface LevelService {
    ListTopicByLevel getTopicByLevel(int lid, UserEntity user);
    ArrayList<LevelDto> getAll(UserEntity user);
}

