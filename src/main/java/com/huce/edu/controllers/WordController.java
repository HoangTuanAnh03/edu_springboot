package com.huce.edu.controllers;

import com.huce.edu.entities.*;
import com.huce.edu.models.ApiResult;
import com.huce.edu.models.dto.*;
import com.huce.edu.repositories.*;
import com.huce.edu.services.WordService;
import com.huce.edu.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/words")
@RequiredArgsConstructor
public class WordController {
	private final WordRepo wordRepo;
	private final TopicRepo topicRepo;
	private final UserAccountRepo userAccountRepo;
	private final HistoryRepo historyRepo;
	private final WordService wordService;

	@GetMapping("/getScrambleWord")
	public ResponseEntity<ApiResult<WordEntity>> getScrambleWord(){
		ArrayList<WordEntity> words = new ArrayList<>(wordRepo.findAll());
		Collections.shuffle(words);
		WordEntity w = words.get(0);
		return ResponseEntity.ok(ApiResult.create(HttpStatus.OK, "Lấy ScrambleWord thành công", w));
	}

	@GetMapping("/getQuestionByTid")
	public ResponseEntity<ApiResult<ArrayList<WordQuestion>>> getQuestionByTid(@RequestParam int tid){
		ApiResult<ArrayList<WordQuestion>> result;

		if(!topicRepo.existsByTid(tid)){
			result = ApiResult.create(HttpStatus.OK, "Topic không tồn tại", null);
			return ResponseEntity.ok(result);
		}

		result = ApiResult.create(HttpStatus.OK, "Lấy danh sách câu hỏi thành công", wordService.getQuestionByTid(tid));
		return ResponseEntity.ok(result);
	}

	@GetMapping("/getTest")
	public ResponseEntity<ApiResult<ArrayList<WordQuestion>>> getTestWords(HttpServletRequest request){
		ApiResult<ArrayList<WordQuestion>> result;
		String email = BearerTokenUtil.getUserName(request);
		UserEntity user = userAccountRepo.findFirstByEmail(email);

		ArrayList<WordQuestion> data = wordService.getTest(user);
		if(data == null) {
			result = ApiResult.create(HttpStatus.NOT_FOUND, "Bạn phải học trên 10 câu để truy cập", null);
			return ResponseEntity.ok(result);
		}

		result = ApiResult.create(HttpStatus.OK, "Lấy danh sách câu hỏi thành công", data);
		return ResponseEntity.ok(result);
	}

}