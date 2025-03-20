package scorefour.gamemodes;

import scorefour.common.GameState;
import scorefour.controller.ProgramController;
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
     * @param programController a {@link ProgramController} instance to be used in this mode
     */
    @Override
    public void setup(ProgramController programController) {
        GameState.state = GameState.MENU;
        programController.getGameView().startGUI();
        programController.getMenu().getAudioController().playSong(AudioController.MENU);
    }
}
