package com.squirrelly_app.zde_game_server.model.card_addon;

import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;

public record ComponentRequirement(
        @NotNull ComponentType componentType,
        @NotNull Integer minimum
) {}
