import React, { useState } from 'react';
import MypageAside from '../components/MypageAside';
import MypageMain from '../components/MypageMain';
const Mypage = () => {
    const [selectedMenu, setSelectedMenu] = useState('profile');
    return (
        <div id='mypage'>
            <h2>마이페이지</h2>
            <hr />
            <MypageAside onSelectMenu={setSelectedMenu} />
            <MypageMain selectedMenu={selectedMenu} />
        </div>
    );
}

export default Mypage;