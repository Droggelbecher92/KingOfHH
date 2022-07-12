package de.kittlaus.backend.models.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private String userId;

    private String name;
    private int health = 10;
    private int maxHealth = 10;
    private int energy = 0;
    private int score = 0;
    private int die = 6;
    private int dieThrows = 3;
    private int attack = 0;
    private int defense = 0;


    private List<PowerCard> playerCards;
    private List<String> powerUps;


}
