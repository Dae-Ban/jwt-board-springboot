
interface Props {
    onSelectMenu: (menu: string) => void;
}

const MypageAside = ({ onSelectMenu }: Props) => {
    return (
        <aside id="mypage-menus">
            <div className="side-menu-btn" onClick={() => onSelectMenu('profile')}>프로필</div>
            <div className="side-menu-btn" onClick={() => onSelectMenu('posts')}>내 글 목록</div>
            <div className="side-menu-btn" onClick={() => onSelectMenu('withdraw')}>회원 탈퇴</div>
        </aside>
    );
}

export default MypageAside;