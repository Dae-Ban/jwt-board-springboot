import React from 'react';
import logo from '../logo.svg';
import '../styles/home.css'
import '../App.css';

const HomePage = () => {
    return (
        <div id='home'>
            <h1>Home</h1>
            <hr />
            <div id='home-content'>
                <aside>
                    <a href='/about' className='side-menu-link'><div className='side-menu-btn'>소개</div></a>
                    <a href='/board' className='side-menu-link'><div className='side-menu-btn'>게시판</div></a>
                    <a href='/mypage' className='side-menu-link'><div className='side-menu-btn'>마이페이지</div></a>
                </aside>
                <main>
                    <img src={logo} alt='logo' className='App-logo'></img>
                </main>
            </div>
        </div>
    );
}

export default HomePage;