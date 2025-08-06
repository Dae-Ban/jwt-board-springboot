import React from 'react';
import { useNavigate } from 'react-router-dom';
import RegisterForm from '../components/RegisterForm';
import { register } from '../api/auth';

const RegisterPage = () => {
  const navigate = useNavigate();
  const handleRegister = async ({ username, password, nickname }: any) => {
    try {
      await register({ username, password, nickname });
      alert('회원가입 성공!');
      navigate('/');
    } catch (e: any) {
      alert(`회원가입 실패: ${e.response?.data?.message}`);
    }
  };

  return <RegisterForm onSubmit={handleRegister} />;
};

export default RegisterPage;
