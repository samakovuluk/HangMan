package com.example.demo.API.Services;

import com.example.demo.API.Entities.Users;
import com.example.demo.API.Entities.Words;
import com.example.demo.API.Repositories.RepositoryWord;
import com.example.demo.API.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceWord {

    @Autowired
    private RepositoryWord repositoryWord;

    public Optional<Words> findById(Integer id) {
        return repositoryWord.findById(id);
    }

    public List<Words> findAll() {
        return repositoryWord.findAll();
    }

    public Words save(Words stock) {
        return repositoryWord.save(stock);
    }

    public void deleteById(Integer id) {
        repositoryWord.deleteById(id);
    }

}
