package com.squirrelly_app.zde_game_server.controller;

import com.squirrelly_app.zde_game_server.service.LobbyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lobby")
public class LobbyController {

    @NotNull private final LobbyService lobbyService;

    public LobbyController(@NotNull LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping(value = "", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> createLobby(@RequestBody String playerId) {
        lobbyService.createLobby(playerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{lobbyId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> joinLobby(@PathVariable String lobbyId, @RequestBody String playerId) {
        lobbyService.joinLobby(lobbyId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{lobbyId}/{playerId}")
    public ResponseEntity<Void> leaveLobby(@PathVariable String lobbyId, @PathVariable String playerId) {
        lobbyService.leaveLobby(lobbyId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
