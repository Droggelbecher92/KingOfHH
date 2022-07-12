package de.kittlaus.backend.game;


import de.kittlaus.backend.models.game.Game;
import de.kittlaus.backend.models.game.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepo gameRepo;


    public Optional<Game> createGame(ArrayList<Player> players) {
        Collections.shuffle(players);
        return Optional.of(gameRepo.save(new Game(players)));
    }
}
