package com.example.demo.API.Entities;

import com.example.demo.API.Enum.GameStatus;
import com.example.demo.API.Enum.GuessStatus;
import com.example.demo.API.Variables.Variable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Entity
@Table(name = "Game")
@TypeDef(
    name = "string-array",
    typeClass = StringArrayType.class
)
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;


    @Column(name = "playerId")
    private Integer playerId;


    @Column(name = "secretWord")
    private String secretWord;

    @Column(name = "visibleWord")
    private String visibleWord;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gameStatus")
    private GameStatus gameStatus;

    @Column(name = "attemptsLeft")
    private Integer attemptsLeft;

    @Type( type = "string-array")
    @Column(name = "availableLetters", columnDefinition = "text[]")
    private String[] availableLetters;

    @Transient
    private GuessStatus guessStatus;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    private Date updateDate;




    public Game(){ }

    public Game(String secretWord , Integer playerId) {


        this.secretWord = secretWord;
        this.visibleWord = Variable.hideWordCharacter.toString().repeat(secretWord.length());
        this.gameStatus = GameStatus.ACTIVE;
        this.attemptsLeft = Variable.numberForAttempts;
        this.availableLetters = Variable.letters;
        this.guessStatus = GuessStatus.STARTED;
        this.playerId = playerId;
    }

    public void guess(String letter){
        if (secretWord.contains(letter) && letter.length()==1 && getAvailableLetters().contains(letter) ) {
           addLetterToVisibleWord(letter);
        }
        else if (getAvailableLetters().contains(letter)){
            removeLetterFromAvailableLetters(letter);
            guessStatus=GuessStatus.MISSED;
            attemptsLeft-=1;
        }
        else {
            guessStatus=GuessStatus.AGAIN;
        }
        if (!visibleWord.contains(Variable.hideWordCharacter.toString())){
            gameStatus=GameStatus.WON;
        }
        else if (attemptsLeft==0){
            gameStatus=GameStatus.FAILED;
        }
    }
    public void addLetterToVisibleWord(String letter){
        StringBuilder word = new StringBuilder(visibleWord);
        for (int i=0;i<word.length();i++) {
            if (secretWord.charAt(i)==letter.charAt(0))
                word.setCharAt(i,letter.charAt(0));
        }
        setVisibleWord(word.toString());
        removeLetterFromAvailableLetters(letter);
        guessStatus=GuessStatus.GUESSED;
    }
    public void removeLetterFromAvailableLetters(String letter){
        List<String> arrayList = getAvailableLetters();

        arrayList.remove(letter);

        setAvailableLetters(arrayList);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecretWord() {
        if (gameStatus!=GameStatus.ACTIVE)
        return secretWord;
        else return "*hided*";
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

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Integer getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(Integer attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public List<String> getAvailableLetters() {
        return new LinkedList<String>(Arrays.asList(availableLetters));
    }

    public void setAvailableLetters(List<String> availableLetters) {
        this.availableLetters = availableLetters.toArray(new String[availableLetters.size()]);
    }

    public void setAvailableLetters(String[] availableLetters) {
        this.availableLetters = availableLetters;
    }

    public GuessStatus getGuessStatus() {
        return guessStatus;
    }

    public void setGuessStatus(GuessStatus guessStatus) {
        this.guessStatus = guessStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
