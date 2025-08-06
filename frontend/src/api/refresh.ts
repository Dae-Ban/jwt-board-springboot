// api/axios.ts
import api from './api';

api.interceptors.response.use(
  response => response, // 성공 응답은 그대로 통과
  async error => {
    const originalRequest = error.config;

    // accessToken 만료로 401이 발생했고, 무한루프 방지용 조건
    if (
      error.response?.status === 401 &&
      !originalRequest._retry &&
      !originalRequest.url.includes('/auth/refresh')
    ) {
      originalRequest._retry = true;

      try {
        // refresh 요청
        await api.post('/auth/refresh');

        // 재시도
        return api(originalRequest);
      } catch (refreshError) {
        // refresh도 실패 → 로그인 페이지로 이동
        window.location.href = '/login';
        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);
