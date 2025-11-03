package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.exception.InvalidPlayerException;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class Lobby {

    @NotNull private final Set<String> players;

    public Lobby(@NotNull String playerId) {

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to create a lobby.");
        }

        this.players = new HashSet<>();
        this.players.add(playerId);

    }

    public void addPlayer(@NotNull String playerId) {

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to join a lobby.");
        }

        this.players.add(playerId);

    }

    public void removePlayer(@NotNull String playerId) {

        if (!StringUtils.hasText(playerId)) {
            throw new InvalidPlayerException("Player ID must be provided to leave a lobby.");
        }

        this.players.remove(playerId);

    }

    public @NotNull Set<String> getPlayers() {
        return players;
    }

}
