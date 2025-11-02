package com.squirrelly_app.zde_game_server.model.type;

public enum CardType {

    SYSTEM,     // System Action Cards: Upgrade, Hack, Generate Resources
    COMPONENT,  // System Components: HDD, RAM, CPU, GPU, NIC
    RESOURCE,   // Generated Resources for Targeting Bounties
    BOUNTY,     // Target Card used to Earn Bytecoin
    EXPLOIT     // Target Card used to Earn Victory Points

}
