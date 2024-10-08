package com.jap.jukebox.utility;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SongPlayer {
    private Clip audioClip;

    // Load the audio file
    public File loadSong(String songFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Get the resources directory path
        Path resourceDirectory = Paths.get("src", "main", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        // Create the full path to the song
        File songFile1 = new File(absolutePath + File.separator + songFile);
        System.out.println("Song Path : " + songFile1);
        if (!songFile1.exists()) {
            System.out.println("Song file not found: " + songFile);
            return null; } // Play the song
        return songFile1;
    }

        // Play the song7

    public void playSong(File songFilePath) {
        Scanner sc = new Scanner(System.in);
        try {
            // Load and prepare the song for playback
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFilePath);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            audioClip.start();
            System.out.println("Playing: " + songFilePath.getName());

            boolean playMenuActive = true;

            while (playMenuActive) {
                // Display the playback control menu
                System.out.println("\n1. Stop Song");
                System.out.println("2. Rewind Song");
                System.out.println("3. Loop Song");
                System.out.println("4. Quit");
                System.out.print("Choose an option: ");
                int controlChoice = sc.nextInt();
                sc.nextLine();  // Consume newline

                switch (controlChoice) {
                    case 1:
                        stopSong();
                        System.out.println("Song stopped.");
                        playMenuActive = false;  // Exit the menu loop
                        break;

                    case 2:
                        rewindSong();
                        System.out.println("Song rewound to the beginning.");
                        break;

                    case 3:
                        System.out.print("Enter number of loops: ");
                        int loops = sc.nextInt();
                        loopSong(loops);
                        System.out.println("Song will loop " + loops + " times.");
                        break;

                    case 4:
                        System.out.println("Exiting playback menu...");
                        playMenuActive = false;
                        stopSong();  // Stop the song if the user quits
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop the song
    public void stopSong() {
        if (audioClip != null) {
            audioClip.stop();
        }
    }


    public void loopSong(int times) {
        if (audioClip != null) {
            audioClip.loop(times);
        }
    }

    // Rewind the song to the beginning
    public void rewindSong() {
        if (audioClip != null) {
            audioClip.setMicrosecondPosition(0);
        }
    }

    // Get the current position of the song
    public long getSongPosition() {
        if (audioClip != null) {
            return audioClip.getMicrosecondPosition();
        }
        return 0;
    }

    // Close the clip and free system resources
    public void closePlayer() {
        if (audioClip != null) {
            audioClip.close();
        }
    }
}

