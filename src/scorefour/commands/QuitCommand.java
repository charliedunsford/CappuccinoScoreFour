package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class QuitCommand implements Command {
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("quit.");

    }

    @Override
    public void execute(Game game) {
        System.out.print("Quitting game.");
        game.stopGame();
    }

    @Override
    public String getHelp() {
        return "quit.                                       exits the program";
    }
}
