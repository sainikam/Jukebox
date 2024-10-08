package com.jap.jukebox.dao;

import com.jap.jukebox.model.Playlist;
import com.jap.jukebox.model.Songs;

import java.util.List;

public interface PlaylistInterface {
    List<Playlist> getAllPlaylists();
    List<Playlist> getPlaylistsByUser(int userID);
    void updatePlaylist(Playlist playlist);
    void deletePlaylist(int playlistID);
}
