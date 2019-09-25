package com.example.demo.Entities;

import com.example.demo.Enum.UserType;

import java.util.Date;
import java.util.List;

public class Player extends User {

    private List<Game> games;
    private Game currentGame;
    private Integer wonGames;
    private Integer lostGames;
    private Integer failedGames;
    private Date create;
    private Date update;



    public Player(String username, String password, UserType userType) {
        super(username, password, userType);
    }

    public Player(String username, String password, UserType userType, List<Game> games, Game currentGame, Integer wonGames, Integer lostGames, Integer failedGames, Date create, Date update) {
        super(username, password, userType);
        this.games = games;
        this.currentGame = currentGame;
        this.wonGames = wonGames;
        this.lostGames = lostGames;
        this.failedGames = failedGames;
        this.create = create;
        this.update = update;
    }
}
