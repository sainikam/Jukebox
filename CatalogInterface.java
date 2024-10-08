package com.jap.jukebox.dao;

import com.jap.jukebox.model.Artists;
import com.jap.jukebox.model.Genres;
import com.jap.jukebox.model.Songs;

import java.util.List;

public interface CatalogInterface {
    List<Songs> searchByTitle(String title);
    List<Songs> searchByArtist(Artists artist);
    List<Songs> searchByGenre(Genres genres);
    List<Songs> listAllSongs();

}
