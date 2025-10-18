package com.squirrelly_app.zde_game_server.model.card;

import com.squirrelly_app.zde_game_server.model.type.BenefitType;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import org.jetbrains.annotations.NotNull;

public class ComponentCard extends Card {

    @NotNull private final BenefitType benefitType;
    @NotNull private final Integer benefitAmount;

    public ComponentCard(@NotNull String name, @NotNull CardType type, @NotNull BenefitType benefitType, @NotNull Integer benefitAmount) {
        super(name, type);
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
                "benefitType=" + benefitType +
                ", benefitAmount=" + benefitAmount +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
