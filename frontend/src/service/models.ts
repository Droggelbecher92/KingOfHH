// Auth

export interface AuthInterface {
    token : string,
    username : string,
    role : string,
    logout: () => void
    login: (username:string, password: string) => Promise<void>
}

export interface LoginResponseBody {
    token: string,
}

export interface UserDto {
    username : string,
    role : string,
}

export interface JwtToken {
    sub : string,
    iat : number,
    exp: number,
    roles: Array<string>
}

//GAME

export interface Game {
    id : string,
    players : Player[],
    cardDeck : PowerCard[],
    usedCards : PowerCard[],
    kingOfHH : Player,
    started : boolean,
}

export interface PowerCard{

}

export interface Player {
    userId : string,
    name : string,
    health : number,
    maxHealth : number,
    energy : number,
    score : number,
    die : number,
    dieThrows : number,
    attack : number,
    defense : number,
    playerCards : PowerCard[],
    powerUps : string[];
}