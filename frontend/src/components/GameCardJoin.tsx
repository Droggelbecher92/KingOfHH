import {Game} from "../service/models";
import {useNavigate} from "react-router-dom";
import {joinExistingGame} from "../service/apiService";
import {useAuth} from "../auth/AuthProvider";

interface GameCardJoinProps{
    data : Game
}

export default function GameCardJoin({data}:GameCardJoinProps){

    const nav = useNavigate()
    const {token} = useAuth()

    const joinGame = () =>{
        joinExistingGame(data.id,token)
            .then(data => {
                console.log(data)
                nav(`/lobby/${data.id}`)
            })
    }

    return(
        <div>
            <p>{data.id}</p>
            <button onClick={joinGame}>Join</button>
        </div>
    )
}