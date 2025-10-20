package com.squirrelly_app.zde_game_server.model.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squirrelly_app.zde_game_server.model.card_addon.ActionEffect;
import com.squirrelly_app.zde_game_server.model.type.CardType;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;

public class ActionCard extends Card {

    @NotNull protected final ActionEffect actionEffect;
    @NotNull protected final ComponentType componentType;

    public ActionCard(
            @NotNull @JsonProperty("name") String name,
            @NotNull @JsonProperty("type") CardType type,
            @NotNull @JsonProperty("actionEffect") ActionEffect actionEffect,
            @NotNull @JsonProperty("componentType") ComponentType componentType
    ) {
        super(name, type);
        this.actionEffect = actionEffect;
        this.componentType = componentType;
    }

    public @NotNull ActionEffect getActionEffect() {
        return actionEffect;
    }

    public @NotNull ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public String toString() {
        return "ActionCard{" +
                "actionEffect=" + actionEffect +
                ", componentType=" + componentType +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
