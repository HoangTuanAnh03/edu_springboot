package com.huce.edu.controllers;

import com.huce.edu.entities.KeytokenEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.ApiResult;
import com.huce.edu.models.dto.AuthRequest;
import com.huce.edu.models.dto.UserInfo;
import com.huce.edu.models.response.LoginResponse;
import com.huce.edu.models.response.TokenResponse;
import com.huce.edu.repositories.KeyRepo;
import com.huce.edu.repositories.UserAccountRepo;
import com.huce.edu.security.JwtService;
import com.huce.edu.utils.BearerTokenUtil;
import com.huce.edu.utils.GenerateKeyUtil;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class AuthController {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserAccountRepo userAccountRepo;
    private final KeyRepo keyRepo;

    @GetMapping("/verifyRefreshToken")
    public ResponseEntity<ApiResult<TokenResponse>> verifyUser(@RequestParam(name = "token") String token) {
        ApiResult<TokenResponse> result = null;

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();

        String username = jsonObject.get("sub").getAsString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserEntity user = userAccountRepo.findFirstByEmail(username);
        KeytokenEntity keyByUser = keyRepo.findFirstByUid(user.getUid());
        try {
            if (jwtService.validateToken(token, keyByUser.getPrivatekey(), userDetails)) {
                // Tạo lại AccessToken và RefreshToken
                String refreshToken = jwtService.generateRefreshToken(user.getEmail(), keyByUser.getPrivatekey());
                TokenResponse tokens = TokenResponse.create(
                        jwtService.generateAccessToken(user.getEmail(), keyByUser.getPublickey()),
                        refreshToken
                );

                keyByUser.setRefreshtoken(refreshToken);
                keyRepo.save(keyByUser);


                result = ApiResult.create(HttpStatus.OK, "Cấp lại AccessToken và RefreshToken thành công!!", tokens);
            }
        } catch (Exception ex) {
            result = ApiResult.create(HttpStatus.BAD_REQUEST, "RefreshToken sai!", null);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<ApiResult<LoginResponse>> login(@RequestBody AuthRequest authRequest) {

        ApiResult<LoginResponse> result = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                UserEntity user = userAccountRepo.findFirstByEmail(authRequest.getEmail());
                if (user != null && user.getStatus() == 1) {
                    String privateKey = GenerateKeyUtil.generate();
                    String publicKey = GenerateKeyUtil.generate();
                    /* Thêm vào bảng key */
                    // Kiểm tra xem đã tồn tại userID trong bảng Key chưa
                    KeytokenEntity keyByUserId = keyRepo.findFirstByUid(user.getUid());

                    if (keyByUserId != null) {
                        keyRepo.delete(keyByUserId);
                    }
                    String refreshToken = jwtService.generateRefreshToken(authRequest.getEmail(), privateKey);

                    KeytokenEntity newKey = KeytokenEntity.create(
                            user.getUid(),
                            privateKey,
                            publicKey,
                            refreshToken
                    );
                    keyRepo.save(newKey);

                    // lấy key ra
                    KeytokenEntity keyByUser = keyRepo.findFirstByUid(user.getUid());
                    UserInfo userInfo = UserInfo.create(
                            user.getUid(),
                            user.getEmail(),
                            user.getName()
                    );

                    TokenResponse tokens = TokenResponse.create(
                            jwtService.generateAccessToken(authRequest.getEmail(), keyByUser.getPublickey()),
                            refreshToken
                    );

                    LoginResponse loginResponse = LoginResponse.create(
                            userInfo,
                            tokens
                    );

                    result = ApiResult.create(HttpStatus.OK, "Đăng nhập thành công!!", loginResponse);
                    return ResponseEntity.ok(result);
                }
                result = ApiResult.create(HttpStatus.BAD_REQUEST, "tài khoản chưa được kích hoạt!!", null);
            }
        } catch (Exception ex) {
            result = ApiResult.create(HttpStatus.BAD_REQUEST, "Sai tên đăng nhập hoặc mật khẩu!!", null);
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping("/sign-out")
    public ResponseEntity<ApiResult<?>> logout(HttpServletRequest request) {
        ApiResult<?> result = null;
        String token = BearerTokenUtil.getToken(request);
        String username = BearerTokenUtil.getUserName(request);

        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UserEntity user = userAccountRepo.findFirstByEmail(username);
            KeytokenEntity keyByUser = keyRepo.findFirstByUid(user.getUid());
            if (jwtService.validateToken(token, keyByUser.getPublickey(), userDetails)) {
                keyRepo.delete(keyByUser);
                result = ApiResult.create(HttpStatus.OK, "logout Success!!", username);
            } else {
                result = ApiResult.create(HttpStatus.BAD_REQUEST, "Token không đúng!!", username);
            }
        }

        return ResponseEntity.ok(result);
    }
}
