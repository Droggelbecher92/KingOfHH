package de.kittlaus.backend.game;

import de.kittlaus.backend.models.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameRepo extends MongoRepository<Game, String > {

    List<Game> findAllByStartedIsFalse();

}
