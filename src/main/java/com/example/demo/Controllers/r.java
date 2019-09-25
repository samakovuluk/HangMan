package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class r {

    @GetMapping("/{id}")
    public Integer findById(@PathVariable Integer id) {

        return id;
    }

}
