package com.example.demo.Services;

import com.example.demo.Entities.Game;
import com.example.demo.Enum.GameStatus;
import com.example.demo.Repositories.RepositoryGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceGame {
    @Autowired
    private RepositoryGame repositoryGame;

    public List<Game> findAll() {
        return repositoryGame.findAll();
    }

    public Optional<Game> findById(Integer id) {
        return repositoryGame.findById(id);
    }

    public void refresh(Integer id ){
        List<Game> games = repositoryGame.findGamesByPlayerIdAndAndGameStatus(id ,GameStatus.ACTIVE);
        for (Game game:games) {
            game.setGameStatus(GameStatus.LOST);
        }

    }
    public Game save(Game stock) {
        return repositoryGame.save(stock);
    }

    public Game update(Integer id, Game stock){
        Game real = repositoryGame.findById(id).get();
        return repositoryGame.save(real);
    }
    public Game check(Integer id, String letter){
        Game real = repositoryGame.findById(id).get();
        if (real.getGameStatus()== GameStatus.ACTIVE)
        real.guess(letter);
        return repositoryGame.save(real);
    }

    public void deleteById(Integer id) {
        repositoryGame.deleteById(id);
    }


}
