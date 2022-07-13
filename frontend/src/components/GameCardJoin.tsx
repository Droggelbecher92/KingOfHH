import {Game} from "../service/models";
import {useNavigate} from "react-router-dom";

interface GameCardJoinProps{
    data : Game
}

export default function GameCardJoin({data}:GameCardJoinProps){

    const nav = useNavigate()

    return(
        <div>
            <p onClick={() => nav(`/lobby/${data.id}`)}>{data.id}</p>
        </div>
    )
}