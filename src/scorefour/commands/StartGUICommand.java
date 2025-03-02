package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;
import scorefour.controller.AudioController;

/**
 * The {@code StartGUICommand} allows the user to start a GUI instance, if one doesn't already exist.
 */
public class StartGUICommand implements Command {

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("start gui.");

    }

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param game the {@link Game} instance to interact with
     */
    @Override
    public void execute(Game game) {
        System.out.println("Starting gui.");
        game.startGUI();
        game.getPlaying().getAudioController().playSong(AudioController.GAME);
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "start gui.                                  displays the user interface";
    }
}
