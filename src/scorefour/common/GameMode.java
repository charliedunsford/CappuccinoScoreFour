package scorefour.common;

import scorefour.controller.ProgramController;

/**
 * A {@code GameMode} allows a program to be run in various ways.
 */
public interface GameMode {

    /**
     * Initializes the {@code GameMode} setup and starts the game loop.
     *
     * @param programController the {@code Game} instance to be used in this mode
     */
    void setup(ProgramController programController);

}