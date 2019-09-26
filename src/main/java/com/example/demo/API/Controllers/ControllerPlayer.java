package com.example.demo.Controllers;

import com.example.demo.Entities.Game;
import com.example.demo.Entities.Player;
import com.example.demo.Services.ServicePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class ControllerPlayer {
    @Autowired
    private ServicePlayer servicePlayer;

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return ResponseEntity.ok(servicePlayer.findAll());
    }


}
