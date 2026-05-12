# BPDS - 业务平台分布式系统

> 基于 Spring Cloud Alibaba 的微服务架构项目

##  项目简介

BPDS（Business Platform Distributed System）是一个基于 Spring Cloud Alibaba 的分布式微服务系统，采用前后端分离架构，提供完整的业务平台解决方案。

## 🏗️ 系统架构

```
┌─────────────────────────────────────────────┐
│              API Gateway (8000)              │
│          (gateway-service)                   │
└─────────────┬───────────────────────────────┘
              │
    ┌─────────┼─────────┬──────────┬──────────┐
    │         │         │          │          │
    ▼         ▼         ▼          ▼          ▼
┌──────── ┌────────┐ ┌────────┐ ┌────────┐ ┌────────┐
│  User  │ │  Cart  │ │  Item  │ │Project │ │ Order  │
│Service │ │Service │ │Service │ │Service │ │Service │
└────────┘ └────────┘ └────────┘ └────────┘ └────────┘
    │         │         │          │          │
    └─────────┴─────────┴──────────┴──────────┘
              │
    ┌─────────▼──────────┐
    │   Nacos Server     │
    │  服务注册与配置中心  │
    └────────────────────┘
```

## 🛠️ 技术栈

| 组件 | 版本 | 说明 |
|------|------|------|
| **Spring Boot** | 3.5.0 | 应用框架 |
| **Spring Cloud** | 2025.0.0 | 微服务框架 |
| **Spring Cloud Alibaba** | 2025.0.0.0 | 阿里云微服务套件 |
| **Nacos** | 3.0.3 | 服务注册与配置中心 |
| **Sentinel** | 1.8.9 | 流量控制与熔断降级 |
| **Seata** | 2.5.0 | 分布式事务 |
| **RocketMQ** | 5.3.1 | 消息队列 |
| **JDK** | 21 | Java 运行环境 |
| **Maven** | 3.x | 项目构建工具 |

## 📦 项目结构

```
bpds/
├── common/                  # 公共模块
│   ├── aspect/              # 切面
│   ├── config/              # 配置类
│   ├── constants/           # 常量定义
│   ├── dto/                 # 数据传输对象
│   ├── entity/              # 实体类
│   ├── exception/           # 异常处理
│   ├── interceptor/         # 拦截器
│   ├── utils/               # 工具类
│   └── validation/          # 参数校验
├── gateway-service/         # 网关服务 (端口: 8000)
├── user-service/            # 用户服务
├── cart-service/            # 购物车服务
├── item-service/            # 商品服务
├── project-service/         # 项目服务
├── order-service/           # 订单服务
└── pom.xml                  # 父 POM
```

## 🚀 快速开始

### 环境要求

- JDK 21+
- Maven 3.6+
- Nacos Server 3.0.3+
- MySQL 8.0+
- Redis 7.0+
- RocketMQ 5.3.1+（可选）

### 安装步骤

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd bpds
   ```

2. **配置 Nacos**
   
   在 Nacos 控制台创建以下配置：
   
   | Data ID | Group | 格式 | 说明 |
   |---------|-------|------|------|
   | gateway-route.json | DEFAULT_GROUP | JSON | 网关路由配置 |
   | {service}-service.yml | DEFAULT_GROUP | YAML | 各服务配置 |

3. **修改配置文件**
   
   编辑 `bootstrap.yml`，配置 Nacos 地址：
   ```yaml
   spring:
     cloud:
       nacos:
         server-addr: 127.0.0.1:8848
         username: nacos
         password: nacos
         config:
           namespace: ""
         discovery:
           namespace: ""
   ```

4. **构建项目**
   ```bash
   mvn clean install -DskipTests
   ```

5. **启动服务**
   
   按以下顺序启动服务：
   ```bash
   # 1. 启动网关
   cd gateway-service
   mvn spring-boot:run
   
   # 2. 启动业务服务（可并行）
   cd user-service && mvn spring-boot:run
   cd cart-service && mvn spring-boot:run
   cd item-service && mvn spring-boot:run
   cd project-service && mvn spring-boot:run
   cd order-service && mvn spring-boot:run
   ```

## 🔧 配置说明

### 网关路由配置

在 Nacos 中配置 `gateway-route.json`：

```json
[
  {
    "id": "user-service",
    "uri": "lb://user-service",
    "predicates": [
      {
        "name": "Path",
        "args": {
          "pattern": "/gateway-service/user/**"
        }
      }
    ],
    "filters": [
      {
        "name": "StripPrefix",
        "args": { "parts": "1" }
      },
      {
        "name": "PrefixPath",
        "args": { "prefix": "/user-service" }
      }
    ]
  }
]
```

### 服务端口分配

| 服务 | 端口 | 上下文路径 |
|------|------|-----------|
| gateway-service | 8000 | /gateway-service |
| user-service | 8001 | /user-service |
| cart-service | 8002 | /cart-service |
| item-service | 8003 | /item-service |
| project-service | 8004 | /project-service |
| order-service | 8005 | /order-service |

## 📝 API 文档

### 网关路由规则

- **用户服务**: `/gateway-service/user/**`
- **购物车服务**: `/gateway-service/cart/**`
- **商品服务**: `/gateway-service/item/**`
- **项目服务**: `/gateway-service/project/**`
- **订单服务**: `/gateway-service/order/**`

### 示例请求

```bash
# 获取用户信息
curl http://localhost:8000/gateway-service/user/api/user/info

# 获取商品列表
curl http://localhost:8000/gateway-service/item/api/item/list
```

## ️ 架构设计

### 服务调用链路

```
Client → Gateway → Service → Database/Cache/MQ
```

### 核心技术特性

- **服务注册与发现**: Nacos
- **配置中心**: Nacos Config
- **负载均衡**: Spring Cloud LoadBalancer
- **服务调用**: OpenFeign
- **熔断降级**: Sentinel
- **分布式事务**: Seata
- **消息队列**: RocketMQ
- **网关路由**: Spring Cloud Gateway

## 📊 监控与运维

- **Nacos 控制台**: http://127.0.0.1:8848/nacos
- **Sentinel 控制台**: http://127.0.0.1:8858
- **服务健康检查**: `/actuator/health`

##  贡献指南

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 👥 联系方式

- 项目维护者: [Your Name]
- 邮箱: [your-email@example.com]

---

**注意**: 本项目仅用于学习和研究目的。
