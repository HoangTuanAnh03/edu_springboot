package com.huce.edu.controllers;

import com.huce.edu.entities.HistoryEntity;
import com.huce.edu.entities.UsersEntity;
import com.huce.edu.models.ApiResult;
import com.huce.edu.models.dto.HistoryDto;
import com.huce.edu.models.dto.TopicDto;
import com.huce.edu.repositories.HistoryRepo;
import com.huce.edu.repositories.UserAccountRepo;
import com.huce.edu.repositories.WordRepo;
import com.huce.edu.services.HistoryService;
import com.huce.edu.services.Impls.HistoryServiceImpl;
import com.huce.edu.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.huce.edu.shareds.Constants.REWARD_PER;
import static com.huce.edu.shareds.Constants.TOKEN_REMAIN;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class CoinController {

	private final UserAccountRepo userAccountRepo;
	private final HistoryRepo historyRepo;
	private final WordRepo wordRepo;
	private final HistoryService historyService;
	//ĐỂ UPDATE COIN TRONG USER
	@GetMapping("/api/post")
	public ResponseEntity<ApiResult<Double>> sendAnswer(HttpServletRequest request, @RequestParam(defaultValue = "") String answer, @RequestParam(defaultValue = "1") int wid){
		ApiResult<Double> result;

		String email = BearerTokenUtil.getUserName(request);
		if(email == null){
			return ResponseEntity.ok(ApiResult.create(HttpStatus.BAD_REQUEST, "Cần Phải Đăng Nhập", 0.0));
		}
		UsersEntity user = userAccountRepo.findFirstByEmail(email);
		if(user == null){
			return ResponseEntity.ok(ApiResult.create(HttpStatus.BAD_REQUEST, "Token hết hạn", 0.0));
		}
	 	List<HistoryEntity> historyEntityList = historyRepo.findByUid(user.getUid());
		if(historyEntityList.stream().filter(t -> t.getWid() == wid).count() != 0){
			result = ApiResult.create(HttpStatus.BAD_REQUEST, "Câu này đã trả lời trước đây", 0.0);
		}else{
			if(wordRepo.findByWid(wid).getWord().toLowerCase().equals(answer.toLowerCase()))
			{
				result = ApiResult.create(HttpStatus.OK, "Nhận được xu", TOKEN_REMAIN*REWARD_PER);
				TOKEN_REMAIN = TOKEN_REMAIN - TOKEN_REMAIN*REWARD_PER;
				historyService.create(HistoryDto.create(user.getUid(), wid, 1));
			}
			else{
				result = ApiResult.create(HttpStatus.BAD_REQUEST, "Bạn đã trả lời sai", 0.0);
				historyService.create(HistoryDto.create(user.getUid(), wid, 0));
			}
		}
		return ResponseEntity.ok(result);
	}
}
