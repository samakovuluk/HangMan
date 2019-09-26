package com.example.demo.API.Repositories;

import com.example.demo.API.Entities.Game;
import com.example.demo.API.Entities.Player;
import com.example.demo.API.Enum.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryGame extends JpaRepository<Game, Integer> {
        List<Game> findGamesByPlayerIdAndAndGameStatus(Integer id, GameStatus gameStatus);
}
