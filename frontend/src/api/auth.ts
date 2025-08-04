import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/auth'; // Spring Boot 백엔드 주소

export const register = async (data: { username: string; password: string; nickname: string }) => {
  const response = await axios.post(`${API_BASE_URL}/register`, data);
  return response.data;
};

export const login = async (data: { username: string; password: string }) => {
  const response = await axios.post(`${API_BASE_URL}/login`, data, { withCredentials: true });
  return response.data;
};

export const checkLogin = async () => {
  try {
    const res = await axios.get(`${API_BASE_URL}/check`, {
      withCredentials: true,
    });
    const username = res.data.username;
    return { isAuthenticated: true, username };
  } catch (err) {
    return { isAuthenticated: false, username: null };
  }
};