package com.squirrelly_app.zde_game_server.service;

import com.squirrelly_app.zde_game_server.exception.InvalidLobbyException;
import com.squirrelly_app.zde_game_server.model.system.Game;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class GameService {

    @NotNull private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NotNull private final LobbyService lobbyService;

    @NotNull private final Map<String, Game> games;

    public GameService(
            @NotNull LobbyService lobbyService
    ) {

        this.lobbyService = lobbyService;

        this.games = new java.util.HashMap<>();

        logger.info("GameService initialized");

    }

    public void startGame(@NotNull String lobbyId) {

        if (!StringUtils.hasText(lobbyId)) {
            throw new InvalidLobbyException("Lobby ID must be provided to start a game.");
        }

        Lobby lobby = this.lobbyService.getLobby(lobbyId);

        List<String> playerIds = lobby.getPlayers().stream().toList();

        Game game = new Game(playerIds);

        games.put(lobbyId, game);

        logger.info("Game started for lobby ID: {}", lobbyId);

    }
}
