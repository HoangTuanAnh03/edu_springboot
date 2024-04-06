package com.huce.edu.controllers;


import com.huce.edu.entities.LevelEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.ApiResult;
import com.huce.edu.models.dto.ListTopicByLevel;
import com.huce.edu.repositories.*;
import com.huce.edu.services.LevelService;
import com.huce.edu.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/topic")
@RequiredArgsConstructor
public class TopicController {

    private final WordRepo wordRepo;
    private final TopicRepo topicRepo;
    private final UserAccountRepo userAccountRepo;
    private final HistoryRepo historyRepo;
    private final LevelRepo levelRepo;
    private final LevelService levelService;

    @GetMapping("/getTopicByLid")
    public ResponseEntity<ApiResult<ListTopicByLevel>> getTopicByLid(HttpServletRequest request, @RequestParam(defaultValue = "1") int lid) {
        ApiResult<ListTopicByLevel> result;
        LevelEntity level = levelRepo.findByLid(lid);
        if (level == null) {
            result = ApiResult.create(HttpStatus.BAD_REQUEST, "Không tìm thấy level " + lid, null);
            return ResponseEntity.ok(result);
        }

        String email = BearerTokenUtil.getUserName(request);
        UserEntity user = userAccountRepo.findFirstByEmail(email);
        result = ApiResult.create(HttpStatus.OK, "Lấy danh sách chủ đề thành công", levelService.getTopicByLevel(lid, user));
        return ResponseEntity.ok(result);
    }
}
