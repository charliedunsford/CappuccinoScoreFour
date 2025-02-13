package scorefour.gamemodes;

import scorefour.core.Game;
import scorefour.common.GameMode;

public class NormalMode implements GameMode {

    @Override
    public void setup(Game game) {
        game.startGUI();
    }
}
