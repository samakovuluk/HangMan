package com.example.demo.Repositories;

import com.example.demo.Entities.Player;
import com.example.demo.Entities.Words;
import org.graalvm.compiler.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryWord extends JpaRepository<Words, Integer> {

}
