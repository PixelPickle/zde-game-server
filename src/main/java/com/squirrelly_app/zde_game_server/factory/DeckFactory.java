package com.squirrelly_app.zde_game_server.factory;

import com.squirrelly_app.zde_game_server.model.Deck;
import com.squirrelly_app.zde_game_server.model.card.ResourceCard;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DeckFactory {

    private static final Integer resourceCardMultiplier = 10;

    public static @NotNull Deck createDeck() {

        Map<ComponentType, List<ResourceCard>> componentCards = new HashMap<>();

        componentCards.put(ComponentType.CPU, constructResourceCardsByType(ComponentType.CPU));
        componentCards.put(ComponentType.GPU, constructResourceCardsByType(ComponentType.GPU));
        componentCards.put(ComponentType.NIC, constructResourceCardsByType(ComponentType.NIC));

        return new Deck(
                CardFactory.getSystemActionCards(),
                CardFactory.getExploitCards(),
                CardFactory.getBountyCards(),
                CardFactory.getComponentCards(),
                componentCards
        );

    }

    private static @NotNull List<ResourceCard> constructResourceCardsByType(@NotNull ComponentType componentType) {

        List<ResourceCard> baseResourceCards = CardFactory.getResourceCards()
                .stream()
                .filter( (aCard) -> aCard.getComponentType() == componentType)
                .toList();

        List<ResourceCard> resourceCards = new ArrayList<>();

        baseResourceCards.forEach( (baseResourceCard) -> resourceCards.addAll( Collections.nCopies(resourceCardMultiplier, baseResourceCard) ));

        return Collections.unmodifiableList(resourceCards);

    }

}
