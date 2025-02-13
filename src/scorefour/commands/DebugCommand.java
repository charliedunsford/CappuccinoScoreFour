package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class DebugCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("debug.");

    }

    @Override
    public void execute(Game game) {
        System.out.println("Getting debug information.\n" +
                "Current FPS : " + game.getDebugFPS() + "\n" +
                "Current Live Threads : " + Thread.activeCount());
    }

    @Override
    public String getHelp() {
        return "debug.                                      display debug information";
    }
}
