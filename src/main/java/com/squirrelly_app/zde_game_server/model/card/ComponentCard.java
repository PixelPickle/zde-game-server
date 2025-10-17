package com.squirrelly_app.zde_game_server.model.card;

import org.jetbrains.annotations.NotNull;

public class ComponentCard extends ActionCard {

    public ComponentCard(@NotNull String name, @NotNull CardType type, @NotNull String actionEffect, @NotNull ComponentEnum componentType) {
        super(name, type, actionEffect, componentType);
    }

}
