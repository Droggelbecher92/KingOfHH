import axios, {AxiosResponse} from "axios";
import {LoginResponseBody, UserDto, Game} from "./models";


//USER
export const loginUser = (username:string, password: string) => {
    return axios.post(`/auth`, {'username':username, 'password':password})
        .then((response: AxiosResponse<LoginResponseBody>) => response.data)
}

export const registerUser = (username:string, password: string, passwordAgain: string) => {
    return axios.post(`/api/user`, {username, password, passwordAgain})
        .then((response:AxiosResponse<UserDto>) => response.data)
}

//GAME_START

export const createNewGame = (token : string) => {
    return axios.post(`/api/game`,null,createHeaders(token))
        .then((response :AxiosResponse<Game>) => response.data)
}

export const getAllOpenGames = (token : string) => {
    return axios.get(`/api/game/open`,createHeaders(token))
        .then((response :AxiosResponse<Array<Game>>)=> response.data)
}

export const joinExistingGame = (gameId : string, token:string) => {
    return axios.put(`/api/game/addPlayer/${gameId}`, null, createHeaders(token))
        .then((response :AxiosResponse<Game>) => response.data)
}

//HELPER

const createHeaders = (token : string) => {
    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
}