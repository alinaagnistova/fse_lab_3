import {createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";

const backendURL = 'http://127.0.0.1:5000'

export const registerUser = createAsyncThunk(
    'auth/register',
    async({username, password},{rejectWithValue}) => {
        try {
            const config = {
                headers: {
                    'Content-type': 'application/json',
                },
            }
            await axios.post(
                `${backendURL}/api/user/register`,
                {username, password},
                config
            )
        } catch (error) {
            if (error.response && error.response.data.message) {
                return rejectWithValue(error.response.data.message)
            } else {
                return rejectWithValue(error.message)
            }
        }
    }
)
export const userLogin = createAsyncThunk(
    'auth/login',
    async({username, password},{rejectWithValue}) => {
        try {
            const config = {
                headers: {
                    'Content-type': 'application/json',
                },
            }
            const {data} = await axios.post(
                `${backendURL}/api/user/login`,
                {username, password},
                config
            )
            localStorage.setItem('userToken', data.userToken)
            return data
        } catch (error) {
            if (error.response && error.response.data.message) {
                return rejectWithValue(error.response.data.message)
            } else {
                return rejectWithValue(error.message)
            }
        }
    }
)