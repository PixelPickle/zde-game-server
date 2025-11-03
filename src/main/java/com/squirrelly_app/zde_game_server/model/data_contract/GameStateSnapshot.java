package com.squirrelly_app.zde_game_server.model.data_contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.system.Game;
import org.jetbrains.annotations.NotNull;

public class GameStateSnapshot {

    @NotNull @JsonProperty("gameId")
    private final String gameId;

    public GameStateSnapshot(@NotNull Game game) {

        this.gameId = game.getGameId();

    }

}
