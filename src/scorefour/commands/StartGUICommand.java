package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class StartGUICommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("start gui.");

    }

    @Override
    public void execute(Game game) {
        System.out.println("Starting gui.");
        game.startGUI();
    }

    @Override
    public String getHelp() {
        return "start gui.                                  displays the user interface";
    }
}
