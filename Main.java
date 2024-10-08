package com.jap.jukebox;

import com.jap.jukebox.daoImpl.SongImplementation;
import com.jap.jukebox.daoImpl.PlaylistImplementation;
import com.jap.jukebox.daoImpl.UserService;
import com.jap.jukebox.model.Playlist;
import com.jap.jukebox.model.Songs;
import com.jap.jukebox.utility.SongPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Creating objects for service/implementation classes
        UserService userService = new UserService();
        SongImplementation songImplementation = new SongImplementation();
        PlaylistImplementation playlistImplementation = new PlaylistImplementation();
        SongPlayer songPlayer = new SongPlayer();

        // Scanner object to take input
        Scanner sc = new Scanner(System.in);

        // Login System
        System.out.println("---- Welcome to Jukebox System ----");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        boolean loggedIn = userService.authenticateUser(username, password);

        if (loggedIn) {
            System.out.println("Login successful! Welcome, " + username);

            // Displaying menu options
            boolean exit = false;
            while (!exit) {
                System.out.println("\n----- Main Menu -----");
                System.out.println("1. Add Song");
                System.out.println("2. Remove Song");
                System.out.println("3. View All Songs");
                System.out.println("4. Add Playlist");
                System.out.println("5. View Playlists");
                System.out.println("6. Remove Playlist");
                System.out.println("7. Logout");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // To consume newline character

                switch (choice) {
                    case 1:
                        // Add Song
                        System.out.print("Enter Song Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Artist Name: ");
                        String artist = sc.nextLine();
                        System.out.print("Enter Album Name: ");
                        String album = sc.nextLine();
                        System.out.print("Enter Genre: ");
                        String genre = sc.nextLine();
                        System.out.print("Enter Duration (MM:SS): ");
                        String duration = sc.nextLine();
                        duration = "00:" + duration;
                        LocalTime time = LocalTime.parse(duration, DateTimeFormatter.ofPattern("HH:mm:ss"));

                        Songs userSong = new Songs();
                        userSong.setSongName(title);
                        userSong.setArtistName(artist);
                        userSong.setAlbumName(album);
                        userSong.setGenreName(genre);
                        userSong.setDuration(time);

                        songImplementation.addSong(userSong);
                        break;

                    case 2:
                        // Remove Song
                        System.out.print("Enter Song ID to remove: ");
                        int songId = sc.nextInt();
                        songImplementation.deleteSong(songId);
                        break;

                    case 3:
                        // View All Songs
                        List<Songs> allSongs = songImplementation.getAllSongs();
                        for (Songs song : allSongs) {
                            System.out.println(song.toString());
                        }
                        break;

                    case 4:
                        // Add Playlist
                        System.out.print("Enter Playlist Name: ");
                        String playlistName = sc.nextLine();
                        System.out.print("Enter UserID: ");
                        int userId = sc.nextInt();
                        sc.nextLine();
                        Playlist playlist = new Playlist();
                        playlist.setName(playlistName);
                        playlist.setUserID(userId);
                        playlistImplementation.createPlaylist(playlist);
                        break;

                    case 5:
                        // View Playlists
                        List<Playlist> allPlaylists = playlistImplementation.getAllPlaylists();
                        for (Playlist playlist1 : allPlaylists) {
                            System.out.println(playlist1.toString());
                        }

                        System.out.println("Do you wish to add or play songs in a playlist (y/n)? : ");
                        String userInput = sc.nextLine();
                        if (userInput.equals("y")) {
                            System.out.println("Enter the playlist name in which you want to proceed:");
                            String playlistNameU = sc.nextLine();

                            for (Playlist playlist1 : allPlaylists) {
                                if (playlistNameU.equals(playlist1.getPlaylistName())){
                                    System.out.println("Enter your choice:");
                                    System.out.println("1.Add Song");
                                    System.out.println("2.Play Song");
                                    int userValue = sc.nextInt();

                                    if (userValue == 1) {

                                        System.out.println("Available songs:");
                                        List<Songs> allSongsI = songImplementation.getAllSongs();
                                        for (Songs song : allSongsI) {
                                            System.out.println("Song: " + song.getSongName() + " | Artist: " + song.getName());
                                        }


                                        System.out.println("Enter the name of the song you wish to add to this playlist: ");
                                        String songNameU = sc.next();


                                        int songId1 = songImplementation.getSongIdByName(songNameU);
                                        if (songId1 != -1) {
                                            songImplementation.addSongToPlaylist(playlist1.getPlaylistID(), songId1);
                                            System.out.println("Song '" + songNameU + "' added to playlist successfully.");
                                        } else {
                                            System.out.println("Song not found!");
                                        }
                                    } else if (userValue == 2) {
                                        System.out.println("Available Songs:");
                                        List<Songs> availableSongs = songImplementation.getAllSongs();
                                        for (Songs song : availableSongs) {
                                            System.out.println(song.getSongID() + ": " + song.getSongName());
                                        }

                                        System.out.println("Enter the song id you wish to play:");
                                        int songid = sc.nextInt();
                                        sc.nextLine(); // Consume the newline character

                                        try {
                                            String songFilePath = songImplementation.getSongFilePathById(songid);
                                            System.out.println(songFilePath);
                                            File songFile = songPlayer.loadSong(songFilePath);
                                            if (songFile != null) {
                                                songPlayer.playSong(songFile);

                                            } else {
                                                System.out.println("Song not found!");
                                                break;
                                            }
                                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                            System.out.println("Error retrieving song: " + e.getMessage());
                                        }
                                    }
                                }
                            }


                        }
                        break;

                    case 6:
                        // Remove Playlist
                        System.out.print("Enter Playlist ID to remove: ");
                        int playlistId = sc.nextInt();
                        playlistImplementation.deletePlaylist(playlistId);
                        System.out.println("Playlist removed successfully!");
                        break;

                    case 7:
                        // Logout
                        exit = true;
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
        } else {
            System.out.println("Login failed! Please check your username and password.");
        }

        sc.close();
    }
}
