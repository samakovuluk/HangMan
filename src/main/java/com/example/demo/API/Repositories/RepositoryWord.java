package com.example.demo.API.Repositories;

import com.example.demo.API.Entities.Player;
import com.example.demo.API.Entities.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryWord extends JpaRepository<Words, Integer> {

}
