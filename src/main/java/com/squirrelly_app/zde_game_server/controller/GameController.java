package com.squirrelly_app.zde_game_server.controller;

import com.squirrelly_app.zde_game_server.model.data_contract.GameStateSnapshot;
import com.squirrelly_app.zde_game_server.model.request_contract.AuthorizedRequest;
import com.squirrelly_app.zde_game_server.service.GameService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @NotNull private final GameService gameService;

    public GameController(@NotNull GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/{gameId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> startGame(@NotNull @PathVariable String gameId, @RequestBody @NotNull AuthorizedRequest authorizedRequest) {
        gameService.startGame(gameId, authorizedRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameStateSnapshot> getGameStateSnapshot(@NotNull @PathVariable String gameId) {
        GameStateSnapshot gameStateSnapshot = gameService.getGameStateSnapshot(gameId);
        return new ResponseEntity<>(gameStateSnapshot, HttpStatus.OK);
    }

}
