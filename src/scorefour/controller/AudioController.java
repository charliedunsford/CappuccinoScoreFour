package scorefour.controller;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

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
     * The index of the menu hover effect.
     */
    public static final int OPTION_HOVER = 0;

    /**
     * The index of the peg hover effect.
     */
    public static final int PEG_HOVER = 1;

    /**
     * The index of the bead falling effect.
     */
    public static final int FALLING = 2;

    private Clip[] songs, effects;
    private int currentSong, currentEffect;

    /**
     * A constructor which creates a new {@code AudioController} object.
     * <p>
     * Initializes all songs and effects, which are stored as clips.
     */
    public AudioController() {
        loadSongs();
        loadEffects();
    }

    private void loadSongs() {
        String[] names = {"menu", "playing_nolead"}; // remove no lead for more lead :D
        songs = new Clip[names.length];
        for (int i = 0; i < songs.length; i++) {
            songs[i] = getClip(names[i]);
        }
    }

    private void loadEffects() {
        String[] names = {"menu_hover", "peg_hover", "falling"};
        effects = new Clip[names.length];
        for (int i = 0; i < effects.length; i++) {
            effects[i] = getClip(names[i]);
        }
    }

    private Clip getClip(String name) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/res/audio/" + name + ".wav")));
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
        stopEffect();

        currentEffect = effect;
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    /**
     * Stops the currently active {@link AudioController}'s effect
     */
    public void stopEffect() {
        if (effects[currentEffect].isActive()) {
            effects[currentEffect].stop();
        }
    }
}
