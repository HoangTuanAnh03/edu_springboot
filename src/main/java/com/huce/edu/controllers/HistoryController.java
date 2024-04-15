package com.huce.edu.controllers;


import com.huce.edu.entities.TestHistoryEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.ApiResult;
import com.huce.edu.repositories.*;
import com.huce.edu.shareds.Constants;
import com.huce.edu.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final UserAccountRepo userAccountRepo;
    private final TestHistoryRepo testHistoryRepo;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResult<List<TestHistoryEntity>>> getAll(HttpServletRequest request) {
        String email = BearerTokenUtil.getUserName(request);
        UserEntity user = userAccountRepo.findFirstByEmail(email);

        ApiResult<List<TestHistoryEntity>> result = ApiResult.create(HttpStatus.OK, "Lấy danh sách bai Test thành công", testHistoryRepo.findByUid(user.getUid()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResult<TestHistoryEntity>> add(HttpServletRequest request, @RequestParam int numQues, @RequestParam int numCorrectQues) {
        String email = BearerTokenUtil.getUserName(request);
        UserEntity user = userAccountRepo.findFirstByEmail(email);

        TestHistoryEntity testHistoryEntity = TestHistoryEntity.create(
                0,
                user.getUid(),
                numQues,
                numCorrectQues,
                Constants.getCurrentDay()
        );
        testHistoryRepo.save(testHistoryEntity);

        ApiResult<TestHistoryEntity> result = ApiResult.create(HttpStatus.OK, "Lấy danh sách bai Test thành công", testHistoryEntity);
        return ResponseEntity.ok(result);
    }
}
