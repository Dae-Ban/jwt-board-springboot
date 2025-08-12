# --- Build stage ---
FROM node:20-alpine AS builder
WORKDIR /app
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./
# CRA: npm run build / Vite: npm run build
RUN npm run build

# --- Runtime stage ---
FROM nginx:1.27-alpine
# 빌드 결과 복사
COPY --from=builder /app/build /usr/share/nginx/html
# Nginx 설정
COPY frontend/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
