package com.squirrelly_app.zde_game_server.model.card;

import com.squirrelly_app.zde_game_server.model.card_addon.ComponentRequirement;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TargetCard extends Card {

    @NotNull protected final List<ComponentRequirement> bruteForceRequirements;

    public TargetCard(@NotNull String name, @NotNull CardType type) {
        super(name, type);
        this.bruteForceRequirements = new ArrayList<>();
    }

    public TargetCard(@NotNull String name, @NotNull CardType type, @NotNull List<ComponentRequirement> bruteForceRequirements) {
        super(name, type);
        this.bruteForceRequirements = bruteForceRequirements;
    }

    public @NotNull List<ComponentRequirement> getBruteForceRequirements() {
        return bruteForceRequirements;
    }

    @Override
    public String toString() {
        return "TargetCard{" +
                "bruteForceRequirements=" + bruteForceRequirements +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
