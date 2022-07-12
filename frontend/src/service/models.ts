// Auth

export interface AuthInterface {
    token : string,
    username : string,
    role : string,
    logout: () => void
}

export interface LoginResponseBody {
    token: string,
    language: string,
}

export interface JwtToken {
    sub : string,
    iat : number,
    exp: number,
    role: Array<string>
}