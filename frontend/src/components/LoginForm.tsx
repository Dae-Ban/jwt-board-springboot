import React, { useState } from 'react';

interface Props {
    onSubmit: (form: { username: string; password: string; }) => void;
}

const LoginForm = ({ onSubmit }: Props) => {
    const [form, setForm] = useState({
        username: '',
        password: '',
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
            <button type="submit">로그인</button>
        </form>
    );
};

export default LoginForm;
