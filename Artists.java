package com.jap.jukebox.model;

import com.jap.jukebox.dao.ArtistInterface;

import java.util.List;

public class Artists{
    private int ArtistID;
    private String Name;
    private List<Albums> album;



    public void setArtistID(int artistID) {
        ArtistID = artistID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName(){
        return Name;
    }

    public int getArtistID() {
        return ArtistID;
    }

    public List<Albums> getAlbum(){
        return album;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "ArtistID=" + ArtistID +
                ", Name='" + Name + '\'' +
                '}';
    }
}
