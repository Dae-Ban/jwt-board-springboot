import api from './api';

export const register = async (data: { username: string; password: string; nickname: string }) => {
  const response = await api.post(`/auth/register`, data);
  return response.data;
};

export const login = async (data: { username: string; password: string }) => {
  const response = await api.post(`/auth/login`, data);
  return response.data;
};

export const logout = async () => {
  await api.post(`/auth/logout`, {});
}

export const checkLogin = async () => {
  try {
    const res = await api.get('/auth/check');
    return { isAuthenticated: true, username: res.data.username };
  } catch (err: any) {
    // accessToken 만료로 401일 경우 → refresh 시도
    if (err.response?.status === 401) {
      try {
        await api.post('/auth/refresh');
        const res = await api.get('/auth/check'); // 다시 인증 확인
        return { isAuthenticated: true, username: res.data.username };
      } catch (refreshErr) {
        // refresh 실패 시 → 로그아웃 처리
        return { isAuthenticated: false, username: null };
      }
    }

    return { isAuthenticated: false, username: null };
  }
};