package scorefour.gamemodes;

import scorefour.core.Game;
import scorefour.common.GameMode;
import scorefour.controller.AudioController;

public class NormalMode implements GameMode {
    @Override
    public void setup(Game game) {
        game.startGUI();
        game.getAudio().playSong(AudioController.MENU);
    }
}
