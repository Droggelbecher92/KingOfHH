import React, {FormEvent, useState} from "react";
import {useAuth} from "../auth/AuthProvider";
import {useNavigate} from "react-router-dom";
import {registerUser} from "../service/apiService";

export default function LoginRegistrationPage(){
    const [newUsername, setNewUsername] = useState('')
    const [newPasswordOne, setNewPasswordOne] = useState('')
    const [newPasswordTwo, setNewPasswordTwo] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [register, setRegister] = useState(false)
    const [error, setError] = useState('')

    const nav = useNavigate()
    const auth = useAuth()


    const createUser = (event : FormEvent) => {
        event.preventDefault()
        setError('')
        if (!(newPasswordOne===newPasswordTwo) || newPasswordOne.length<5){
            setError("Passwörter nicht identisch oder zu kurz")
        } else {
            registerUser(newUsername, newPasswordOne, newPasswordTwo)
                .then(()=>{
                    setNewUsername('')
                    setNewPasswordOne('')
                    setNewPasswordTwo('')
                    setRegister(!register)
                })
                .catch(e => {
                    if (e.response.status===400){
                        setError("Name schon vergeben")
                    } else if (e.response.staus === 409){
                        setError("Passwörter nicht identisch")
                    }
                    else {
                        setError(e.message)
                    }
                })
        }
    }

    const login = (event : FormEvent) => {
        event.preventDefault()
        setError('')
        auth.login(username,password)
            .then(()=>nav("/"))
            .catch(() => setError("Login fehlgeschlagen"))
    }



    return (
        <div className="App">
            {
                register ?
                    <div>
                        <h2>Melde dich an</h2>
                        <form onSubmit={createUser}>
                            <input type="text" placeholder={'Dein Nutzername'} value={newUsername} onChange={ev => setNewUsername(ev.target.value)}/>
                            <input type="password" placeholder={'Passwort'} value={newPasswordOne} onChange={ev => setNewPasswordOne(ev.target.value)}/>
                            <input type="password" placeholder={'Passwort wiederholen'} value={newPasswordTwo} onChange={ev => setNewPasswordTwo(ev.target.value)}/>
                            <button type='submit'>Registrieren</button>
                        </form>
                    </div>
                    :
                    <div>
                        <h2>Login</h2>
                        <form onSubmit={login}>
                            <input type="text" placeholder={'Nutzername'} value={username} onChange={ev => setUsername(ev.target.value)}/>
                            <input type="password" placeholder={'Passwort'} value={password} onChange={ev => setPassword(ev.target.value)}/>
                            <button type='submit'>Login</button>
                        </form>
                    </div>
            }
            {error && <p>{error}</p>}
            <button onClick={()=> setRegister(!register)}>{register?'Zum Login':'Zur Anmeldung'}</button>
        </div>
    );
}