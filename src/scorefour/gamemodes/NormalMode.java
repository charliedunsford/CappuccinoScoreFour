package scorefour.gamemodes;

import scorefour.common.GameState;
import scorefour.model.Game;
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
     * @param game a {@link Game} instance to be used in this mode
     */
    @Override
    public void setup(Game game) {
        GameState.state = GameState.MENU;
        game.startGUI();
        game.getMenu().getAudioController().playSong(AudioController.MENU);
    }
}
