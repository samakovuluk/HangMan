package com.example.demo.API.Services;

import com.example.demo.API.Entities.Player;
import com.example.demo.API.Entities.Users;
import com.example.demo.API.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicePlayer servicePlayer;

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Optional<Users> findbyusername(String username) {
        return userRepository.findByUsername(username);
    }


    public Optional<Users> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Users save(Users stock) {
        stock.encryptPassword();
        stock = userRepository.save(stock);
        servicePlayer.save(new Player(stock.getId()));
        return stock;
    }

    public void deleteById(Integer id) {
        servicePlayer.deleteByUserId(id);
        userRepository.deleteById(id);
    }


}