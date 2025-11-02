package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.exception.InvalidPlayerException;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class Lobby {

    @NotNull private final Set<String> players;

    public Lobby(@NotNull String player) {
        this.players = new HashSet<>();
        this.players.add(player);
    }

    public void addPlayer(@NotNull String player) {

        if (!StringUtils.hasText(player)) {
            throw new InvalidPlayerException("Player ID must be provided to join a lobby.");
        }

        this.players.add(player);

    }

    public void removePlayer(@NotNull String player) {

        if (!StringUtils.hasText(player)) {
            throw new InvalidPlayerException("Player ID must be provided to leave a lobby.");
        }

        this.players.remove(player);

    }

    public @NotNull Set<String> getPlayers() {
        return players;
    }

}
