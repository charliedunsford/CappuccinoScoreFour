package scorefour.controller;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class Audio {

    public static final int MENU = 0;
    public static final int GAME = 1;

    public static final int MENU_HOVER = 0;

    private Clip[] songs, effects;
    private int currentSong, currentEffect;

    public Audio() {
        loadSongs();
        loadEffects();
    }

    public void loadSongs() {
        String[] names = {"menu", "playing_nolead"}; // remove no lead for more lead :D
        songs = new Clip[names.length];
        for (int i = 0; i < songs.length; i++) {
            songs[i] = getClip(names[i]);
        }
    }

    public void loadEffects() {
        String[] names = {"menu_hover"};
        effects = new Clip[names.length];
        for (int i = 0; i < effects.length; i++) {
            effects[i] = getClip(names[i]);
        }
    }

    public Clip getClip(String name) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/res/audio/" + name + ".wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Failed to load game audio.");
        }
    }

    public void playSong(int song) {
        stopSong();

        currentSong = song;
        songs[currentSong].setMicrosecondPosition(0);
        songs[currentSong].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSong() {
        if (songs[currentSong].isActive()) {
            songs[currentSong].stop();
        }
    }

    public void playEffect(int effect) {
        stopEffect();

        currentEffect = effect;
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    public void stopEffect() {
        if (effects[currentEffect].isActive()) {
            effects[currentEffect].stop();
        }
    }
}
