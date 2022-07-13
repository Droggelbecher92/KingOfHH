import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./auth/AuthProvider";
import LoginRegistrationPage from "./pages/LoginRegistrationPage";
import HomePage from "./pages/HomePage";

export default function App() {
    return(
        <BrowserRouter>
            <AuthProvider>
                <Routes>
                    <Route path={'/login'} element={<LoginRegistrationPage/>}/>
                    <Route path={'/'} element={<HomePage/>}/>
                </Routes>
            </AuthProvider>
        </BrowserRouter>
    )
}

