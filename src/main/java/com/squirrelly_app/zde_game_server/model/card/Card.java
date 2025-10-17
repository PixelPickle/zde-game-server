package com.squirrelly_app.zde_game_server.model.card;

import org.jetbrains.annotations.NotNull;

public class Card {

    @NotNull protected final String name;
    @NotNull protected final CardType type;

    public Card(@NotNull String name, @NotNull CardType type) {
        this.name = name;
        this.type = type;
    }

}
