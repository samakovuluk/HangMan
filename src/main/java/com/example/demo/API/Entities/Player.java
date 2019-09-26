package com.example.demo.Entities;

import com.example.demo.Enum.UserType;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @OneToMany(mappedBy = "playerId")
    private List<Game> games;






    @Formula("(select count(*) from game u where u.player_id = id and u.game_status = 'WON' )")
    private Integer wonGames;
    @Formula("(select count(*) from game u where u.player_id = id and u.game_status = 'LOST' )")
    private Integer lostGames;
    @Formula("(select count(*) from game u where u.player_id = id and u.game_status = 'FAILED' )")
    private Integer failedGames;
//    private Date create;
//    private Date update;

    public Player(){

    }
    public Player(Integer userId){
        this.userId=userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Integer getWonGames() {
        return wonGames;
    }

    public void setWonGames(Integer wonGames) {
        this.wonGames = wonGames;
    }

    public Integer getLostGames() {
        return lostGames;
    }

    public void setLostGames(Integer lostGames) {
        this.lostGames = lostGames;
    }

    public Integer getFailedGames() {
        return failedGames;
    }

    public void setFailedGames(Integer failedGames) {
        this.failedGames = failedGames;
    }
}
