package de.kittlaus.backend.game;

import de.kittlaus.backend.models.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepo extends MongoRepository<Game, String > {
}
