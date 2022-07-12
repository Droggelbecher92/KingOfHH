package de.kittlaus.backend.game;


import de.kittlaus.backend.models.game.Game;
import de.kittlaus.backend.models.game.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepo gameRepo;


    public Optional<Game> createGame(Player player) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        return Optional.of(gameRepo.save(new Game(players)));
    }

    public Optional<Game> addPlayer(Player player, String id) {
        Optional<Game> optGame = gameRepo.findById(id);
        if (optGame.isPresent()){
            List<Player> players = optGame.get().getPlayers();
            players.add(player);
            optGame.get().setPlayers(players);
            gameRepo.save(optGame.get());
        }
        return optGame;
    }
}