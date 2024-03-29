package com.example.demo.API.Controllers;

import com.example.demo.API.Entities.Users;
import com.example.demo.API.Services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

    @Autowired
    private ServiceUser serviceUser;

    @GetMapping
    public ResponseEntity<List<Users>> findAll() {

        return ResponseEntity.ok(serviceUser.findAll());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Users user) {

        return ResponseEntity.ok(serviceUser.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id) {
        Optional<Users> stock = serviceUser.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        if (!serviceUser.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        serviceUser.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
