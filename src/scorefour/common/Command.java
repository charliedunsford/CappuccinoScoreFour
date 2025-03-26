package scorefour.common;

import scorefour.controller.ProgramController;

/**
 * A {@code Command} is a way the user can interact with the program using string in
 * a command line.
 */
public interface Command {

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    boolean parse(String input);

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param programController the {@link ProgramController} instance to interact with
     */
    void execute(ProgramController programController);

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    String getHelp();
}