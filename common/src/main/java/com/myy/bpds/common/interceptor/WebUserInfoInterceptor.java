package com.myy.bpds.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myy.bpds.common.constants.RequestHeaders;
import com.myy.bpds.common.dto.BpdsContext;
import com.myy.bpds.common.dto.UserInfo;
import com.myy.bpds.common.utils.BpdsContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class WebUserInfoInterceptor implements HandlerInterceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        String userInfoJson = request.getHeader(RequestHeaders.USER_INFO);
        if (StringUtils.isNotBlank(userInfoJson)) {
            try {
                UserInfo userInfo = objectMapper.readValue(userInfoJson, UserInfo.class);
                BpdsContext bpdsContext = new BpdsContext();
                bpdsContext.setUserInfo(userInfo);
                BpdsContextHolder.set(bpdsContext);
                log.debug("用户信息已设置到上下文: userId={}", userInfo.getUserId());
            } catch (Exception e) {
                log.error("解析用户信息失败: {}", userInfoJson, e);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, @Nullable Exception ex) throws Exception {
        BpdsContextHolder.clear();
    }
}
