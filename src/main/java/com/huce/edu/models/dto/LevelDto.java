package com.huce.edu.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelDto {
	int lid;
	String levelName;
	Integer numTopics;
	Integer numWords;
	Integer process;
}
