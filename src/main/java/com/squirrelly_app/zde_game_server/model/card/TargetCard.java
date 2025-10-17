package com.squirrelly_app.zde_game_server.model.card;

import com.squirrelly_app.zde_game_server.model.BruteForceRequirement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TargetCard extends Card {

    @NotNull protected final List<BruteForceRequirement> bruteForceRequirements;

    public TargetCard(@NotNull String name, @NotNull CardType type) {
        super(name, type);
        this.bruteForceRequirements = new ArrayList<>();
    }

    public TargetCard(@NotNull String name, @NotNull CardType type, @NotNull List<BruteForceRequirement> bruteForceRequirements) {
        super(name, type);
        this.bruteForceRequirements = bruteForceRequirements;
    }

}
