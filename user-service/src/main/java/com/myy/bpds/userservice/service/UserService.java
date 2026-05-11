package com.myy.bpds.userservice.service;

import com.myy.bpds.userservice.dto.LoginRequest;
import com.myy.bpds.userservice.dto.LoginResponse;
import com.myy.bpds.userservice.dto.RegisterRequest;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param request 登录请求
     * @return 登录响应（包含 JWT Token 和过期时间）
     */
    LoginResponse login(LoginRequest request);

    /**
     * 用户注册
     *
     * @param request 注册请求
     * @return 用户ID
     */
    String register(RegisterRequest request);
}
