package com.example.demo.Repositories;

import com.example.demo.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryGame extends JpaRepository<Game, Integer> {
}
