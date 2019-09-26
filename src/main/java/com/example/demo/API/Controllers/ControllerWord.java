package com.example.demo.API.Controllers;

import com.example.demo.API.Entities.Words;
import com.example.demo.API.Services.ServiceGame;
import com.example.demo.API.Services.ServiceWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/words")
public class ControllerWord {
    @Autowired
    ServiceWord serviceWord;

    @GetMapping
    public ResponseEntity<List<Words>> findAll() {

        return ResponseEntity.ok(serviceWord.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Words words) {
        return ResponseEntity.ok(serviceWord.save(words));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Words> findById(@PathVariable Integer id) {
        Optional<Words> stock = serviceWord.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        if (!serviceWord.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        serviceWord.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
