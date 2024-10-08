package com.jap.jukebox.model;

import com.jap.jukebox.dao.GenreInterface;

import java.util.List;

public class Genres implements GenreInterface {
    private int GenreID;
    private String GenreName;
    private List<Songs> songs;

    @Override
    public String getName(){
        return GenreName;
    }

    @Override
    public List<Songs> getSongs(){
        return songs;
    }
}
