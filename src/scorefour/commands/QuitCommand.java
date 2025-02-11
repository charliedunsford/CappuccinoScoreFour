package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class QuitCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("quit.");

    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Quitting game.");
        gameState.stopGui();
        gameState.stop();
    }

    @Override
    public String getHelp() {
        return "quit.                                       exits the program";
    }
}
