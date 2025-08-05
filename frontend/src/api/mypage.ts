import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/mypage';

export const myInfo = async () => {
    const res = await axios.get(`${API_BASE_URL}`, { withCredentials: true });
    return res.data;
}

export const updateNickname = async (nickname: string) => {
    return await axios.patch(`${API_BASE_URL}/nickname`, { nickname }, { withCredentials: true });
};

export const updatePassword = async (password: string) => {
    return await axios.patch(`${API_BASE_URL}/password`, { password }, { withCredentials: true });
};