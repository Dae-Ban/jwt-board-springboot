import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import AboutPage from '../pages/AboutPage';
import RegisterPage from '../pages/RegisterPage';
import LoginPage from '../pages/LoginPage';

const AppRoutes = () => (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/about" element={<AboutPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/login" element={<LoginPage />} />
    </Routes>
  </BrowserRouter>
);

export default AppRoutes;