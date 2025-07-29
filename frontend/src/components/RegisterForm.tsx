import React, { useState } from 'react';

interface Props {
    onSubmit: (form: { username: string; password: string; nickname: string }) => void;
}

const RegisterForm = ({ onSubmit }: Props) => {
    const [form, setForm] = useState({
        username: '',
        password: '',
        nickname: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit(form);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input name="username" placeholder="아이디" value={form.username} onChange={handleChange} />
            <input name="password" type="password" placeholder="비밀번호" value={form.password} onChange={handleChange} />
            <input name="nickname" placeholder="닉네임" value={form.nickname} onChange={handleChange} />
            <button type="submit">회원가입</button>
        </form>
    );
};

export default RegisterForm;
