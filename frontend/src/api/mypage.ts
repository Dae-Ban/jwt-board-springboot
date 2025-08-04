import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/mypage';

export const myInfo = async () => {
    const res = await axios.get(`${API_BASE_URL}`, { withCredentials: true });
    return res.data;
}