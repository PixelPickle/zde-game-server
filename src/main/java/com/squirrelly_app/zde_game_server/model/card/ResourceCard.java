package com.squirrelly_app.zde_game_server.model.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.card_addon.ActionEffect;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResourceCard extends ActionCard {

    @Nullable private final ComponentType attackType;
    @Nullable private final ComponentType defendType;

    public ResourceCard(
            @NotNull @JsonProperty("name") String name,
            @NotNull @JsonProperty("type") CardType type,
            @NotNull @JsonProperty("actionEffect") ActionEffect actionEffect,
            @NotNull @JsonProperty("componentType") ComponentType componentType,
            @Nullable @JsonProperty("attackType") ComponentType attackType,
            @Nullable @JsonProperty("defendType") ComponentType defendType
    ) {
        super(name, type, actionEffect, componentType);
        this.attackType = attackType;
        this.defendType = defendType;
    }

    public @Nullable ComponentType getAttackType() {
        return attackType;
    }

    public @Nullable ComponentType getDefendType() {
        return defendType;
    }

    @Override
    public String toString() {
        return "ResourceCard{" +
                "attackType=" + attackType +
                ", defendType=" + defendType +
                ", actionEffect=" + actionEffect +
                ", componentType=" + componentType +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
