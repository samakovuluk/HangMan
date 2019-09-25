package com.example.demo.Entities;

import com.example.demo.Enum.GameStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Entity(name = "Game")
@Table(name = "Game")
public class Game {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;


    private Player player;
    @Column(name = "secretWord")
    private String secretWord;
    @Column(name = "visibleWord")
    private String visibleWord;
    @Column(name = "attemptsLeft")
    private Integer attemptsLeft;
    @ElementCollection
    @Column(name = "availableLetters")
    private List<Character> availableLetters;
    @Enumerated
    @Column(name = "gameStatus")
    private GameStatus gameStatus;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;

    private final Integer numberForAttempts= 7;
    private final Character[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final String hideWordCharacter = "?";

    public Game(Player player, String secretWord) {
        this.player = player;
        this.secretWord = secretWord;
        this.visibleWord = hideWordCharacter.repeat(secretWord.length());
        this.attemptsLeft = numberForAttempts;
        this.availableLetters = Arrays.asList(letters);
        this.gameStatus = GameStatus.ACTIVE;
        this.dateCreate = LocalDateTime.now();

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public void setVisibleWord(String visibleWord) {
        this.visibleWord = visibleWord;
    }

    public Integer getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(Integer attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public List<Character> getAvailableLetters() {
        return availableLetters;
    }

    public void setAvailableLetters(List<Character> availableLetters) {
        this.availableLetters = availableLetters;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getNumberForAttempts() {
        return numberForAttempts;
    }

    public Character[] getLetters() {
        return letters;
    }
}
