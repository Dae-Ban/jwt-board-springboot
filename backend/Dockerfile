# --- Build stage ---
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# gradlew + wrapper
COPY gradlew ./
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/
COPY gradle/wrapper/gradle-wrapper.properties gradle/wrapper/

# 윈도우 줄바꿈
RUN sed -i 's/\r$//' gradlew

# 빌드 파일/소스 복사
COPY build.gradle settings.gradle ./
COPY src ./src

# 의존성/빌드 (Gradle)
RUN chmod +x ./gradlew && ./gradlew clean bootJar --no-daemon

# --- Runtime stage ---
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# 빌드 산출물 복사 (Gradle)
COPY --from=builder /app/build/libs/*.jar app.jar
# 포트
EXPOSE 8080

# 헬스체크
HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# JVM 튜닝
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+UseG1GC"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
