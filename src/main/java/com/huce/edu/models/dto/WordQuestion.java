package com.huce.edu.models.dto;

import com.huce.edu.entities.WordsEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordQuestion {
	WordsEntity words;
	String answerA;
	String answerB;
	String answerC;
	String answerD;
}
