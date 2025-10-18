package com.squirrelly_app.zde_game_server.model.card;

import com.squirrelly_app.zde_game_server.model.card_addon.ComponentRequirement;
import com.squirrelly_app.zde_game_server.model.card_addon.SecurityVulnerability;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BountyCard extends TargetCard {

    @NotNull private final List<SecurityVulnerability> vulnerabilities;
    @NotNull private final Integer bruteForceRewardAmount;

    public BountyCard(@NotNull String name, @NotNull CardType type, @NotNull Integer bruteForceRewardAmount) {
        super(name, type);
        this.vulnerabilities = new ArrayList<>();
        this.bruteForceRewardAmount = bruteForceRewardAmount;
    }

    public BountyCard(@NotNull String name, @NotNull CardType type, @NotNull List<ComponentRequirement> bruteForceRequirements, @NotNull Integer bruteForceRewardAmount, @NotNull List<SecurityVulnerability> vulnerabilities) {
        super(name, type, bruteForceRequirements);
        this.vulnerabilities = vulnerabilities;
        this.bruteForceRewardAmount = bruteForceRewardAmount;
    }

    public @NotNull List<SecurityVulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public @NotNull Integer getBruteForceRewardAmount() {
        return bruteForceRewardAmount;
    }

    @Override
    public String toString() {
        return "BountyCard{" +
                "vulnerabilities=" + vulnerabilities +
                ", bruteForceRewardAmount=" + bruteForceRewardAmount +
                ", bruteForceRequirements=" + bruteForceRequirements +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
