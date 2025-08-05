import './styles/common.css';
import AppRoutes from './routes/AppRoutes';
import { Provider } from 'react-redux';
import { store } from './store/index';
import Navbar from './components/Navbar';
import { BrowserRouter } from 'react-router-dom';

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Navbar />
        <AppRoutes />
      </BrowserRouter>
    </Provider>
  );
}

export default App;
