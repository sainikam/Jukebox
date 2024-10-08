package com.jap.jukebox.dao;

import com.jap.jukebox.model.Songs;

import java.sql.Time;
import java.util.List;

public interface SongInterface {

    List<Songs> getAllSongs();
    void addSong(Songs song);
    void updateSong(Songs song);
    void deleteSong(int songID);
    Songs getSongById(int songID);

}
