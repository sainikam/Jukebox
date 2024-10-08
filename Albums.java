package com.jap.jukebox.model;

import com.jap.jukebox.dao.AlbumInterface;

import java.util.ArrayList;
import java.util.List;

public class Albums implements AlbumInterface {
    private int AlbumID;
    private String AlbumName;
    private int ArtistID;
    private List<Songs> song;

    @Override
    public List<Songs> getSong(){
        return song;
    }

    @Override
    public void addSong(Songs songs){
        song.add(songs);
        System.out.println("Song added to Album " + this.AlbumName + " Successfully");
    }

    @Override
    public List<Songs> searchArtist(Artists artists){
        List<Songs> artistSongs = new ArrayList<>();
        for (Songs songs : song ){
            if (songs.getName().equals(artists.getName())){
                artistSongs.add(songs);
            }
        }
        return artistSongs;
    }
}
