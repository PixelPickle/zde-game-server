package com.squirrelly_app.zde_game_server.model.request_contract;

import org.jetbrains.annotations.NotNull;

public record AuthorizedRequest (
        @NotNull String playerId
) { }
