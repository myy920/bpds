package com.myy.bpds.common.utils;

import com.myy.bpds.common.dto.BpdsContext;
import com.myy.bpds.common.dto.UserInfo;
import org.springframework.core.NamedThreadLocal;

public class BpdsContextHolder {
    private static final ThreadLocal<BpdsContext> THREAD_LOCAL = new NamedThreadLocal<>("BpdsContext");

    public static BpdsContext get() {
        BpdsContext bpdsContext = THREAD_LOCAL.get();
        if (bpdsContext == null) {
            THREAD_LOCAL.set(bpdsContext = new BpdsContext());
        }
        return bpdsContext;
    }

    public static void set(BpdsContext context) {
        THREAD_LOCAL.set(context);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static String currentUserId() {
        BpdsContext bpdsContext = get();
        UserInfo userInfo = bpdsContext.getUserInfo();
        String userId = userInfo == null ? null : userInfo.getUserId();
        userId = userId == null ? "System" : userId;
        return userId;
    }

    public static UserInfo currentUserInfo() {
        BpdsContext bpdsContext = get();
        return bpdsContext.getUserInfo();
    }
}
