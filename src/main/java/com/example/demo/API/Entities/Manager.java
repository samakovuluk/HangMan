package com.example.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @Column(name = "id")
    private Integer id;

}
