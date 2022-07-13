package de.kittlaus.backend.game;


import de.kittlaus.backend.models.game.Game;
import de.kittlaus.backend.models.game.Player;
import de.kittlaus.backend.models.user.MyUser;
import de.kittlaus.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> postNewGame(Principal principal){
        MyUser user = userService.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.of(gameService.createGame(user));
    }

    @GetMapping("/open")
    public List<Game> getAllOpenGames(){
        return gameService.findAllOpen();
    }

    @PutMapping("/addPlayer/{id}")
    public ResponseEntity<Game> addPlayerToGame(Principal principal, @PathVariable String id){
        MyUser user = userService.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.of(gameService.addPlayer(user,id));
    }

}
