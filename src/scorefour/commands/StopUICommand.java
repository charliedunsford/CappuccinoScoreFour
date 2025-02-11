package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class StopUICommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("stop gui.");

    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Stopping gui.");
        gameState.stopGui();
    }

    @Override
    public String getHelp() {
        return "stop gui.                                   hides the user interface";
    }
}
