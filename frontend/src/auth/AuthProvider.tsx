import {ReactNode, useContext, useEffect, useState} from "react";
import AuthContext from "./AuthContext";
import {useNavigate} from "react-router-dom";
import jwtDecode from "jwt-decode";
import {JwtToken} from "../service/models";

export default function AuthProvider({children}:{children :ReactNode}) {
    const nav = useNavigate()
    const [token, setToken] = useState(localStorage.getItem('jwt') ?? '')
    const [role, setRole] = useState('')
    const [username, setUsername] = useState('');

    useEffect(() => {
        if (token) {
            const decodeJWT : JwtToken = jwtDecode(token)
            setUsername(decodeJWT.sub);
            setRole(decodeJWT.role[0])
        } else {
            nav('/login')
        }
    }, [token, nav])


    const logout = () => {
        localStorage.removeItem('jwt')
        setToken('')
        setRole('')
        setUsername('')
    }

    return <AuthContext.Provider value={{token, role, username, logout}} >{children}</AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext)