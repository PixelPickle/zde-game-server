package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidLobbyException;
import com.squirrelly_app.zde_game_server.exception.InvalidPlayerException;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class LobbyService {

    @NotNull private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NotNull private final Map<String, Lobby> lobbies;

    public LobbyService() {

        this.lobbies = new HashMap<>();

        logger.info("LobbyService initialized");

    }

    public void createLobby(@NotNull String playerId) {

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to create a lobby.");
        }

        Lobby lobby = new Lobby(playerId);

        lobbies.put(playerId, lobby);

        logger.info("Lobby created with initial player: {}", playerId);

    }

    public void joinLobby(@NotNull String lobbyId, @NotNull String playerId) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to join a lobby.");
        }

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to join a lobby.");
        }

        Lobby lobby = lobbies.get(lobbyId);

        if (lobby == null) {
            throw new InvalidLobbyException("Lobby with ID " + lobbyId + " does not exist.");
        }

        lobby.addPlayer(playerId);

        logger.info("Player {} joined lobby {}", playerId, lobbyId);

    }

    public void leaveLobby(@NotNull String lobbyId, @NotNull String playerId) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to leave a lobby.");
        }

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to leave a lobby.");
        }

        Lobby lobby = lobbies.get(lobbyId);

        if (lobby == null) {
            throw new InvalidLobbyException("Lobby with ID " + lobbyId + " does not exist.");
        }

        lobby.removePlayer(playerId);

        logger.info("Player {} left lobby {}", playerId, lobbyId);

    }

    public @NotNull Lobby getLobby(@NotNull String lobbyId) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to get a lobby.");
        }

        Lobby lobby = lobbies.get(lobbyId);

        if (lobby == null) {
            throw new InvalidLobbyException("Lobby with ID " + lobbyId + " does not exist.");
        }

        return lobby;

    }

}
