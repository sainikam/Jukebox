package com.jap.jukebox.model;

import com.jap.jukebox.dao.PlaylistInterface;

import java.util.List;

public class Playlist {
    private int playlistID;
    private int songID;

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    private String playlistName;
    private int userID;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public void setName(String playlistName) {
        this.playlistName = playlistName;
    }

    private List<Songs> playlistSongs;



    public int getPlaylistID() {
        return playlistID;
    }


    public String getName(){
        return playlistName;
    }


    public List<Songs> getSongs(){
        return playlistSongs;
    }


    public void addSong(Songs song){
        if (playlistSongs.contains(song)){
            System.out.println("Song Already Added");
        }
        playlistSongs.add(song);
        System.out.println("Song added Successfully");
    }


    public void removeSong(Songs song){
        if (playlistSongs.contains(song)){
            playlistSongs.remove(song);
            System.out.println("Song removed Successfully");
        }
        System.out.println("Playlist does not contain this song");
    }


    public void playSong(Songs song) {
        System.out.println("Now playing: " + song.getTitle() + " by " + song.getName() +
                " [Duration: " + song.getDuration() + "]");
    }


    public void playAllSongs() {
        if (playlistSongs.isEmpty()) {
            System.out.println("The playlist '" + playlistName + "' is empty.");
            return;
        }

        System.out.println("Playing all songs from playlist: " + playlistName);
        for (Songs song : playlistSongs) {
            playSong(song);
        }
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistID=" + playlistID +
                ", playlistName='" + playlistName + '\'' +
                '}';
    }
}
