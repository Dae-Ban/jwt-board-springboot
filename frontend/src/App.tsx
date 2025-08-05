import AppRoutes from './routes/AppRoutes';
import { Provider } from 'react-redux';
import { store } from './store/index';
import Navbar from './components/Navbar';

function App() {
  return (
    <Provider store={store}>
      <Navbar />
      <AppRoutes/>
    </Provider>
  );
}

export default App;
