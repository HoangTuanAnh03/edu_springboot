package com.huce.edu.models.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedTopicDto {
	private Integer tid;
	private String topic;
	private Integer lid;
	int process;
}
