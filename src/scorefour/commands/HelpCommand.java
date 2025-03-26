package scorefour.commands;

import scorefour.controller.ProgramController;
import scorefour.common.Command;

import java.util.List;

/**
 * The {@code HelpCommand} lists all available {@link Command}'s and their descriptions.
 */
public class HelpCommand implements Command {
    private final List<Command> commands;

    /**
     * Gives {@link HelpCommand} access to a list of all available {@link Command}'s.
     *
     * @param commands a list of {@link Command}'s
     */
    public HelpCommand(List<Command> commands) {
        this.commands = commands;
    }

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("help.");
    }

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param programController the {@link ProgramController} instance to interact with
     */
    @Override
    public void execute(ProgramController programController) {
        for (Command command : commands) {
            System.out.println(command.getHelp());
        }
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "help.                                       display this help message";
    }
}
