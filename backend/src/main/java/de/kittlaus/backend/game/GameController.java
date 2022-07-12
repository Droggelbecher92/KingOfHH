package de.kittlaus.backend.game;


import de.kittlaus.backend.models.game.Game;
import de.kittlaus.backend.models.game.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> postNewGame(@RequestBody Player player){
        return ResponseEntity.of(gameService.createGame(player));
    }

    @PutMapping("/addPlayer/{id}")
    public ResponseEntity<Game> addPlayerToGame(@RequestBody Player player, @PathVariable String id){
        return ResponseEntity.of(gameService.addPlayer(player,id));
    }

}
