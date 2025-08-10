import '../styles/mypage.css';
import api from './api';

export const myInfo = async () => {
    const res = await api.get(`/mypage`);
    return res.data.data;
}

export const updateNickname = async (nickname: string) => {
    return await api.patch(`/mypage/nickname`, { nickname });
};

export const updatePassword = async (password: string) => {
    return await api.patch(`/mypage/password`, { password });
};