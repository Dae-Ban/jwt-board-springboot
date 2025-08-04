import React from 'react';
import { useNavigate } from 'react-router-dom';
import LoginForm from '../components/LoginForm';
import { login } from '../api/auth';
import { useDispatch } from 'react-redux';
import { authActions } from '../store/authSlice';

const LoginPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleLogin = async ({ username, password }: any) => {
        try {
            const data = await login({ username, password });
            localStorage.setItem('token', data.token);
            dispatch(authActions.login({ username }));
            sessionStorage.setItem('auth', JSON.stringify({ isLoggedIn: true, username }));
            alert('로그인 성공!');
            navigate('/');
        } catch (err: any) {
            alert(`로그인 실패: ${err.response?.data?.message || '알 수 없는 오류'}`);
        }
    };

    return <LoginForm onSubmit={handleLogin} />;
};

export default LoginPage;
