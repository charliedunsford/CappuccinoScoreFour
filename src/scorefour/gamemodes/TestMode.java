package scorefour.gamemodes;

import scorefour.commands.*;
import scorefour.core.Game;
import scorefour.common.GameState;
import scorefour.common.Command;
import scorefour.common.GameMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static scorefour.common.GameState.PLAYING;

public class TestMode implements GameMode {

    private final Scanner scanner;
    private final List<Command> commands;

    private Game game;

    public TestMode() {
        this.scanner = new Scanner(System.in);
        this.commands = initializeCommands();
        GameState.state = PLAYING;
    }

    private List<Command> initializeCommands() {
        List<Command> commandList = new ArrayList<>();

        // Commands go here
        commandList.add(new ClearCommand());
        commandList.add(new QuitCommand());
        commandList.add(new AddCommand());
        commandList.add(new RemoveCommand());
        commandList.add(new GetMoveCommand());
        commandList.add(new ShowBoardCommand());
        commandList.add(new DrawBoardCommand());
        commandList.add(new StartGUICommand());
        commandList.add(new StopGUICommand());
        commandList.add(new DebugCommand());

        commandList.add(new HelpCommand(commandList));
        return commandList;
    }

    @Override
    public void setup(Game game) {
        this.game = game;
        startCommands();
    }

    private void startCommands() {
        System.out.println("TESTING MODE (type 'help.' for list of commands)");
        boolean running = true;
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            boolean match = false;

            if (input.equals("quit.")) {
                running = false;
            }
            for (Command command : commands) {
                if (command.parse(input)) {
                    command.execute(game);
                    match = true;
                    break;
                }
            }
            if (!match) {
                System.out.println("Unknown command. Type 'help.' for command list.");
            }
        }
    }
}
