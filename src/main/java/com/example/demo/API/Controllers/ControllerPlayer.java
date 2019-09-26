package com.example.demo.API.Controllers;

import com.example.demo.API.Entities.Game;
import com.example.demo.API.Entities.Player;
import com.example.demo.API.Services.ServicePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class ControllerPlayer {
    @Autowired
    private ServicePlayer servicePlayer;

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return ResponseEntity.ok(servicePlayer.findAll());
    }


}
