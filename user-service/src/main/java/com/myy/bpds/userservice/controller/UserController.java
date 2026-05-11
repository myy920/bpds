package com.myy.bpds.userservice.controller;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.userservice.dto.LoginRequest;
import com.myy.bpds.userservice.dto.LoginResponse;
import com.myy.bpds.userservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        LoginResponse loginResponse = userService.login(request);
        // 设置 Cookie
        ResponseCookie cookie = ResponseCookie.from("Authorization", loginResponse.getToken())
                .httpOnly(true)           // 防止 XSS 攻击
                .secure(false)            // 生产环境应设为 true（HTTPS）
                .path("/")                // Cookie 有效路径
                .maxAge(Duration.ofDays(7)) // 有效期 7 天
                .sameSite("Lax")          // CSRF 保护
                .build();
        
        response.addHeader("Set-Cookie", cookie.toString());
        return Result.ok(loginResponse);
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<String> register(@RequestParam String username, @RequestParam String password) {
        String userId = userService.register(username, password);
        return Result.ok(userId);
    }
}
