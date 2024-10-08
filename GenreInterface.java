package com.jap.jukebox.dao;

import com.jap.jukebox.model.Songs;

import java.util.List;

public interface GenreInterface {
    String getName();
    List<Songs> getSongs();
}
