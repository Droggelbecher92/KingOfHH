package de.kittlaus.backend.game;

import de.kittlaus.backend.models.game.Game;
import de.kittlaus.backend.models.game.Player;
import de.kittlaus.backend.models.user.MyUser;
import de.kittlaus.backend.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceTest {

    @Test
    void name(){
        //GIVEN

        //WHEN

        //THEN

    }
    
    
    @Test
    void shouldCreateNewGame(){
        //GIVEN
        MyUser testUser = MyUser.builder().role("USER").username("Kim").password("1234").id("123").build();
        Game testGame = new Game(List.of(new Player(testUser.getId(),testUser.getUsername())));
        GameRepo mockGameRepo = mock(GameRepo.class);
        when(mockGameRepo.save(testGame)).thenReturn(testGame);

        GameService gameService = new GameService(mockGameRepo);

        //WHEN
        Optional<Game> optActual = gameService.createGame(testUser);

        //THEN
        assertThat(optActual).isPresent();
        Game actual = optActual.get();
        assertThat(actual.getPlayers().get(0).getUserId()).isEqualTo(testUser.getId());
    }

    @Test
    void addNewPlayerToGame(){
        //GIVEN
        MyUser testUser = MyUser.builder().role("USER").username("Kim").password("1234").id("123").build();
        MyUser testUser2 = MyUser.builder().role("USER").username("Tom").password("1234").id("124").build();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(testUser.getId(),testUser.getUsername()));
        Game testGame = new Game(players);
        GameRepo mockGameRepo = mock(GameRepo.class);
        when(mockGameRepo.findById("1")).thenReturn(Optional.of(testGame));


        GameService gameService = new GameService(mockGameRepo);
        //WHEN
        Optional<Game> optActual = gameService.addPlayer(testUser2, "1");
        //THEN
        assertThat(optActual).isPresent();
        Game actual = optActual.get();
        assertThat(actual.getPlayers().size()).isEqualTo(2);
        assertThat(actual.getPlayers()).containsExactlyInAnyOrder(new Player(testUser.getId(),testUser.getUsername()), new Player(testUser2.getId(), testUser2.getUsername()));
    }

    @Test
    void shouldNotAllowDuplicatePlayersInGame(){
        //GIVEN
        MyUser testUser = MyUser.builder().role("USER").username("Kim").password("1234").id("123").build();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(testUser.getId(),testUser.getUsername()));
        Game testGame = new Game(players);
        GameRepo mockGameRepo = mock(GameRepo.class);
        when(mockGameRepo.findById("1")).thenReturn(Optional.of(testGame));


        GameService gameService = new GameService(mockGameRepo);
        //WHEN

        //THEN
        assertThrows(IllegalArgumentException.class, () ->gameService.addPlayer(testUser, "1"));
    }


}