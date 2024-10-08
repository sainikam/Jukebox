package com.jap.jukebox.dao;

import com.jap.jukebox.model.Artists;
import com.jap.jukebox.model.Songs;

import java.util.List;

public interface AlbumInterface {
    List<Songs> getSong();
    void addSong(Songs song);
    List<Songs> searchArtist(Artists artists);
}
