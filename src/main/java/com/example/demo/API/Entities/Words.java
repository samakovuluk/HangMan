package com.example.demo.Entities;

import org.graalvm.compiler.word.Word;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Words {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "word")
    private String word;

    public Words(){

    }

    public Words(String word) {
        this.word = word;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
