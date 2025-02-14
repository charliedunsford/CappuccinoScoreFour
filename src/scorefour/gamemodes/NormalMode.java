package scorefour.gamemodes;

import scorefour.core.Game;
import scorefour.common.GameMode;
import scorefour.ui.AudioPlayer;

public class NormalMode implements GameMode {
    @Override
    public void setup(Game game) {
        game.startGUI();
        game.getAudioPlayer().playSong(AudioPlayer.MENU);
    }
}
