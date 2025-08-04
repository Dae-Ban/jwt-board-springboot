import { JSX, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { authActions } from '../store/authSlice';
import { checkLogin } from '../api/auth';

const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
  const [checking, setChecking] = useState(true);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    const validate = async () => {
      const { isAuthenticated, username } = await checkLogin();

      if (isAuthenticated) {
        dispatch(authActions.login({ username }));
      } else {
        dispatch(authActions.logout());
        navigate('/login');
      }

      setChecking(false);
    };

    validate();
  }, [dispatch, navigate]);

  if (checking) return <div>인증 확인 중...</div>;

  return children;
};

export default ProtectedRoute;