package com.myy.bpds.gatewayservice.router;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicRouteLoader {
    private final NacosConfigManager nacosConfigManager;
    private final RouteDefinitionWriter routeDefinitionWriter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String DATA_ID = "gateway-route.json";
    private static final String GROUP = "DEFAULT_GROUP";

    private Set<String> routeIds = new HashSet<>();

    @PostConstruct
    public void init() throws NacosException {
        String configInfo = nacosConfigManager.getConfigService()
                .getConfigAndSignListener(DATA_ID, GROUP, 5000, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        updateRoute(configInfo);
                    }
                });
        updateRoute(configInfo);
    }

    public void updateRoute(String configInfo) {
        try {
            List<RouteDefinition> routeDefinitions = objectMapper.readValue(configInfo, new TypeReference<>() {
            });
            for (String routeId : routeIds) {
                routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
            }
            routeIds.clear();
            for (RouteDefinition routeDefinition : routeDefinitions) {
                routeIds.add(routeDefinition.getId());
                routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
                log.info("路由更新成功: id={}, uri={}, predicates={}", routeDefinition.getId(),
                        routeDefinition.getUri(), routeDefinition.getPredicates());
            }
        } catch (Exception e) {
            log.error("更新路由配置失败", e);
        }
    }
}
