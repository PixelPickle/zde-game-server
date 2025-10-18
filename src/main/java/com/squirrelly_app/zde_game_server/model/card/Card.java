package com.squirrelly_app.zde_game_server.model.card;

import com.squirrelly_app.zde_game_server.model.type.CardType;
import org.jetbrains.annotations.NotNull;

public class Card {

    @NotNull protected final String name;
    @NotNull protected final CardType type;

    public Card(@NotNull String name, @NotNull CardType type) {
        this.name = name;
        this.type = type;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull CardType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
