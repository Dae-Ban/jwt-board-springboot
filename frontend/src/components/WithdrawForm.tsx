import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { authActions } from "../store/authSlice";
import { logout, withdraw } from "../api/auth";

const WithdrawForm = () => {
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleSubmit = async () => {
        try {
            const result = await withdraw(password);
            if (result.success) {
                logout();
                dispatch(authActions.logout());
                sessionStorage.removeItem('auth');
                console.log('탈퇴 성공');
                alert('탈퇴 완료');
                navigate('/');
            } else {
                console.error('실패:', result.message);
                alert(result.message);
            }
        } catch (err) {
            console.error("요청 실패:", err);
            alert("탈퇴에 실패했습니다.");
        }
    }

    return (
        <main>
            <h1>회원탈퇴</h1>
            <input
                type="password"
                onChange={(e) => setPassword(e.target.value)}
                placeholder="비밀번호 입력"
                required />
            <button onClick={handleSubmit}>탈퇴하기</button>
        </main>
    )
}

export default WithdrawForm;