package scorefour.commands;

import scorefour.model.Game;
import scorefour.common.Command;

/**
 * The {@code StopGUICommand} allows the user to stop the current GUI instance, if one exists.
 */
public class StopGUICommand implements Command {

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("stop gui.");

    }

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param game the {@link Game} instance to interact with
     */
    @Override
    public void execute(Game game) {
        System.out.println("Stopping gui.");
        game.stopGUI();
        game.getPlaying().getAudioController().stopSong();
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "stop gui.                                   hides the user interface";
    }
}
