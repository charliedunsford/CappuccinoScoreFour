package scorefour.controller;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * A {@link AudioController} handles all audio of the game.
 */
public class AudioController {

    /**
     * The index of the menu song.
     */
    public static final int MENU = 0;

    /**
     * The index of the game song.
     */
    public static final int GAME = 1;

    /**
     * The index of the button hover effect.
     */
    public static final int BUTTON_HOVER = 0;

    /**
     * The index of the peg hover effect.
     */
    public static final int PEG_HOVER = 1;

    /**
     * The index of the bead falling effect.
     */
    public static final int BEAD_FALLING = 2;

    private Clip[] songs;
    private String[] effectNames;
    private int currentSong;

    /**
     * A constructor which creates a new {@link AudioController} object.
     * <p>
     * Initializes all songs and effects, which are stored as clips.
     */
    public AudioController() {
        loadSongs();
        loadEffects();
    }

    // Loads all the music for the game.
    private void loadSongs() {
        String[] names = {"menu", "game"};
        songs = new Clip[names.length];
        for (int i = 0; i < songs.length; i++) {
            songs[i] = getClip(names[i]);
        }
    }

    // Loads a list of effect names for the game.
    private void loadEffects() {
        effectNames = new String[]{"button_hover", "peg_hover", "bead_falling"};
    }

    // Gets all audio as a clip which can be manipulated easier.
    private Clip getClip(String name) {
        try {
            InputStream audioPath = Objects.requireNonNull(getClass().getResourceAsStream("/res/audio/" + name + ".wav"));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audioPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Failed to load game audio.");
        }
    }

    /**
     * Plays the song of the provided index.
     *
     * @param song integer or static value of song
     */
    public void playSong(int song) {
        stopSong();

        currentSong = song;
        songs[currentSong].setMicrosecondPosition(0);
        songs[currentSong].loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the currently active {@link AudioController}'s song
     */
    public void stopSong() {
        if (songs[currentSong].isActive()) {
            songs[currentSong].stop();
        }
    }

    /**
     * Plays the effect of the provided index.
     *
     * @param effect integer or static value of effect
     */
    public void playEffect(int effect) {
        try {
            Clip clip = getClip(effectNames[effect]);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load effect clip.");
        }
    }
}
