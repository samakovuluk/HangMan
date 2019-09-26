package com.example.demo.API.Entities;

import com.example.demo.API.Enum.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;


import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Users")
public class Users  {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Integer id;
    @Column(name = "username", unique = true)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "userType")
    private UserType userType;

    public Users(){

    }
    public Users(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public Users(Integer id,String username, String password, UserType userType) {
        this.id =id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void encryptPassword(){
        byte[] byteArray = new byte[16];
        new Random(System.currentTimeMillis()).nextBytes(byteArray);
        this.password = OpenBSDBCrypt.generate(this.password.toCharArray(), byteArray, 11);
    }

}
