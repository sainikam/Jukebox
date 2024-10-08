package com.jap.jukebox.daoImpl;

import com.jap.jukebox.dao.CatalogInterface;
import com.jap.jukebox.dao.SongInterface;
import com.jap.jukebox.model.Songs;
import com.jap.jukebox.utility.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SongImplementation implements SongInterface {

    public void addSongToPlaylist(int playlistID, int songID) throws SQLException {
        String query = "INSERT INTO Playlists_ (playlistName, songID) VALUES (?, ?)";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            stmt.setInt(2, songID);
            stmt.executeUpdate();
        }
    }

    public int getSongIdByName(String songName) throws SQLException {
        // Trim song name to avoid issues with extra spaces
        songName = songName.trim();

        // Establish database connection
        Connection connection = ConnectDatabase.getConnection();

        // SQL query to get song ID by song name
        String query = "SELECT songID FROM SongsIm WHERE songName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Set the song name in the query
        preparedStatement.setString(1, songName);

        // Execute the query
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if any results were returned
        if (resultSet.next()) {
            // Return the songID if a match is found
            return resultSet.getInt("songID");
        } else {
            // No matching song found, return -1
            System.out.println("No song found with name: " + songName);
            return -1;
        }
    }





    // Method to get all songs in the catalog
    @Override
    public List<Songs> getAllSongs() {
        List<Songs> songList = new ArrayList<>();
        String query = "SELECT * FROM SongsIm";

        try (Connection connection = ConnectDatabase.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Songs song = new Songs();
                song.setSongID(rs.getInt("SongID"));
                song.setSongName(rs.getString("SongName"));
                java.sql.Time sqlTime = rs.getTime("Duration");
                song.setArtistName(rs.getString("Name"));
                song.setAlbumName(rs.getString("AlbumName"));
                song.setGenreName(rs.getString("GenreName"));
                song.setSongFilePath(rs.getString("songFilePath"));
                songList.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songList;
    }

    // Method to add a new song to the catalog
    @Override
    public void addSong(Songs song) {
        String query = "INSERT INTO SongsIm (SongID ,SongName, Duration, Name, AlbumName, GenreName , songFilePath) VALUES (?,?, ?, ?, ?,?, ?)";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, song.getSongID());
            stmt.setString(2, song.getSongName());
            java.sql.Time sqlTime = java.sql.Time.valueOf(song.getDuration());
            stmt.setTime(3, sqlTime);
            stmt.setString(4, song.getName());
            stmt.setString(5, song.getAlbumName());
            stmt.setString(6, song.getGenreName());
            stmt.setString(7, song.getSongFilePath());

            stmt.executeUpdate();

            System.out.println("Song added successfully to the catalog.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing song in the catalog
    @Override
    public void updateSong(Songs song) {
        String query = "UPDATE SongsIm SET Name = ?, Duration = ?, ArtistName = ?, AlbumName = ?, GenreName = ? , songFilePath = ?  WHERE SongID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, song.getSongName());
            java.sql.Time sqlTime = java.sql.Time.valueOf(song.getDuration());
            stmt.setTime(2, sqlTime);
            stmt.setString(3, song.getName());
            stmt.setString(4, song.getAlbumName());
            stmt.setString(5, song.getGenreName());
            stmt.setString(6, song.getSongFilePath());
            stmt.setInt(7, song.getSongID());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Song with ID " + song.getSongID() + " has been updated.");
            } else {
                System.out.println("No song found with ID " + song.getSongID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a song from the catalog
    @Override
    public void deleteSong(int songID) {
        String query = "DELETE FROM SongsIm WHERE SongID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, songID);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Song with ID " + songID + " has been deleted from the catalog.");
            } else {
                System.out.println("No song found with ID " + songID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSongFilePathById(int songID) throws SQLException {
        String query = "SELECT songFilePath FROM SongsIm WHERE SongID = ?";
        String songFilePath = null;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, songID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                songFilePath = rs.getString("songFilePath");
            }
        }

        return songFilePath;
    }


    // Method to get a song by its ID
    @Override
    public Songs getSongById(int songID) {
        String query = "SELECT * FROM SongsIm WHERE SongID = ?";
        Songs song = null;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, songID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                song = new Songs();
                song.setSongID(rs.getInt("SongID"));
                song.setSongName(rs.getString("SongName"));
                java.sql.Time sqlTime = java.sql.Time.valueOf(song.getDuration());
                stmt.setTime(3, sqlTime);
                song.setDuration(rs.getTime("Duration").toLocalTime());
                song.setArtistName(rs.getString("Name"));
                song.setAlbumName(rs.getString("AlbumName"));
                song.setGenreName(rs.getString("GenreName"));
                song.setSongFilePath(rs.getString("songFilePath"));
            } else {
                System.out.println("No song found with ID " + songID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return song;
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        SongImplementation catalogImpl = new SongImplementation();

        // Adding a new song
        Songs newSong = new Songs();
        newSong.setSongName("Imagine");
        newSong.setDuration(Time.valueOf("00:03:05").toLocalTime());
        newSong.setArtistName("John Lennon");
        newSong.setAlbumName("Imagine");
        newSong.setGenreName("Rock");
        catalogImpl.addSong(newSong);
        // Replace with a valid SongID to delete
        // Getting all songs
        List<Songs> songs = catalogImpl.getAllSongs();
        for (Songs song : songs) {
            System.out.println(song.getSongID() + ": " + song.getSongName() + " by " + song.getName());
        }

        // Updating a song
        //Songs songToUpdate = catalogImpl.getSongById(1); // Replace with a valid SongID
        //if (songToUpdate != null) {
        //    songToUpdate.setSongName("Imagine (Remastered)");
        //    catalogImpl.updateSong(songToUpdate);
        //}

        // Deleting a song
        //catalogImpl.deleteSong(2); // Replace with a valid SongID to delete
    }
}
