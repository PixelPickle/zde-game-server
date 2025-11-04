package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidGameTaskException;
import com.squirrelly_app.zde_game_server.exception.InvalidLobbyException;
import com.squirrelly_app.zde_game_server.exception.InvalidPlayerException;
import com.squirrelly_app.zde_game_server.model.system.Game;
import com.squirrelly_app.zde_game_server.model.system.GameTask;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
import com.squirrelly_app.zde_game_server.model.type.GameTaskType;
import com.squirrelly_app.zde_game_server.model.type.PhaseType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@Service
public final class GameExecutorService {

    @NotNull private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NotNull private final ExecutorService executorService;
    @NotNull private final DataManagementService dataManagementService;

    public GameExecutorService(
            @NotNull DataManagementService dataManagementService
    ) {

        this.executorService = newSingleThreadExecutor();

        this.dataManagementService = dataManagementService;

        logger.info("GameExecutorService initialized");

    }

    public Future<?> addTask(@NotNull GameTask gameTask) {

        return executorService.submit( () -> {

            try {

                processGameTask(gameTask);

            } catch (Exception exception) {

                logger.error(exception.getMessage(), exception);

            }

        } );

    }

    private void processGameTask(@NotNull GameTask gameTask) {

        switch (gameTask.getGameTaskType()) {

            case GameTaskType.CREATE_LOBBY:
                createLobby(gameTask);
                break;

            case GameTaskType.JOIN_LOBBY:
                joinLobby(gameTask);
                break;

            case GameTaskType.LEAVE_LOBBY:
                leaveLobby(gameTask);
                break;

            case GameTaskType.START_GAME:
                startGame(gameTask);
                break;

            default:
                throw new InvalidGameTaskException(gameTask);

        }

    }

    private void createLobby(@NotNull GameTask gameTask) {

        Lobby preexistingLobby = dataManagementService.findLobbyByPlayerId(gameTask.getPlayerId());

        if (preexistingLobby != null) {
            throw new InvalidLobbyException("Player cannot create a lobby while joined in another lobby.");
        }

        Lobby lobby = new Lobby(gameTask.getPlayerId());

        dataManagementService.writeLobby(gameTask.getGameId(), lobby);

    }

    private void joinLobby(@NotNull GameTask gameTask) {

        Lobby preexistingLobby = dataManagementService.findLobbyByPlayerId(gameTask.getPlayerId());

        if (preexistingLobby != null) {
            throw new InvalidLobbyException("Player cannot join multiple lobbies.");
        }

        Lobby lobby = dataManagementService.getLobby(gameTask.getGameId());

        lobby.addPlayer(gameTask.getPlayerId());

    }

    private void leaveLobby(@NotNull GameTask gameTask) {

        Lobby lobby = dataManagementService.getLobby(gameTask.getGameId());

        if (!lobby.getPlayers().contains(gameTask.getPlayerId())) {
            throw new InvalidLobbyException("Player not found in lobby.");
        }

        lobby.removePlayer(gameTask.getPlayerId());

    }

    private void startGame(@NotNull GameTask gameTask) {

        Lobby lobby = dataManagementService.getLobby(gameTask.getGameId());

        if (!lobby.getPlayers().contains(gameTask.getPlayerId())) {
            throw new InvalidPlayerException("Player cannot start game when not part of lobby.");
        }

        Game game = new Game(gameTask.getGameId(), lobby, gameTask);

        startRound(gameTask, game);
        checkExploit(gameTask, game);
        checkBounty(gameTask, game);
        openForActions(gameTask, game);

        dataManagementService.deleteLobby(gameTask.getGameId());
        dataManagementService.writeGame(gameTask.getGameId(), game);

    }

    private void startRound(@NotNull GameTask gameTask, @NotNull Game game) {

        game.progressPlayerIndex();

        game.recordPlaybackStep(gameTask, PhaseType.ROUND_START);

    }

    private void checkExploit(@NotNull GameTask gameTask, @NotNull Game game) {

        if (game.isEnableExploits()) {
            game.getGameBoard().dealExploitCard();
        }

        game.recordPlaybackStep(gameTask, PhaseType.CHECK_EXPLOIT);

    }

    private void checkBounty(@NotNull GameTask gameTask, @NotNull Game game) {

        game.getGameBoard().dealBountyCards();

        game.recordPlaybackStep(gameTask, PhaseType.CHECK_BOUNTY);

    }

    private void  openForActions(@NotNull GameTask gameTask, @NotNull Game game) {

        game.recordPlaybackStep(gameTask, PhaseType.SELECT_ACTION);

    }

}
