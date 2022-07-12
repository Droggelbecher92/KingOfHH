import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestPage from "./TestPage";
import AuthProvider from "./auth/AuthProvider";
import LoginRegistrationPage from "./pages/LoginRegistrationPage";

export default function App() {
    return(
        <BrowserRouter>
            <AuthProvider>
                <Routes>
                    <Route path={'/login'} element={<LoginRegistrationPage/>}/>
                    <Route path={'/'} element={<TestPage/>}/>
                </Routes>
            </AuthProvider>
        </BrowserRouter>
    )
}

