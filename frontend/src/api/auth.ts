import axios from 'axios';

const API_BASE_URL = 'http://localhost/auth'; // Spring Boot 백엔드 주소

export const register = async (data: { username: string; password: string; nickname: string }) => {
  const response = await axios.post(`${API_BASE_URL}/register`, data);
  return response.data;
};

export const login = async (data: { username: string; password: string }) => {
  const response = await axios.post(`${API_BASE_URL}/login`, data);
  return response.data; // { token: "..." }
};