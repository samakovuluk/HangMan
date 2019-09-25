package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ko")
public class ent {
    @Id
    @Column(name = "sad")
    Integer id;
}
