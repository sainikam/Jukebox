package com.jap.jukebox.model;

import com.jap.jukebox.dao.CatalogInterface;

import java.util.ArrayList;
import java.util.List;

public class Catalog implements CatalogInterface{
    private List<Albums> albums;
    private List<Artists> artists;
    private List<Genres> genre;
    private List<Songs> song;

    @Override
    public List<Songs> searchByTitle(String title){
        List<Songs> neededSongs = new ArrayList<>();
        for (Songs sng : song){
            if (sng.getTitle().equals(title)){
                neededSongs.add(sng);
            }
        }
        return neededSongs;
    }

    @Override
    public List<Songs> searchByArtist(Artists artist){
        List<Songs> neededSongs = new ArrayList<>();
        for (Songs sng : song){
            if (sng.getName().equals(artist.getName())){
                neededSongs.add(sng);
            }
        }
        return neededSongs;
    }

    @Override
    public List<Songs> searchByGenre(Genres genres){
        List<Songs> neededSongs = new ArrayList<>();
        for (Songs sng : song){
            if (sng.getGenreName().equals(genres.getName())){
                neededSongs.add(sng);
            }
        }
        return neededSongs;
    }

    @Override
    public List<Songs> listAllSongs(){
        return song;
    }


}
