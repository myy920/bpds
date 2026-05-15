# 依赖jdk21
FROM eclipse-temurin:21-jre

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 暴露端口
EXPOSE 8080

# 复制已构建好的 JAR 包
COPY *.jar app.jar

# JVM 参数和启动命令
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:+UseG1GC"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]