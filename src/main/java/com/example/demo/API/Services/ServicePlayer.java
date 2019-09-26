package com.example.demo.API.Services;


import com.example.demo.API.Entities.Game;
import com.example.demo.API.Entities.Player;
import com.example.demo.API.Enum.GameStatus;
import com.example.demo.API.Repositories.RepositoryGame;
import com.example.demo.API.Repositories.RepositoryPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePlayer {
    @Autowired
    private RepositoryPlayer repositoryPlayer;

    public List<Player> findAll() {
        return repositoryPlayer.findAll();
    }

    public Optional<Player> findById(Integer id) {
        return repositoryPlayer.findById(id);
    }

    public Optional<Player> findByUserId(Integer id){
        return repositoryPlayer.findPlayerByUserId(id);
    }

    public Player save(Player stock) {
        return repositoryPlayer.save(stock);
    }

    public void deleteById(Integer id) {
        repositoryPlayer.deleteById(id);

    }

    public void deleteByUserId(Integer id) {
        repositoryPlayer.deleteAllByUserId(id);

    }



}
