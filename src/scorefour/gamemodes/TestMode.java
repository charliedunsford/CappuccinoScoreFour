package scorefour.gamemodes;

import scorefour.commands.*;
import scorefour.common.GameState;
import scorefour.controller.ProgramController;
import scorefour.common.Command;
import scorefour.common.GameMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static scorefour.common.GameState.GAME;

/**
 * {@link TestMode} is a {@link GameMode} which allows the user to control the program through commands.
 * <p>
 * The initial state of {@link TestMode} is defined in the {@code setup} method.
 */
public class TestMode implements GameMode {

    private final Scanner scanner;
    private final List<Command> commands;

    private ProgramController programController;

    /**
     * Constructs a new {@link TestMode} object which initializes the commands to be used during the programs runtime and
     * the scanner to accept user input.
     */
    public TestMode() {
        this.scanner = new Scanner(System.in);
        this.commands = initializeCommands();
    }

    // Makes a list of all the commands.
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

    /**
     * Initializes the game in {@link TestMode} and starts the command loop.
     * <p>
     * This mode sets the {@link GameState} to {@code GAME}.
     *
     * @param programController a {@link ProgramController} instance to be used in this mode
     */
    @Override
    public void setup(ProgramController programController) {
        GameState.state = GAME;
        this.programController = programController;
        startCommands();
    }

    // Starts the command line for the user to interact with.
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
                    command.execute(programController);
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
