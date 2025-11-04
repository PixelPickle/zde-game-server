package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.factory.DeckFactory;
import com.squirrelly_app.zde_game_server.model.type.PhaseType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Game {

    @NotNull private final String gameId;
    @NotNull private final List<String> players;
    @NotNull private final GameBoard gameBoard;
    private int firstPlayerIndex;
    private boolean enableExploits;
    @NotNull private final List<GameTask> playbackSteps;
    @NotNull private PhaseType currentPhase;

    public Game(
            @NotNull String gameId,
            @NotNull Lobby lobby,
            @NotNull GameTask gameTask
    ) {

        this.gameId = gameId;
        this.players = new ArrayList<>(lobby.getPlayers());
        this.gameBoard = new GameBoard(DeckFactory.createDeck(), players);
        this.enableExploits = false;
        this.playbackSteps = new ArrayList<>();

        Collections.shuffle(this.players);

        firstPlayerIndex = new Random().nextInt(this.players.size());

        recordPlaybackStep(gameTask, PhaseType.SETUP);

    }

    public @NotNull String getGameId() {
        return gameId;
    }

    public @NotNull List<String> getPlayers() {
        return players;
    }

    public @NotNull List<String> getPlayersInTurnOrder() {

        if (firstPlayerIndex == 0) {
            return players;
        }

        return Stream.concat(
                players.subList(firstPlayerIndex, players.size()).stream(),
                players.subList(0, firstPlayerIndex).stream()
        ).toList();

    }

    public @NotNull GameBoard getGameBoard() {
        return gameBoard;
    }

    public void progressPlayerIndex() {

        this.firstPlayerIndex = this.firstPlayerIndex + 1 % this.players.size();

    }

    public boolean isEnableExploits() {
        return enableExploits;
    }

    public void setEnableExploits(boolean enableExploits) {
        this.enableExploits = enableExploits;
    }

    public @NotNull List<GameTask> getPlaybackSteps() {
        return playbackSteps;
    }

    public void recordPlaybackStep(@NotNull GameTask gameTask, @NotNull PhaseType phaseType) {

        this.currentPhase = phaseType;

        playbackSteps.add( new GameTask(gameTask, phaseType) );

    }

}
