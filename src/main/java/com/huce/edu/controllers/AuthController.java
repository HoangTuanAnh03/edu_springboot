package com.huce.edu.controllers;

import com.huce.edu.models.dto.AuthRequest;
import com.huce.edu.models.dto.UserInfo;
import com.huce.edu.models.response.LoginResponse;
import com.huce.edu.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;

    @PostMapping("/sign-in")
    public ResponseEntity<String> login( @RequestBody AuthRequest authRequest) {
        // Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());

        // xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // create a token
        String access_token = securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        UserInfo userInfo = UserInfo.create(user.getUid(), user.getEmail(), user.getName());
//        LoginResponse loginResponse = LoginResponse.create(userInfo, access_token);

        return ResponseEntity.ok().body(access_token);
    }

//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//    private final AuthenticationManager authenticationManager;
//    private final UserAccountRepo userAccountRepo;
//    private final KeyRepo keyRepo;
//
//    @GetMapping("/verifyRefreshToken")
//    public ResponseEntity<ApiResult<TokenResponse>> verifyUser(@RequestParam(name = "token") String token) {
//        ApiResult<TokenResponse> result = null;
//        String username = BearerTokenUtil.getUserName(token);
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UserEntity user = userAccountRepo.findFirstByEmail(username);
//        if (user == null) {
//            result = ApiResult.create(HttpStatus.BAD_REQUEST, "Không tồn tại user!!", null);
//            return ResponseEntity.ok(result);
//        }
//        try {
//            KeytokenEntity keyByUser = keyRepo.findFirstByUid(user.getUid());
//            if (jwtService.validateToken(token, keyByUser.getPrivatekey(), userDetails)) {
//                // Tạo lại AccessToken và RefreshToken
//                TokenResponse tokens = TokenResponse.create(
//                        jwtService.generateAccessToken(user.getEmail(), keyByUser.getPublickey()),
//                        jwtService.generateRefreshToken(user.getEmail(), keyByUser.getPrivatekey())
//                );
////                keyByUser.setRefreshtoken(refreshToken);
//                keyRepo.save(keyByUser);
//                result = ApiResult.create(HttpStatus.OK, "Cấp lại AccessToken và RefreshToken thành công!!", tokens);
//            }
//        } catch (Exception ex) {
//            result = ApiResult.create(HttpStatus.BAD_REQUEST, "RefreshToken sai!", null);
//        }
//        return ResponseEntity.ok(result);
//    }
//
//
//    @PostMapping("/sign-in")
//    public ResponseEntity<ApiResult<LoginResponse>> login(@RequestBody AuthRequest authRequest) {
//
//        ApiResult<LoginResponse> result = null;
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
//
//            if (authentication.isAuthenticated()) {
//                UserEntity user = userAccountRepo.findFirstByEmail(authRequest.getEmail());
//                if (user != null) {
//                    String privateKey;
//                    String publicKey;
//                    TokenResponse tokens;
//                    KeytokenEntity keyByUser = keyRepo.findFirstByUid(user.getUid());
//
//                    // Tạo mơi
//                    if (keyByUser == null) {
//                        publicKey = GenerateKeyUtil.generate();
//                        privateKey = GenerateKeyUtil.generate();
//                        KeytokenEntity newKey = KeytokenEntity.create(
//                                0,
//                                user.getUid(),
//                                null,
//                                privateKey,
//                                publicKey,
//                                null
//                        );
//                        keyRepo.save(newKey);
//                        tokens = TokenResponse.create(
//                                jwtService.generateAccessToken(authRequest.getEmail(), publicKey),
//                                jwtService.generateRefreshToken(authRequest.getEmail(), privateKey)
//                        );
//                    } else {
//                        // lấy key ra
//                        KeytokenEntity keyByAdmin = keyRepo.findFirstByUid(user.getUid());
//                        tokens = TokenResponse.create(
//                                jwtService.generateAccessToken(authRequest.getEmail(), keyByAdmin.getPublickey()),
//                                jwtService.generateRefreshToken(authRequest.getEmail(), keyByAdmin.getPrivatekey())
//                        );
//                    }
//                    UserInfo userInfo = UserInfo.create(user.getUid(), user.getEmail(), user.getName());
//
//                    LoginResponse loginResponse = LoginResponse.create(userInfo, tokens);
//
//                    result = ApiResult.create(HttpStatus.OK, "Đăng nhập thành công!!", loginResponse);
//                    return ResponseEntity.ok(result);
//                }
//                result = ApiResult.create(HttpStatus.BAD_REQUEST, "tài khoản chưa được kích hoạt!!", null);
//            }
//        } catch (Exception ex) {
//            result = ApiResult.create(HttpStatus.BAD_REQUEST, "Sai tên đăng nhập hoặc mật khẩu!!", null);
//            return ResponseEntity.ok(result);
//        }
//        return ResponseEntity.ok(result);
//    }


//    @GetMapping("/sign-out")
//    public ResponseEntity<ApiResult<?>> logout(HttpServletRequest request) {
//        ApiResult<?> result = null;
//        String token = BearerTokenUtil.getToken(request);
//        String username = BearerTokenUtil.getUserName(request);
//
//        if (username != null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            UserEntity user = userAccountRepo.findFirstByEmail(username);
//            KeytokenEntity keyByUser = keyRepo.findFirstByUid(user.getUid());
//            if (jwtService.validateToken(token, keyByUser.getPublickey(), userDetails)) {
//                keyRepo.delete(keyByUser);
//                result = ApiResult.create(HttpStatus.OK, "logout Success!!", username);
//            } else {
//                result = ApiResult.create(HttpStatus.BAD_REQUEST, "Token không đúng!!", username);
//            }
//        }
//
//        return ResponseEntity.ok(result);
//    }
}
