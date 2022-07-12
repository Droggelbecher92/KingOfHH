import axios from "axios";
import {LoginResponseBody, UserDto} from "./models";

export const loginUser = (username:string, password: string) => {
    return axios.post<LoginResponseBody>(`/auth`, {'username':username, 'password':password})
        .then(response => response.data)
}

export const registerUser = (username:string, password: string, passwordAgain: string) => {
    return axios.post<UserDto>(`/api/user`, {username, password, passwordAgain})
        .then(response => response.data)
}