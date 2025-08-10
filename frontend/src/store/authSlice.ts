import { createSlice } from '@reduxjs/toolkit';

interface AuthState {
    isLoggedIn: boolean;
    username: string | null;
}

const storedAuth = sessionStorage.getItem('auth');
const initialState: AuthState = storedAuth
    ? JSON.parse(storedAuth)
    : {
        isLoggedIn: false,
        username: null,
    };

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login(state, action) {
            state.isLoggedIn = true;
            state.username = action.payload.username;
        },
        logout(state) {
            state.isLoggedIn = false;
            state.username = null;
        },
    },
});

export const authActions = authSlice.actions;
export default authSlice.reducer;
