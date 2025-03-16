package scorefour.commands;

import scorefour.controller.GameController;
import scorefour.common.Command;

/**
 * The {@code QuitCommand} gracefully quits the program.
 */
public class QuitCommand implements Command {

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("quit.");
    }

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param gameController the {@link GameController} instance to interact with
     */
    @Override
    public void execute(GameController gameController) {
        System.out.print("Quitting game.");
        gameController.stopGame();
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "quit.                                       exits the program";
    }
}
