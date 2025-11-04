package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidLobbyException;
import com.squirrelly_app.zde_game_server.exception.InvalidPlayerException;
import com.squirrelly_app.zde_game_server.model.request_contract.AuthorizedRequest;
import com.squirrelly_app.zde_game_server.model.system.GameTask;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
import com.squirrelly_app.zde_game_server.model.type.GameTaskType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class LobbyService {

    @NotNull private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NotNull private final GameExecutorService gameExecutorService;
    @NotNull private final DataManagementService dataManagementService;

    public LobbyService(
            @NotNull GameExecutorService gameExecutorService,
            @NotNull DataManagementService dataManagementService
    ) {

        this.gameExecutorService = gameExecutorService;
        this.dataManagementService = dataManagementService;

        logger.info("LobbyService initialized");

    }

    public String createLobby(@NotNull AuthorizedRequest request) throws ExecutionException, InterruptedException, TimeoutException {

        if (!StringUtils.hasText(request.playerId())) {
            throw new InvalidPlayerException("Player ID must be provided to create a lobby.");
        }

        String lobbyId = UUID.randomUUID().toString();

        GameTask gameTask = new GameTask(GameTaskType.CREATE_LOBBY, request.playerId(), lobbyId);

        gameExecutorService.addTask(gameTask).get(2, TimeUnit.SECONDS);

        logger.info("Lobby {} created with initial player: {}", lobbyId, request.playerId());

        return lobbyId;

    }

    public void joinLobby(@NotNull String lobbyId, @NotNull AuthorizedRequest request) throws ExecutionException, InterruptedException, TimeoutException {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to join a lobby.");
        }

        if (!StringUtils.hasText(request.playerId())) {
            throw new InvalidPlayerException("Player ID must be provided to join a lobby.");
        }

        GameTask gameTask = new GameTask(GameTaskType.JOIN_LOBBY, request.playerId(), lobbyId);

        gameExecutorService.addTask(gameTask).get(2, TimeUnit.SECONDS);

        logger.info("Player {} joined lobby {}", request.playerId(), lobbyId);

    }

    public void leaveLobby(@NotNull String lobbyId, @NotNull String playerId, @NotNull AuthorizedRequest request) throws ExecutionException, InterruptedException, TimeoutException {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to leave a lobby.");
        }

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to leave a lobby.");
        }

        if (!Objects.equals(request.playerId(), playerId)) {
            throw new InvalidPlayerException("Inconsistent player IDs provided.");
        }

        GameTask gameTask = new GameTask(GameTaskType.LEAVE_LOBBY, playerId, lobbyId);

        gameExecutorService.addTask(gameTask).get(2, TimeUnit.SECONDS);

        logger.info("Player {} left lobby {}", playerId, lobbyId);

    }

    public @NotNull Lobby getLobby(@NotNull String lobbyId, @NotNull AuthorizedRequest request) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to get a lobby.");
        }

        if (!StringUtils.hasText(request.playerId())) {
            throw new InvalidPlayerException("Player ID must be provided to get a lobby.");
        }

        Lobby lobby = dataManagementService.getLobby(lobbyId);

        if (!lobby.getPlayers().contains(request.playerId())) {
            throw new InvalidPlayerException("Player ID is not in the lobby.");
        }

        return lobby;

    }

}
