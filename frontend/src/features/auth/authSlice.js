import {createSlice} from "@reduxjs/toolkit";
import {registerUser, userLogin} from "./authActions";

const userToken = localStorage.getItem('userToken')
    ? localStorage.getItem('userToken')
    :null
const initialState={
    loading: false,
    userInfo: null,
    userToken,
    error: null,
    success:false,
}
const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers:{
        logout: (state) =>{
            localStorage.removeItem('userToken')
            state.loading = false
            state.userInfo = null
            state.userToken = null
            state.error = null
        },
        setCredentials: (state, {payload}) => {
            state.userInfo = payload
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(userLogin.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(userLogin.fulfilled, (state, action) => {
                state.loading = false;
                state.userInfo = action.payload;
                state.userToken = action.payload.userToken;
            })
            .addCase(userLogin.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })
            .addCase(registerUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(registerUser.fulfilled, (state, action) => {
                state.loading = false;
                state.success = true;
            })
            .addCase(registerUser.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    },
    // extraReducers:{
    //     [userLogin.pending]: (state) => {
    //         state.loading = true
    //         state.error = null
    //     },
    //     [userLogin.fulfilled]: (state, { payload }) => {
    //         state.loading = false
    //         state.userInfo = payload
    //         state.userToken = payload.userToken
    //     },
    //     [userLogin.rejected]: (state, { payload }) => {
    //         state.loading = false
    //         state.error = payload
    //     },
    //     [registerUser.pending] :(state) =>{
    //         state.loading = true
    //         state.error = null
    //     },
    //     [registerUser.fulfilled]: (state, {payload}) =>{
    //         state.loading = false
    //         state.success = true
    //     },
    //     [registerUser.rejected]: (state, {payload}) =>{
    //         state.loading = false
    //         state.error = payload
    //     },
    // },
})
export default authSlice.reducer
export const {logout, setCredentials} = authSlice.actions