package com.example.demo.Entities;

import com.example.demo.Enum.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Users")
public class Users  {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @JsonIgnore
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


    public String getPassword() {
        return password;
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

}
