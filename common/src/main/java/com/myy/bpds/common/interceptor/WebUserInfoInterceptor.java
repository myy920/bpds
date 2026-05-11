package com.myy.bpds.common.interceptor;

import com.myy.bpds.common.constants.RequestHeaders;
import com.myy.bpds.common.dto.BpdsContext;
import com.myy.bpds.common.utils.BpdsContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

public class WebUserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        String userInfo = request.getHeader(RequestHeaders.USER_INFO);
        if (StringUtils.isNotBlank(userInfo)) {
            BpdsContext bpdsContext = new BpdsContext();
            BpdsContextHolder.set(bpdsContext);
        }
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, @Nullable Exception ex) throws Exception {
        BpdsContextHolder.clear();
    }
}
