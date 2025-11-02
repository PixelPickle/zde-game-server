package com.squirrelly_app.zde_game_server.model.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.type.BenefitType;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;

public class ComponentCard extends Card {

    @NotNull private final ComponentType componentType;
    @NotNull private final BenefitType benefitType;
    @NotNull private final Integer benefitAmount;

    public ComponentCard(
            @NotNull @JsonProperty("name") String name,
            @NotNull @JsonProperty("type") CardType type,
            @NotNull @JsonProperty("componentType") ComponentType componentType,
            @NotNull @JsonProperty("benefitType") BenefitType benefitType,
            @NotNull @JsonProperty("benefitAmount") Integer benefitAmount
    ) {
        super(name, type);
        this.componentType = componentType;
        this.benefitType = benefitType;
        this.benefitAmount = benefitAmount;
    }

    public @NotNull BenefitType getBenefitType() {
        return benefitType;
    }

    public @NotNull Integer getBenefitAmount() {
        return benefitAmount;
    }

    @Override
    public String toString() {
        return "ComponentCard{" +
                "componentType=" + componentType +
                ", benefitType=" + benefitType +
                ", benefitAmount=" + benefitAmount +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
