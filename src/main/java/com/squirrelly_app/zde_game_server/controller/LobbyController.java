package com.squirrelly_app.zde_game_server.controller;

import com.squirrelly_app.zde_game_server.model.request_contract.AuthorizedRequest;
import com.squirrelly_app.zde_game_server.model.system.Lobby;
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

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createLobby(@NotNull @RequestBody AuthorizedRequest request) {
        String lobbyId = lobbyService.createLobby(request);
        return new ResponseEntity<>(lobbyId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{lobbyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> joinLobby(@NotNull @PathVariable String lobbyId, @NotNull @RequestBody AuthorizedRequest request) {
        lobbyService.joinLobby(lobbyId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{lobbyId}/{playerId}")
    public ResponseEntity<Void> leaveLobby(@NotNull @PathVariable String lobbyId, @NotNull @PathVariable String playerId, @NotNull @RequestBody AuthorizedRequest request) {
        lobbyService.leaveLobby(lobbyId, playerId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{lobbyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lobby> getLobby(@NotNull @PathVariable String lobbyId, @NotNull @RequestBody AuthorizedRequest request) {
        Lobby lobby = lobbyService.getLobby(lobbyId, request);
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

}
