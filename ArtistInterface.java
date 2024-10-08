package com.jap.jukebox.dao;

import com.jap.jukebox.model.Artists;

import java.util.List;

public interface ArtistInterface {
    List<Artists> getAllArtists();
    void addArtist(Artists artist);
    void updateArtist(int artistId);
    void deleteArtist(int artistID);
    Artists getArtistById(int artistID);
}
