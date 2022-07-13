import axios from "axios";
import {LoginResponseBody, UserDto, Game} from "./models";


//USER
export const loginUser = (username:string, password: string) => {
    return axios.post<LoginResponseBody>(`/auth`, {'username':username, 'password':password})
        .then(response => response.data)
}

export const registerUser = (username:string, password: string, passwordAgain: string) => {
    return axios.post<UserDto>(`/api/user`, {username, password, passwordAgain})
        .then(response => response.data)
}

//GAME_START

export const createNewGame = (token : string) => {
    return axios.post<Game>(`/api/game`,null,createHeaders(token))
        .then(response => response.data)
}

export const getAllOpenGames = (token : string) => {
    return axios.get<Array<Game>>(`/api/game/open`,createHeaders(token))
        .then(response => response.data)
}

export const joinExistingGame = (gameId : string, token:string) => {
    return axios.put(`/api/game/addPlayer/${gameId}`, null, createHeaders(token))
        .then(response => response.data)
}

//HELPER

const createHeaders = (token : string) => {
    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
}