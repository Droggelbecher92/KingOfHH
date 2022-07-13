import {useAuth} from "../auth/AuthProvider";
import {createNewGame, getAllOpenGames} from "../service/apiService";

export default function HomePage(){


    const {username,token} = useAuth()

    const createGame = () => {
        createNewGame(token)
            .then(data => console.log(data))
    }

    const getOpenGames = () => {
        getAllOpenGames(token)
            .then(data => console.log(data))
    }


    return(<div>
            <h2>Hallo {username}</h2>
            <button onClick={createGame}>Eigenes Spiel eröffnen</button>
            <button onClick={getOpenGames}>Offene Spiele suchen</button>
    </div>

    )
}