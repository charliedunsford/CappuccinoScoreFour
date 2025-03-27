package scorefour.commands;

import scorefour.common.BeadColour;
import scorefour.controller.ProgramController;
import scorefour.common.Command;
import scorefour.model.Board;
import scorefour.player.ComputerPlayer;

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
     * @param programController the {@link ProgramController} instance to interact with
     */
    @Override
    public void execute(ProgramController programController) {
        System.out.println("Getting " + colour + " move.");
        Board board = programController.getGameController().getBoardController().getBoard();
        if (colour.equalsIgnoreCase("white")) {
            ComputerPlayer computer = new ComputerPlayer(BeadColour.WHITE, board);
            board.addBead(computer.getMove(), BeadColour.WHITE);
        } else if (colour.equalsIgnoreCase("black")) {
            ComputerPlayer computer = new ComputerPlayer(BeadColour.BLACK, board);
            board.addBead(computer.getMove(), BeadColour.BLACK);
        }

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
