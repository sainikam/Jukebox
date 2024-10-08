package com.jap.jukebox.model;

import com.jap.jukebox.dao.UserInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {
    private int userID;
    private String username;
    private String password;
    private Date CreatedDate;

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }



    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
