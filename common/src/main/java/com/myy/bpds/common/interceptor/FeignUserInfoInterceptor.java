package com.myy.bpds.common.interceptor;

import com.myy.bpds.common.constants.RequestHeaders;
import com.myy.bpds.common.utils.BpdsContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignUserInfoInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String userId = BpdsContextHolder.currentUserId();
        requestTemplate.header(RequestHeaders.USER_INFO, userId);
    }
}
