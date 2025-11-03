package com.squirrelly_app.zde_game_server.exception;

import com.squirrelly_app.zde_game_server.model.system.GameTask;

public class InvalidGameTaskException extends RuntimeException {
    public InvalidGameTaskException(GameTask gameTask) {
        super(String.format("Invalid game task: %s", gameTask.toString()));
    }
}
