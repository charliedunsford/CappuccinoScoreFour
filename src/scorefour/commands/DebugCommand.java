package scorefour.commands;

import scorefour.controller.ProgramController;
import scorefour.common.Command;

/**
 * The {@code DebugCommand} displays various debug information to the console.
 */
public class DebugCommand implements Command {

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("debug.");
    }

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param programController the {@link ProgramController} instance to interact with
     */
    @Override
    public void execute(ProgramController programController) {
        System.out.println("Getting debug information.\n" +
                "Current FPS : " + programController.getDebugFPS() + "\n" +
                "Current Live Threads : " + Thread.activeCount());
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "debug.                                      display debug information";
    }
}
