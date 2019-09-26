package com.example.demo.Repositories;

import com.example.demo.Entities.Game;
import com.example.demo.Entities.Player;
import com.example.demo.Enum.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryGame extends JpaRepository<Game, Integer> {
        List<Game> findGamesByPlayerIdAndAndGameStatus(Integer id, GameStatus gameStatus);
}
