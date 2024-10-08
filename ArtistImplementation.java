package com.jap.jukebox.daoImpl;
import com.jap.jukebox.model.Artists;
import com.jap.jukebox.dao.ArtistInterface;
import com.jap.jukebox.utility.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistImplementation implements ArtistInterface {
    @Override
    public List<Artists> getAllArtists() {
        List<Artists> artists = new ArrayList<>();
        String query = "SELECT * FROM artist";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Artists artist = new Artists();
                artist.setArtistID(rs.getInt("ArtistID"));
                artist.setName(rs.getString("Name"));

                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public void addArtist(Artists artist) {
        String query = "INSERT INTO artist (ArtistID , Name) VALUES (?,?)";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, artist.getArtistID());
            stmt.setString(2, artist.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Artists getArtistById(int artistID) {
        String query = "SELECT * FROM Artists WHERE artistID = ?";
        Artists artist = null;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, artistID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                artist = new Artists();
                artist.setArtistID(rs.getInt("artistID"));
                artist.setName(rs.getString("ArtistName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artist;
    }


    @Override
    public void updateArtist(int artistId) {
        Artists artist = getArtistById(104);

        String query = "UPDATE artist SET Name = ? WHERE ArtistID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, artist.getArtistID());
            stmt.setString(2,artist.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArtist(int artistID) {
        String query = "DELETE FROM artists WHERE ArtistID = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, artistID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        ArtistImplementation artimpl = new ArtistImplementation();
        //List<Artists> artistList = artimpl.getAllArtists();

        Artists art = new Artists();
        art.setArtistID(104);
        art.setName("Rihanna");
        //artimpl.addArtist(art);
        //artimpl.updateArtist(104);

        artimpl.deleteArtist(101);
        List<Artists> artistList = artimpl.getAllArtists();
        System.out.println(artistList);


    }







}

