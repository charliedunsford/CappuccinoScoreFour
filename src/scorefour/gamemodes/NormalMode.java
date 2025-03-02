package scorefour.gamemodes;

import scorefour.common.GameState;
import scorefour.controller.GameController;
import scorefour.common.GameMode;
import scorefour.controller.AudioController;

/**
 * {@code NormalMode} is a visually interactive {@link GameMode}.
 * <p>
 * The initial state of {@code NormalMode} is defined in the {@code setup} method.
 */
public class NormalMode implements GameMode {

    /**
     * Initializes the game in {@link NormalMode} and starts the GUI and menu music.
     *
     * @param gameController a {@link GameController} instance to be used in this mode
     */
    @Override
    public void setup(GameController gameController) {
        GameState.state = GameState.MENU;
        gameController.getGameView().startGUI();
        gameController.getMenu().getAudioController().playSong(AudioController.MENU);
    }
}
