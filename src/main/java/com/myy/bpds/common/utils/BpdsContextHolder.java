package com.myy.bpds.common.utils;

import com.myy.bpds.common.dto.BpdsContext;
import org.springframework.core.NamedThreadLocal;

public class BpdsContextHolder {
    private static final ThreadLocal<BpdsContext> THREAD_LOCAL = new NamedThreadLocal<>("BpdsContext");

    public static BpdsContext get() {
        return THREAD_LOCAL.get();
    }

    public static void set(BpdsContext context) {
        THREAD_LOCAL.set(context);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
