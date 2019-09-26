package com.example.demo.API.Repositories;

import com.example.demo.API.Entities.Game;
import com.example.demo.API.Entities.Player;
import com.example.demo.API.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryPlayer extends JpaRepository<Player, Integer> {
    Optional<Player> findPlayerByUserId(Integer id);
    void deleteAllByUserId(Integer id);
}
