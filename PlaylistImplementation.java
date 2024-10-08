package com.jap.jukebox.daoImpl;

import com.jap.jukebox.dao.PlaylistInterface;
import com.jap.jukebox.model.Playlist;
import com.jap.jukebox.model.Songs;
import com.jap.jukebox.utility.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistImplementation implements PlaylistInterface {
    public void createPlaylist(Playlist playlist) throws SQLException {
        String query = "INSERT INTO Playlists_ (playlistName, userID) VALUES (?, ?)";


        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            // Setting the values in the prepared statement
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, playlist.getUserID());

            // Executing the query
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Playlist created successfully!");
            } else {
                System.out.println("Failed to create playlist.");
            }
        } catch (SQLException e) {
            System.out.println("Error while creating playlist: " + e.getMessage());
            throw e;
        }
    }
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlistList = new ArrayList<>();
        String query = "SELECT * FROM Playlists_";
        try (Connection connection = ConnectDatabase.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setPlaylistID(rs.getInt("PlaylistID"));
                playlist.setName(rs.getString("PlaylistName"));
                playlistList.add(playlist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlistList;
    }

    @Override
    public List<Playlist> getPlaylistsByUser(int userID) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlists_ WHERE userID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Playlist playlist = new Playlist();
                    playlist.setPlaylistID(rs.getInt("PlaylistID"));
                    playlist.setName(rs.getString("Name"));
                    playlist.setUserID(rs.getInt("UserID"));
                    playlists.add(playlist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        String query = "UPDATE Playlists_ SET Name = ? WHERE PlaylistID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, playlist.getName());
            stmt.setInt(2, playlist.getPlaylistID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylist(int playlistID) {
        String query = "DELETE FROM Playlists_ WHERE PlaylistID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, playlistID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
