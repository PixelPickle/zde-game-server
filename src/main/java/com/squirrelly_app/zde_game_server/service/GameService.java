package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidLobbyException;
import com.squirrelly_app.zde_game_server.model.data_contract.GameStateSnapshot;
import com.squirrelly_app.zde_game_server.model.request_contract.AuthorizedRequest;
import com.squirrelly_app.zde_game_server.model.system.Game;
import com.squirrelly_app.zde_game_server.model.system.GameTask;
import com.squirrelly_app.zde_game_server.model.type.GameTaskType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GameService {

    @NotNull private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NotNull private final GameExecutorService gameExecutorService;
    @NotNull private final DataManagementService dataManagementService;

    public GameService(
            @NotNull GameExecutorService gameExecutorService,
            @NotNull DataManagementService dataManagementService
    ) {

        this.gameExecutorService = gameExecutorService;
        this.dataManagementService = dataManagementService;

        logger.info("GameService initialized");

    }

    public void startGame(@NotNull String lobbyId, @NotNull AuthorizedRequest authorizedRequest) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to start a game.");
        }

        GameTask gameTask = new GameTask(GameTaskType.START_GAME, authorizedRequest.playerId(), lobbyId);

        gameExecutorService.addTask(gameTask);

        logger.info("Game started for lobby ID: {}", lobbyId);

    }

    public @NotNull GameStateSnapshot getGameStateSnapshot(@NotNull String gameId) {

        if (!StringUtils.hasText(gameId)) {
            throw new InvalidLobbyException("Game ID must be provided to get a game's state.");
        }

        Game game = dataManagementService.getGame(gameId);

        return new GameStateSnapshot(game);

    }

}
