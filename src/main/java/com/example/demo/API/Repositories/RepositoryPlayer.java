package com.example.demo.Repositories;

import com.example.demo.Entities.Game;
import com.example.demo.Entities.Player;
import com.example.demo.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryPlayer extends JpaRepository<Player, Integer> {
    Optional<Player> findPlayerByUserId(Integer id);
    void deleteAllByUserId(Integer id);
}
