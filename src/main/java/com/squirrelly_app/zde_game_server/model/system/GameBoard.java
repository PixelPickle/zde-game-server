package com.squirrelly_app.zde_game_server.model.system;

import com.squirrelly_app.zde_game_server.model.card.BountyCard;
import com.squirrelly_app.zde_game_server.model.card.Card;
import com.squirrelly_app.zde_game_server.model.card.ExploitCard;
import com.squirrelly_app.zde_game_server.model.card.ResourceCard;
import com.squirrelly_app.zde_game_server.model.type.ComponentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class GameBoard {

    @NotNull private List<@NotNull ExploitCard> exploitCardDrawPile;
    @Nullable private ExploitCard currentExploitCard;
    @NotNull private final List<@NotNull ExploitCard> exploitCardDiscardPile;

    @NotNull private List<@NotNull BountyCard> bountyCardDrawPile;
    @NotNull private final List<@NotNull BountyCard> currentBountyCards;
    @NotNull private final List<@NotNull BountyCard> bountyCardDiscardPile;

    @NotNull private final Map<ComponentType, @NotNull List<ResourceCard>> resourceCardDrawPile;
    @NotNull private final Map<ComponentType, @NotNull List<ResourceCard>> resourceCardDiscardPile;

    @NotNull private final Map<String, @NotNull List<? extends Card>> playerHands;

    public GameBoard(@NotNull Deck deck, @NotNull List<String> playerIds) {

        this.exploitCardDrawPile = deck.exploitCards;
        this.currentExploitCard = null;
        this.exploitCardDiscardPile = new ArrayList<>();

        this.bountyCardDrawPile = deck.bountyCards;
        this.currentBountyCards = new ArrayList<>();
        this.bountyCardDiscardPile = new ArrayList<>();

        this.resourceCardDrawPile = new HashMap<>();

        this.resourceCardDrawPile.put( ComponentType.CPU, deck.resourceCards.get(ComponentType.CPU) );
        this.resourceCardDrawPile.put( ComponentType.GPU, deck.resourceCards.get(ComponentType.GPU) );
        this.resourceCardDrawPile.put( ComponentType.NIC, deck.resourceCards.get(ComponentType.NIC) );

        this.resourceCardDiscardPile = new HashMap<>();

        this.resourceCardDiscardPile.put( ComponentType.CPU, new ArrayList<>() );
        this.resourceCardDiscardPile.put( ComponentType.GPU, new ArrayList<>() );
        this.resourceCardDiscardPile.put( ComponentType.NIC, new ArrayList<>() );

        this.playerHands = new HashMap<>();

        playerIds.forEach( id -> playerHands.put( id, deck.startingHand ) );

        reshuffleExploitCards();

        reshuffleBountyCards();

        reshuffleResourceCards(ComponentType.CPU);
        reshuffleResourceCards(ComponentType.GPU);
        reshuffleResourceCards(ComponentType.NIC);

    }

    public void reshuffleExploitCards() {

        List<@NotNull ExploitCard> exploitCards = new ArrayList<>();

        exploitCards.addAll(this.exploitCardDrawPile);
        exploitCards.addAll(this.exploitCardDiscardPile);

        Collections.shuffle(exploitCards);

        this.exploitCardDrawPile = exploitCards;
        this.exploitCardDiscardPile.clear();

    }

    public void reshuffleBountyCards() {

        List<@NotNull BountyCard> bountyCards = new ArrayList<>();

        bountyCards.addAll(this.bountyCardDrawPile);
        bountyCards.addAll(this.bountyCardDiscardPile);

        Collections.shuffle(bountyCards);

        this.bountyCardDrawPile = bountyCards;
        this.bountyCardDiscardPile.clear();

    }

    public void reshuffleResourceCards(@NotNull ComponentType componentType) {

        List<@NotNull ResourceCard> resourceCards = new ArrayList<>();

        resourceCards.addAll( this.resourceCardDrawPile.get(componentType) );
        resourceCards.addAll( this.resourceCardDiscardPile.get(componentType) );

        Collections.shuffle(resourceCards);

        this.resourceCardDrawPile.put(componentType, resourceCards);
        this.resourceCardDiscardPile.put(componentType, new ArrayList<>());

    }

    public void dealExploitCard() {
        // TODO: Implement Deal Exploit Card
    }

    public void dealBountyCards() {
        // TODO: Implement Deal Bounty Cards
    }

}
