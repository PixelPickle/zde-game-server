package com.squirrelly_app.zde_game_server.model.type;

public enum ActionType {

    SYSTEM_UPGRADE, // Shutdown System & Install Component
    HACK_TARGET,    // Hack Bounty or Exploit Targets
    GENERATE,       // Generate Component Resources
    OVERCLOCK,      // Boost System Components during HACK_TARGET Action Types
    INTERRUPT,      // Nullify Opponents' GENERATE Action Types
    DEFEND,         // Counteract Opponents' INTERRUPT Action Types

}
