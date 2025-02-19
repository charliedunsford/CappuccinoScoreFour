package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class StopGUICommand implements Command {
    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("stop gui.");

    }

    @Override
    public void execute(Game game) {
        System.out.println("Stopping gui.");
        game.getAudioPlayer().stopSong();
        game.stopGUI();
    }

    @Override
    public String getHelp() {
        return "stop gui.                                   hides the user interface";
    }
}
