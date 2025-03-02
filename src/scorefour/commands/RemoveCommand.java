package scorefour.commands;

import scorefour.controller.GameController;
import scorefour.common.Command;

/**
 * The {@code RemoveCommand} allows the user to remove a bead from a specified location, if it exists.
 */
public class RemoveCommand implements Command {

    private String position;

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {

        // Checks that the string starts with "remove " and ends with ".".
        if (!input.toLowerCase().startsWith("remove ") || !input.endsWith(".")) {
            return false;
        }

        // Removes the period at the end of the string.
        input = input.substring(0, input.length() - 1);

        // Splits the string into parts and stores it in an array.
        String[] parts = input.split(" ");

        // Checks that the array contains the proper amount of strings.
        if (parts.length != 4) {
            return false;
        }

        // Checks the syntax of the array.
        if (!parts[0].equalsIgnoreCase("remove") ||
                !parts[1].equalsIgnoreCase("bead") ||
                !parts[2].equalsIgnoreCase("from")) {
            return false;
        }

        position = parts[3];

        // Checks that the position is valid.
        if (!position.matches("[A-D][1-4]")) {
            System.err.println("Invalid position. Position should be a row A-D followed by a column 1-4.");
            return false;
        }

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
        gameController.getPlaying().getBoardController().removeBead(position);
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "remove bead from [position].                remove a bead from a specified location";
    }
}
