package com.example.demo.API.Controllers;

import com.example.demo.API.Entities.Player;
import com.example.demo.API.Entities.Users;
import com.example.demo.API.Services.ServicePlayer;
import com.example.demo.API.Services.ServiceUser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/api/login")
public class ControllerLogin {
    @Autowired
    ServiceUser serviceUser;
    @Autowired
    EntityManager entityManager;

    @GetMapping
    public String findById(HttpServletRequest req)
    {
        String username =  getUserName(req.getHeader("Authorization"));
        Query query = entityManager.createNativeQuery("SELECT id FROM users WHERE username = :username");
        query.setParameter("username", username);
        List<Object> rows =query.getResultList();
        Optional<Users> users = serviceUser.findById(Integer.parseInt(rows.get(0).toString()));
        return users.get().getUserType().toString();
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
