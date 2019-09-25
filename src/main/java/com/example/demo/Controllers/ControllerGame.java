package com.example.demo.Controllers;

import com.example.demo.Entities.Game;
import com.example.demo.Services.ServiceGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class ControllerGame {
    @Autowired
    private ServiceGame serviceGame;


    @GetMapping
    public ResponseEntity<List<Game>> findAll(){
        return ResponseEntity.ok(serviceGame.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable Integer id) {
        Optional<Game> stock = serviceGame.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }
    @PostMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable Integer id, @Valid @RequestBody String letter) {
        if (!serviceGame.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(serviceGame.check(id,letter));
    }




}
