package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidLobbyException;
import com.squirrelly_app.zde_game_server.exception.InvalidPlayerException;
import com.squirrelly_app.zde_game_server.model.system.Game;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataManagementService {

    @NotNull private final Map<String, Lobby> lobbies;
    @NotNull private final Map<String, Game> games;

    public DataManagementService() {

        this.lobbies = new HashMap<>();
        this.games = new java.util.HashMap<>();

        LoggerFactory.getLogger(this.getClass())
                .info("DataManagementService initialized");

    }

    public void writeLobby(@NotNull String lobbyId, @NotNull Lobby lobby) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to create a lobby.");
        }

        lobbies.put(lobbyId, lobby);

    }

    public @NotNull Lobby getLobby(@NotNull String lobbyId) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to read a lobby.");
        }

        Lobby lobby = lobbies.get(lobbyId);

        if (lobby == null) {
            throw new InvalidLobbyException("Lobby with ID " + lobbyId + " does not exist.");
        }

        return lobby;

    }

    public @Nullable Lobby findLobbyByPlayerId(@NotNull String playerId) {

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to read a lobby.");
        }

        for (Lobby lobby : lobbies.values()) {
            if (lobby.getPlayers().contains(playerId)) {
                return lobby;
            }
        }

        return null;

    }

    public void deleteLobby(@NotNull String lobbyId) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to delete a lobby.");
        }

        if (!lobbies.containsKey(lobbyId)) {
            throw new InvalidLobbyException("Lobby with ID " + lobbyId + " does not exist.");
        }

        lobbies.remove(lobbyId);

    }

    public void writeGame(@NotNull String gameId, @NotNull Game game) {

        if (!StringUtils.hasText(gameId)) {
            throw new InvalidLobbyException("Game ID must be provided to create a game.");
        }

        games.put(gameId, game);

    }

    public @NotNull Game getGame(@NotNull String gameId) {

        if (!StringUtils.hasText(gameId)) {
            throw new InvalidLobbyException("Game ID must be provided to read a game.");
        }

        Game game = games.get(gameId);

        if (game == null) {
            throw new InvalidLobbyException("Game with ID " + gameId + " does not exist.");
        }

        return game;

    }
}
