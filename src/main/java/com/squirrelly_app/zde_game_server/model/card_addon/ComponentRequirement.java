package com.squirrelly_app.zde_game_server.model.card_addon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;

public record ComponentRequirement(
        @NotNull @JsonProperty("componentType") ComponentType componentType,
        @NotNull @JsonProperty("minimum") Integer minimum
) {}
