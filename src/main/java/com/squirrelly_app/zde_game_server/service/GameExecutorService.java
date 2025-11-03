package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidGameTaskException;
import com.squirrelly_app.zde_game_server.model.system.GameTask;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
import com.squirrelly_app.zde_game_server.model.type.GameTaskType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

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

    public void addTask(@NotNull GameTask gameTask) {

        executorService.execute( () -> {

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

        Lobby lobby = new Lobby(gameTask.getPlayerId());

        dataManagementService.writeLobby(gameTask.getGameId(), lobby);

    }

    private void joinLobby(@NotNull GameTask gameTask) {

        Lobby lobby = dataManagementService.getLobby(gameTask.getGameId());

        lobby.addPlayer(gameTask.getPlayerId());

    }

    private void leaveLobby(@NotNull GameTask gameTask) {

        Lobby lobby = dataManagementService.getLobby(gameTask.getGameId());

        lobby.removePlayer(gameTask.getPlayerId());

    }

    private void startGame(@NotNull GameTask gameTask) {

        // TODO: Implement Game Start Process

    }

}
