package com.jap.jukebox.dao;

import com.jap.jukebox.model.Playlist;
import com.jap.jukebox.model.Songs;
import com.jap.jukebox.model.User;

import java.util.List;

public interface UserInterface {

    void registerUser(String usernameOrEmail, String password);
    boolean authenticateUser(String usernameOrEmail, String password);
    User getUserByUsernameOrEmail(String username);
}
