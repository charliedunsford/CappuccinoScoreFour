package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

import java.util.List;

public class HelpCommand implements Command {
    private final List<Command> commands;

    public HelpCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("help.");

    }

    @Override
    public void execute(GameState gameState) {
        for (Command command : commands) {
            System.out.println(command.getHelp());
        }
    }

    @Override
    public String getHelp() {
        return "help.                                       display this help message";
    }
}
