package de.kittlaus.backend.game;


import de.kittlaus.backend.models.game.Game;
import de.kittlaus.backend.models.game.Player;
import de.kittlaus.backend.models.user.MyUser;
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


    public Optional<Game> createGame(MyUser user) {
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(user.getId(), user.getUsername());
        players.add(player);
        return Optional.of(gameRepo.save(new Game(players)));
    }

    public Optional<Game> addPlayer(MyUser user, String id) throws IllegalArgumentException {
        Optional<Game> optGame = gameRepo.findById(id);
        if (optGame.isPresent()){
            List<Player> players = optGame.get().getPlayers();
            Player player = new Player(user.getId(), user.getUsername());
            if (players.contains(player)){
                throw new IllegalArgumentException("Schon im Spiel");
            }
            players.add(player);
            optGame.get().setPlayers(players);
            gameRepo.save(optGame.get());
        }
        return optGame;
    }

    public List<Game> findAllOpen() {
        return gameRepo.findAllByStartedIsFalse();
    }
}
