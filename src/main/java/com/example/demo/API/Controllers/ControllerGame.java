package com.example.demo.API.Controllers;

import com.example.demo.API.Entities.Game;
import com.example.demo.API.Entities.Player;
import com.example.demo.API.Entities.Words;
import com.example.demo.API.Services.ServiceGame;
import com.example.demo.API.Services.ServicePlayer;
import com.example.demo.API.Services.ServiceWord;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/api/game")
public class ControllerGame {
    @Autowired
    private ServiceGame serviceGame;

    @Autowired
    private ServicePlayer servicePlayer;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ServiceWord serviceWord;

    @GetMapping
    public ResponseEntity<List<Game>> findAll(){
        return ResponseEntity.ok(serviceGame.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable Integer id) {
        Optional<Game> stock = serviceGame.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }
    @GetMapping("/start")
    public ResponseEntity<Game> start(HttpServletRequest req) {

        String username =  getUserName(req.getHeader("Authorization"));
        Query query = entityManager.createNativeQuery("SELECT id FROM users WHERE username = :username");
        query.setParameter("username", username);
        List<Object> rows =query.getResultList();
        int i=(Integer.parseInt(rows.get(0).toString()));
        Optional<Player> player = servicePlayer.findByUserId(Integer.parseInt(rows.get(0).toString()));
        serviceGame.refresh(player.get().getId());
        List<Words> words = serviceWord.findAll();
        Random random = new Random();
        Game game = serviceGame.save(new Game(words.get(random.nextInt(words.size())).getWord(), player.get().getId()));
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable Integer id, @Valid @RequestBody String letter) {
        if (!serviceGame.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(serviceGame.check(id,letter));
    }

    public String getUserName(String authHeader){
        String _username=null;
        String _password = null;
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            _username = credentials.substring(0, p).trim();
                            _password = credentials.substring(p + 1).trim(); } }
                    catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
    }}}
    return _username;
    }




}
