import { useState, useEffect } from "react";
import { myInfo, updateNickname, updatePassword } from "../api/mypage";

const Profile = () => {
    const [userInfo, setUserInfo] = useState({
        username: "",
        nickname: "",
        role: "",
        created_at: "",
    });

    const [newNickname, setNewNickname] = useState("");
    const [newPassword, setNewPassword] = useState("");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await myInfo();
                setUserInfo(data);
            } catch (error) {
                console.error("유저 정보 불러오기 실패:", error);
            }
        };

        fetchData();
    }, []);


    useEffect(() => {
        if (userInfo.nickname) {
            setNewNickname(userInfo.nickname);
        }
    }, [userInfo.nickname]);

    const handleUpdate = async () => {
        try {
            if (newNickname !== userInfo.nickname) {
                await updateNickname(newNickname);
            }
            if (newPassword.trim()) {
                await updatePassword(newPassword);
                setNewPassword(""); // 입력 초기화
            }
            const updated = await myInfo(); // 변경 후 새로 불러오기
            setUserInfo(updated);
            alert("정보가 변경되었습니다.");
        } catch (err) {
            console.error("업데이트 실패:", err);
            alert("업데이트에 실패했습니다.");
        }
    };

    return (
        <main>
            <table>
                <tbody>
                    <tr>
                        <td>계정명</td>
                        <td>{userInfo.username}</td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <input
                                type="password"
                                value={newPassword}
                                onChange={(e) => setNewPassword(e.target.value)}
                                placeholder="새 비밀번호 입력"
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>닉네임</td>
                        <td>
                            <input
                                type="text"
                                value={newNickname}
                                onChange={(e) => setNewNickname(e.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>계정등급</td>
                        <td>{userInfo.role}</td>
                    </tr>
                    <tr>
                        <td>가입일시</td>
                        <td>{new Date(userInfo.created_at).toLocaleString()}</td>
                    </tr>
                </tbody>
            </table>
            <button onClick={handleUpdate}>수정하기</button>
        </main>
    )
}

export default Profile;