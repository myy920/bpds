package com.myy.bpds.userservice.service;

import com.myy.bpds.userservice.dto.LoginRequest;
import com.myy.bpds.userservice.dto.LoginResponse;

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
     * @param username 用户名
     * @param password 密码
     * @return 用户ID
     */
    String register(String username, String password);
}
