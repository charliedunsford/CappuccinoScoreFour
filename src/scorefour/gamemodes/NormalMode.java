package scorefour.gamemodes;

import scorefour.common.GameState;
import scorefour.controller.ProgramController;
import scorefour.common.GameMode;
import scorefour.controller.AudioController;

/**
 * {@link NormalMode} is a visually interactive {@link GameMode}.
 * <p>
 * The initial state of {@link NormalMode} is defined in the {@code setup} method.
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
        programController.getProgramView().startGUI();
        programController.getMenuController().getAudioController().playSong(AudioController.MENU);
    }
}
