package com.jap.jukebox.model;

import java.sql.Time;
import java.time.LocalTime;

public class Songs{
    // Attributes
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    private int songID;
    private String songName;
    private LocalTime duration;
    private String Name;
    private String album_name;
    private String genre_name;
    private String songFilePath;

    public String getSongFilePath() {
        return songFilePath;
    }

    public void setSongFilePath(String songFilePath) {
        this.songFilePath = songFilePath;
    }



    public void setArtistName(String Name) {
        this.Name = Name;
    }

    public void setAlbumName(String album_name) {
        this.album_name = album_name;
    }

    public void setGenreName(String genre_name) {
        this.genre_name = genre_name;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public int getSongID() {
        return songID;
    }

    public String getName() {
        return Name;
    }

    // Constructor
    public Songs() {
        this.songID = songID;
        this.songName = songName;
        this.duration = duration;
        this.Name = Name;
        this.album_name = album_name;
        this.genre_name = genre_name;
    }

    public String getTitle() {
        return songName;
    }


    public LocalTime getDuration() {
        return duration;
    }

    public String getAlbumName() {
        return album_name;
    }

    public String getSongName() {
        return songName;
    }


    public String getGenreName() {
        return genre_name;
    }
    public void play(){

    }

    @Override
    public String toString() {
        return "Songs{" +
                "filePath='" + filePath + '\'' +
                ", songID=" + songID +
                ", songName='" + songName + '\'' +
                ", duration=" + duration +
                ", Name='" + Name + '\'' +
                ", album_name='" + album_name + '\'' +
                ", genre_name='" + genre_name + '\'' +
                '}';
    }


}
