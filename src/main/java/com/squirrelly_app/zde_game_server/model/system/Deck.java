package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.model.card.*;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class Deck {

    @NotNull public final List<ActionCard> startingHand;
    @NotNull public final List<ExploitCard> exploitCards;
    @NotNull public final List<BountyCard> bountyCards;
    @NotNull public final List<ComponentCard> componentCards;
    @NotNull public final Map<ComponentType, List<ResourceCard>> resourceCards;

    public Deck(
            @NotNull List<ActionCard> startingHand,
            @NotNull List<ExploitCard> exploitCards,
            @NotNull List<BountyCard> bountyCards,
            @NotNull List<ComponentCard> componentCards,
            @NotNull Map<ComponentType, List<ResourceCard>> resourceCards
    ) {
        this.startingHand = startingHand;
        this.exploitCards = exploitCards;
        this.bountyCards = bountyCards;
        this.componentCards = componentCards;
        this.resourceCards = resourceCards;
    }



}
