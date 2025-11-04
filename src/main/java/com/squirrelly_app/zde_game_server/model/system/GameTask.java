package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.model.type.GameTaskType;
import com.squirrelly_app.zde_game_server.model.type.PhaseType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameTask {

    @NotNull private final GameTaskType gameTaskType;
    @NotNull private final String playerId;
    @NotNull private final String gameId;

    @Nullable private PhaseType phaseType;

    public GameTask(
            @NotNull GameTaskType gameTaskType,
            @NotNull String playerId,
            @NotNull String gameId
    ) {

        this.gameTaskType = gameTaskType;
        this.playerId = playerId;
        this.gameId = gameId;

        this.phaseType = null;

    }

    public GameTask(@NotNull GameTask gameTask, @NotNull PhaseType phaseType) {

        this.gameTaskType = gameTask.getGameTaskType();
        this.playerId = gameTask.getPlayerId();
        this.gameId = gameTask.getGameId();

        this.phaseType = phaseType;

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

    public @Nullable PhaseType getPhaseType() {
        return phaseType;
    }

    public void setPhaseType(@Nullable PhaseType phaseType) {
        this.phaseType = phaseType;
    }
}
