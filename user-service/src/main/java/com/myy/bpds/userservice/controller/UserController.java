package com.myy.bpds.userservice.controller;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.userservice.dto.LoginRequest;
import com.myy.bpds.userservice.dto.LoginResponse;
import com.myy.bpds.userservice.dto.RegisterRequest;
import com.myy.bpds.userservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<Void> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        LoginResponse loginResponse = userService.login(request);
        // 设置 Cookie
        ResponseCookie cookie = ResponseCookie.from("Authorization", loginResponse.getToken())
                .httpOnly(true)           // 防止 XSS 攻击
                .secure(false)            // 生产环境应设为 true（HTTPS）
                .path("/")                // Cookie 有效路径
                .maxAge(loginResponse.getExpiresIn()) // 有效期
                .sameSite("Lax")          // CSRF 保护
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return Result.ok();
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterRequest request) {
        String userId = userService.register(request);
        return Result.ok(userId);
    }
}
