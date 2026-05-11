package com.myy.bpds.common.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myy.bpds.common.constants.RequestHeaders;
import com.myy.bpds.common.dto.UserInfo;
import com.myy.bpds.common.utils.BpdsContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignUserInfoInterceptor implements RequestInterceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfo userInfo = BpdsContextHolder.currentUserInfo();
        if (userInfo != null) {
            try {
                String userInfoJson = objectMapper.writeValueAsString(userInfo);
                requestTemplate.header(RequestHeaders.USER_INFO, userInfoJson);
            } catch (JsonProcessingException e) {
                log.error("序列化用户信息失败", e);
            }
        }
    }
}
