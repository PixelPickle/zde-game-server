package com.squirrelly_app.zde_game_server.model.card;

import com.squirrelly_app.zde_game_server.model.BruteForceRequirement;
import com.squirrelly_app.zde_game_server.model.SecurityVulnerability;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BountyCard extends TargetCard {

    @NotNull private final List<SecurityVulnerability> vulnerabilities;

    public BountyCard(@NotNull String name, @NotNull CardType type) {
        super(name, type);
        this.vulnerabilities = new ArrayList<>();
    }

    public BountyCard(@NotNull String name, @NotNull CardType type, @NotNull List<BruteForceRequirement> bruteForceRequirements, @NotNull List<SecurityVulnerability> vulnerabilities) {
        super(name, type, bruteForceRequirements);
        this.vulnerabilities = vulnerabilities;
    }

}
