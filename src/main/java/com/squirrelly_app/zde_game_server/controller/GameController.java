package com.squirrelly_app.zde_game_server.controller;

import com.squirrelly_app.zde_game_server.service.GameService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @NotNull private final GameService gameService;

    public GameController(@NotNull GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "", consumes = "text/plain")
    public ResponseEntity<Void> startGame(@RequestBody String lobbyId) {
        gameService.startGame(lobbyId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
