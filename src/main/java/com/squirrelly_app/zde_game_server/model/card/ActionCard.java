package com.squirrelly_app.zde_game_server.model.card;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

public class ActionCard extends Card {

    @NotNull protected final String actionEffect;
    @Nullable protected final ComponentEnum componentType;

    public ActionCard(@NotNull String name, @NotNull CardType type, @NotNull String actionEffect, @Nullable ComponentEnum componentType) {
        super(name, type);
        this.actionEffect = actionEffect;
        this.componentType = componentType;
    }

}
