package scorefour.commands;

import scorefour.controller.GameController;
import scorefour.common.Command;

/**
 * The {@code GetMoveCommand} allows the user to have the program propose a move.
 */
public class GetMoveCommand implements Command {

    private String colour;

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {

        // Checks that the string starts with "get " and ends with ".".
        if (!input.toLowerCase().startsWith("get ") || !input.endsWith(".")) {
            return false;
        }

        // Removes the period at the end of the string.
        input = input.substring(0, input.length() - 1);

        // Splits the string into an array of parts.
        String[] parts = input.split(" ");

        // Checks that the array contains the proper amount of strings.
        if (parts.length != 3) {
            return false;
        }

        // Checks that the array contains the proper command syntax
        if (!parts[0].equalsIgnoreCase("get") ||
                !parts[2].equalsIgnoreCase("move")) {
            return false;
        }

        colour = parts[1];
        return true;
    }

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param gameController the {@link GameController} instance to interact with
     */
    @Override
    public void execute(GameController gameController) {
        System.out.println("Getting " + colour + " move.");
        // Implement functionality here
        // game.getPlayingGame().getBoard().getMoveBead(colour); or computerPlayer.getMove()
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "get [colour] move.                          have the computer propose a move";
    }
}
