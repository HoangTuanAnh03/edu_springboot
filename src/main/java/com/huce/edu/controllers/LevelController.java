package com.huce.edu.controllers;

import com.huce.edu.entities.HistoryEntity;
import com.huce.edu.entities.LevelEntity;
import com.huce.edu.entities.TopicEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.ApiResult;
import com.huce.edu.models.dto.LevelDto;
import com.huce.edu.repositories.*;
import com.huce.edu.services.LevelService;
import com.huce.edu.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/level")
@RequiredArgsConstructor
public class LevelController {
    private final UserAccountRepo userAccountRepo;
    private final LevelService levelService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResult<ArrayList<LevelDto>>> getAllLevels(HttpServletRequest request) {
         String email = BearerTokenUtil.getUserName(request);
        UserEntity user = userAccountRepo.findFirstByEmail(email);

        ApiResult<ArrayList<LevelDto>> result = ApiResult.create(HttpStatus.OK, "Lấy thành công level", levelService.getAll(user));
        return ResponseEntity.ok(result);
    }
}
