package com.myy.bpds.userservice.service.impl;

import com.myy.bpds.common.dto.UserInfo;
import com.myy.bpds.common.exception.BpdsException;
import com.myy.bpds.common.utils.JwtUtils;
import com.myy.bpds.userservice.constants.UserErrorCode;
import com.myy.bpds.userservice.dao.UserDao;
import com.myy.bpds.userservice.dto.LoginRequest;
import com.myy.bpds.userservice.dto.LoginResponse;
import com.myy.bpds.userservice.dto.RegisterRequest;
import com.myy.bpds.userservice.entity.UserEntity;
import com.myy.bpds.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 0. 参数校验
        if (StringUtils.isAnyBlank(request.getUsername(), request.getPassword())) {
            throw new BpdsException(UserErrorCode.USERNAME_OR_PASSWORD_EMPTY);
        }
        // 1. 查询用户
        UserEntity user = userDao.getOne(w -> w.eq(UserEntity::getUsername, request.getUsername()));
        if (user == null) {
            throw new BpdsException(UserErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }
        // 2. 验证密码
        String encryptedPassword = encryptPassword(request.getPassword());
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new BpdsException(UserErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }
        // 3. 检查用户状态
        if (user.getStatus() != null && user.getStatus() == 0) {
            throw new BpdsException(UserErrorCode.USER_DISABLED);
        }
        // 4. 生成 JWT Token
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUsername(user.getUsername());
        String token = JwtUtils.generateToken(userInfo);

        // 5. 构建响应
        return LoginResponse.builder().token(token).expiresIn(7 * 24 * 60 * 60 * 1000L).build();
    }

    @Override
    @Transactional
    public String register(RegisterRequest request) {
        // 1. 检查用户名是否已存在
        boolean exists = userDao.exists(
                w -> w.eq(UserEntity::getUsername, request.getUsername()));
        if (exists) {
            throw new BpdsException(UserErrorCode.USER_ALREADY_EXISTS);
        }

        // 2. 创建新用户
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(encryptPassword(request.getPassword()));
        user.setStatus(1); // 正常状态

        userDao.save(user);

        return user.getId();
    }

    /**
     * 密码加密（MD5）
     */
    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }
}
