package de.kittlaus.backend.models.game;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    private String id;

    private List<Player> players;
    private List<PowerCard> cardDeck;
    private List<PowerCard> usedCards;
    private Player kingOfHH;

    private boolean started = false;

    public Game(List<Player> players) {
        this.players = players;
        cardDeck = new ArrayList<>();
        usedCards = new ArrayList<>();
        kingOfHH = players.get((int) (Math.random()*players.size()));
    }
}
