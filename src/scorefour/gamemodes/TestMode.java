package scorefour.gamemodes;

import scorefour.commands.*;
import scorefour.core.GameState;
import scorefour.interfaces.Command;
import scorefour.interfaces.GameMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMode implements GameMode {

    private final GameState gameState;
    private final Scanner scanner;
    private final List<Command> commands;

    public TestMode() {
        this.scanner = new Scanner(System.in);
        this.gameState = new GameState();
        this.commands = initializeCommands();
    }

    private List<Command> initializeCommands() {
        List<Command> commandList = new ArrayList<>();

        // All available commands
        commandList.add(new ClearCommand());
        commandList.add(new QuitCommand());
        commandList.add(new AddCommand());
        commandList.add(new RemoveCommand());
        commandList.add(new GetMoveCommand());
        commandList.add(new ShowBoardCommand());
        commandList.add(new DrawBoardCommand());
        commandList.add(new StartUICommand());
        commandList.add(new StopUICommand());
        commandList.add(new DebugCommand());

        commandList.add(new HelpCommand(commandList));
        return commandList;
    }

    @Override
    public void setup() {
        gameState.start();
        startCommands();
    }

    // Test commands
    public void startCommands() {
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
                    command.execute(gameState);
                    match = true;
                    break;
                }
            }
            if (!match) {
                System.out.println("Unknown command. Type 'help.' for command list.");
            }
            //Temporary thread tracker
            // System.out.println(Thread.activeCount());
        }
    }
}