package scorefour.commands;

import scorefour.common.BeadColour;
import scorefour.model.Game;
import scorefour.common.Command;

/**
 * The {@code AddCommand} allows the user to add a bead to a position, only if the move is valid.
 */
public class AddCommand implements Command {
    private BeadColour colour;
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

        // Checks if string starts with "add " and end with a period.
        if (!input.toLowerCase().startsWith("add ") || !input.endsWith(".")) {
            return false;
        }

        // Removes the period at the end of the string.
        input = input.substring(0, input.length() - 1);

        // Splits the string into parts and stores it in an array.
        String[] parts = input.split(" ");

        // Checks if the string is the right amount of parts
        if (parts.length != 5) {
            return false;
        }

        // Checks if string contains the proper command syntax.
        if (!parts[0].equalsIgnoreCase("add") ||
                !parts[2].equalsIgnoreCase("bead") ||
                !parts[3].equalsIgnoreCase("to")) {
            return false;
        }

        // Checks the string contains a valid colour and stores that colour into a variable.
        if (parts[1].equalsIgnoreCase("black")) {
            colour = BeadColour.BLACK;
        } else if (parts[1].equalsIgnoreCase("white")) {
            colour = BeadColour.WHITE;
        } else {
            System.err.println("Invalid Colour. Colours should be 'BLACK' or 'WHITE'.");
            return false;
        }

        // Stores the position
        position = parts[4];

        // Checks if the position contains a valid board position,
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
     * @param game the {@link Game} instance to interact with
     */
    @Override
    public void execute(Game game) {
        game.getPlaying().getBoardController().addBead(position, colour);
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "add [colour] bead to [position].            add a bead to a specified location";
    }
}
