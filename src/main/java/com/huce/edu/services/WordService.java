package com.huce.edu.services;

import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.dto.WordQuestion;

import java.util.ArrayList;

public interface WordService {
    ArrayList<WordQuestion> getQuestionByTid(int tid);

    ArrayList<WordQuestion> getTest(UserEntity user);
}
