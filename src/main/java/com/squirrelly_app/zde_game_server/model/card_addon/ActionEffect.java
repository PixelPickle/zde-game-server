package com.squirrelly_app.zde_game_server.model.card_addon;

import com.squirrelly_app.zde_game_server.model.type.ActionType;
import com.squirrelly_app.zde_game_server.model.type.TimingType;
import org.jetbrains.annotations.NotNull;

public record ActionEffect(
        @NotNull ActionType actionType,
        @NotNull TimingType timingType,
        @NotNull String actionName,
        @NotNull String actionDescription
) { }
