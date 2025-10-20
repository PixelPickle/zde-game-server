package com.squirrelly_app.zde_game_server.model.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.card_addon.ComponentRequirement;
import com.squirrelly_app.zde_game_server.model.card_addon.SecurityVulnerability;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BountyCard extends TargetCard {

    @NotNull private final List<SecurityVulnerability> vulnerabilities;
    @NotNull private final Integer bruteForceRewardAmount;

    public BountyCard(
            @NotNull @JsonProperty("name") String name,
            @NotNull @JsonProperty("type") CardType type,
            @NotNull @JsonProperty("bruteForceRequirements") List<ComponentRequirement> bruteForceRequirements,
            @NotNull @JsonProperty("bruteForceRewardAmount") Integer bruteForceRewardAmount,
            @NotNull @JsonProperty("vulnerabilities") List<SecurityVulnerability> vulnerabilities
    ) {
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
