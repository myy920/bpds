# 创建容器网络
docker network create bpds

# nginx
docker run -d \
  --name=nginx \
  -p 80:80 \
  -v /opt/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
  -v /opt/nginx/html:/usr/share/nginx/html \
  --network bpds \
  nginx

#  mysql
docker run -d \
  --name=mysql \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=Root*123 \
  -e TZ=Asia/Shanghai \
  -v /opt/mysql/conf:/etc/mysql/conf.d \
  -v /opt/mysql/init:/docker-entrypoint-initdb.d \
  -v /opt/mysql/data:/var/lib/mysql \
  --network bpds \
  mysql

# redis
docker run -d \
  --name redis \
  -p 6379:6379 \
  -v /opt/redis/redis.conf:/usr/local/etc/redis/redis.conf:ro \
  -v /opt/redis/data:/data \
  --network bpds \
  redis redis-server /usr/local/etc/redis/redis.conf

# nacos
docker run -d \
  --name=nacos \
  -p 8848:8848 \
  -p 18848:8080 \
  -p 9848:9848 \
  -p 9849:9849 \
  -e MODE=standalone \
  -e TZ=Asia/Shanghai \
  -e JVM_XMS=128m \
  -e JVM_XMX=256m \
  -e JVM_XMN=128m \
  -e NACOS_AUTH_ENABLE=true \
  -e NACOS_AUTH_TOKEN=YjJiMmQ5OTQtZDZhYy00ZDE5LTkxMjYtN2I4OTQ4YTVjNTM3 \
  -e NACOS_AUTH_IDENTITY_KEY=nacos \
  -e NACOS_AUTH_IDENTITY_VALUE=nacos \
  -v /opt/nacos/init:/home/nacos/init.d \
  --network bpds \
  nacos/nacos-server:v3.0.3

# seata
docker run -d \
  --name=seata \
  -p 8091:8091 \
  -p 7091:7091 \
  -e TZ=Asia/Shanghai \
  -- network bpds \
  apache/seata-server:2.5.0

# rabbitmq
docker run -d \
  --name=rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  -e TZ=Asia/Shanghai \
  -e RABBITMQ_DEFAULT_USER=rabbitmq \
  -e RABBITMQ_DEFAULT_PASS=rabbitmq \
  --network bpds \
  rabbitmq:3-management



