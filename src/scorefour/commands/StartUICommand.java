package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class StartUICommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("start gui.");

    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Starting gui.");
        gameState.startGui();
    }

    @Override
    public String getHelp() {
        return "start gui.                                  displays the user interface";
    }
}
