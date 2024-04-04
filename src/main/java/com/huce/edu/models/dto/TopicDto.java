package com.huce.edu.models.dto;

import com.huce.edu.entities.TopicsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
	LevelDto levelDto;
	ArrayList<ExtendedTopicDto> listTopics;
}
