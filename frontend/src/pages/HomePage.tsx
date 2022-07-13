import {useAuth} from "../auth/AuthProvider";
import {createNewGame, getAllOpenGames} from "../service/apiService";
import {useState} from "react";
import {Game} from "../service/models";
import GameCardJoin from "../components/GameCardJoin";
import {useNavigate} from "react-router-dom";

export default function HomePage(){

    const [openGames, setOpenGames] = useState<Array<Game>>()


    const {username,token} = useAuth()
    const nav = useNavigate()

    const createGame = () => {
        createNewGame(token)
            .then(data => nav(`/lobby/${data.id}`))
    }

    const getOpenGames = () => {
        getAllOpenGames(token)
            .then(data => setOpenGames(data))
    }


    return(<div>
            <h2>Hallo {username}</h2>
            <button onClick={createGame}>Eigenes Spiel eröffnen</button>
            <button onClick={getOpenGames}>Offene Spiele suchen</button>
            {
                openGames &&
                openGames.map((game,i) => <GameCardJoin key={i} data={game}/>)
            }
    </div>

    )
}