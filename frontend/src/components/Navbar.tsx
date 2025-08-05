import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../store/index';
import { authActions } from '../store/authSlice';
import { logout } from '../api/auth';

const Navbar = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const isLoggedIn = useSelector((state: RootState) => state.auth.isLoggedIn);
    const username = useSelector((state: RootState) => state.auth.username);

    const handleLogin = () => {
        navigate('/login');
    };

    const handleLogout = () => {
        logout();
        dispatch(authActions.logout());
        sessionStorage.removeItem('auth');
        navigate('/');
    };

    return (
        <nav>
            <span id='nav-menus'>
                <span><a href='/'><button>홈</button></a></span>
                <span><a href='/about'><button>소개</button></a></span>
                {isLoggedIn ? (
                    <span className='logged-in'>
                        <span>환영합니다, {username}님!</span>
                        <span><button onClick={handleLogout}>로그아웃</button></span>
                    </span>
                ) : (
                    <span><button onClick={handleLogin}>로그인</button></span>
                )}
            </span>
        </nav>
    );
}

export default Navbar;