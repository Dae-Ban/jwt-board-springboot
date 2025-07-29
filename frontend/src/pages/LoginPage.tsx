import React from 'react';
import LoginForm from '../components/LoginForm';
import { login } from '../api/auth';

const LoginPage = () => {
    const handleLogin = async ({ username, password }: any) => {
        try {
            const data = await login({ username, password });
            localStorage.setItem('token', data.token);
            alert('로그인 성공!');
        } catch (err: any) {
            alert(`로그인 실패: ${err.response?.data?.message || '알 수 없는 오류'}`);
        }
    };

    return <LoginForm onSubmit={handleLogin} />;
};

export default LoginPage;
