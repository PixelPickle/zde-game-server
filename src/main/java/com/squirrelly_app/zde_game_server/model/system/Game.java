package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.factory.DeckFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Game {

    @NotNull private final String gameId;
    @NotNull private final GameBoard gameBoard;

    public Game(
            @NotNull String gameId,
            @NotNull List<String> players
    ) {

        this.gameId = gameId;
        this.gameBoard = new GameBoard(DeckFactory.createDeck(), players);

    }

    public @NotNull String getGameId() {
        return gameId;
    }

    public @NotNull GameBoard getGameBoard() {
        return gameBoard;
    }
}
