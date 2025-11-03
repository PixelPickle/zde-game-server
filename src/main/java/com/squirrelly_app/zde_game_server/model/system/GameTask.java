package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.model.type.GameTaskType;
import org.jetbrains.annotations.NotNull;

public class GameTask {

    @NotNull private final GameTaskType gameTaskType;
    @NotNull private final String playerId;
    @NotNull private final String gameId;

    public GameTask(
            @NotNull GameTaskType gameTaskType,
            @NotNull String playerId,
            @NotNull String gameId
    ) {
        this.gameTaskType = gameTaskType;
        this.playerId = playerId;
        this.gameId = gameId;
    }

    public @NotNull GameTaskType getGameTaskType() {
        return gameTaskType;
    }

    public @NotNull String getPlayerId() {
        return playerId;
    }

    public @NotNull String getGameId() {
        return gameId;
    }
}
