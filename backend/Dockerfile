# --- Build stage ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Gradle
COPY backend/gradlew backend/gradle /app/
COPY backend/build.gradle backend/settings.gradle /app/

# 소스 복사
COPY backend/src /app/src

# 의존성/빌드 (Gradle)
RUN chmod +x ./gradlew && ./gradlew clean bootJar --no-daemon

# --- Runtime stage ---
FROM eclipse-temurin:21-jre-alpine
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
