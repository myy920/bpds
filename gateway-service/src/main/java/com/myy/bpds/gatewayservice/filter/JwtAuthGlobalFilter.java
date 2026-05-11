package com.myy.bpds.gatewayservice.filter;

import com.myy.bpds.common.constants.RequestHeaders;
import com.myy.bpds.common.dto.UserInfo;
import com.myy.bpds.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class JwtAuthGlobalFilter implements GlobalFilter, Ordered {

    // 不需要认证的路径
    private static final List<String> EXCLUDE_PATHS = List.of(
            "/gateway-service/user/login",  // 登录接口
            "/gateway-service/user/register" // 注册接口
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        // 1. 检查是否是排除路径
        if (isExclude(path)) {
            log.debug("跳过认证: {}", path);
            return chain.filter(exchange);
        }

        // 2. 获取 Token
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get(RequestHeaders.AUTHORIZATION);
        String token = null;
        if (CollectionUtils.isNotEmpty(authorization)) {
            token = authorization.get(0);
        }

        // 3. 验证 Token
        boolean isValid = StringUtils.isNotBlank(token);
        UserInfo userInfo = null;
        if (isValid) {
            try {
                userInfo = JwtUtils.claimsToUserInfo(JwtUtils.parseToken(token));
            } catch (Exception ignore) {
                isValid = false;
            }
        }
        if (!isValid) {
            log.warn("Token 无效或已过期: {}", path);
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }


        // 4. 将用户信息传递到下游服务
        String userInfoJson = String.format("{\"userId\":\"%s\",\"username\":\"%s\"}", 
                userInfo.getUserId(), userInfo.getUsername());
        log.debug("用户认证成功: userId={}", userInfo.getUserId());
        
        // 将用户信息添加到请求头
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder
                        .header(RequestHeaders.USER_INFO, userInfoJson)
                )
                .build();
        return chain.filter(swe);
    }

    /**
     * 判断是否是排除路径
     */
    private boolean isExclude(String requestPath) {
        return EXCLUDE_PATHS.stream().anyMatch(requestPath::startsWith);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
