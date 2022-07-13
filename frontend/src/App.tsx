import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./auth/AuthProvider";
import LoginRegistrationPage from "./pages/LoginRegistrationPage";
import HomePage from "./pages/HomePage";
import LobbyPage from "./pages/LobbyPage";

export default function App() {
    return(
        <BrowserRouter>
            <AuthProvider>
                <Routes>
                    <Route path={'/'} element={<HomePage/>}/>
                    <Route path={'/login'} element={<LoginRegistrationPage/>}/>
                    <Route path={'/lobby/:id'} element={<LobbyPage/>}/>
                </Routes>
            </AuthProvider>
        </BrowserRouter>
    )
}

