package com.squirrelly_app.zde_game_server.model.card_addon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.type.ActionType;
import com.squirrelly_app.zde_game_server.model.type.TimingType;
import org.jetbrains.annotations.NotNull;

public record ActionEffect(
        @NotNull @JsonProperty("actionType") ActionType actionType,
        @NotNull @JsonProperty("timingType") TimingType timingType,
        @NotNull @JsonProperty("actionName") String actionName,
        @NotNull @JsonProperty("actionDescription") String actionDescription
) { }
