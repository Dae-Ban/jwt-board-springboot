import { JSX, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { checkLogin } from '../api/auth';
import { authActions } from '../store/authSlice';

const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
  const [checking, setChecking] = useState(true);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    const validate = async () => {
      try {
        const { isAuthenticated, username } = await checkLogin();

        if (isAuthenticated) {
          dispatch(authActions.login({ username }));
        } else {
          dispatch(authActions.logout());
          navigate('/login');
        }
      } catch (error) {
        // 서버 응답 실패, 네트워크 문제, 401 등
        console.warn('인증 확인 중 오류 발생:', error);
        dispatch(authActions.logout());
        navigate('/login');
      } finally {
        setChecking(false);
      }
    };

    validate();
  }, [dispatch, navigate]);

  if (checking) return <div>인증 확인 중...</div>;

  return children;
};

export default ProtectedRoute;
