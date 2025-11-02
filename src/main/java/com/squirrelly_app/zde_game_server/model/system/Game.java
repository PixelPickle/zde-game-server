package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.factory.DeckFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Game {

    @NotNull private final GameBoard gameBoard;

    public Game(List<String> players) {

        this.gameBoard = new GameBoard(DeckFactory.createDeck(), players);

    }
}
