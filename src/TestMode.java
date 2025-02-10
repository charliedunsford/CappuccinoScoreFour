import javax.swing.*;
import java.util.Scanner;

public class TestMode implements GameMode {

    private JFrame frame;
    private GameState gameState;
    private Panel panel;
    private final Board board;
    private final Scanner scanner;

    public TestMode() {
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setup() {
        gameState = new GameState(board, null);
        gameState.start();
        startCommands();
    }

    // Test commands
    public void startCommands() {
        System.out.println("TESTING MODE (type help. for list of all commands)");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ");
            String command = parts[0];
            switch (command) {
                case "clear.": // UNFINISHED
                    //board.clearBoard();
                    System.out.println("TestClearBoard");
                    break;
                case "quit.":
                    gameState.stop();
                    return;
                case "add" : // This is missing color and position valid check. make move validator object class?
                    if (parts.length == 5 && parts[2].equals("bead") && parts[3].equals("to") && parts[4].endsWith(".")) {
                        String color = parts[1];
                        String position = parts[4];
                        System.out.println("Adding " + color + " bead to " + position);
                    } else {
                        System.out.println("add [colour] bead to [position].");
                    }
                    break;
                case "remove" : // UNFINISHED
                    if (parts.length == 4 && parts[1].equals("bead") && parts[2].equals("from") && parts[3].endsWith(".")) {
                        System.out.println("TestingRemoveBead");
                    } else {
                        System.out.println("remove bead from [position].");
                    }
                    break;
                case "get" : // UNFINISHED computer will return a proposed move does not add to board
                    if (parts.length == 3 && parts[2].equals("move.")) {
                        System.out.println("TestComputerPlayerMove");
                    } else {
                        System.out.println("get [colour] move.");
                    }
                    break;
                case "show" :
                    if (parts.length == 2 && parts[1].equals("board.")) {
                        System.out.println("TestConsoleBoardPrint");
                    }
                    break;
                case "go":
                    if (parts.length == 2 && parts[1].equals("gui.")) {
                        startGui();
                        gameState.setPanel(panel);
                    }
                    break;
                case "stop":
                    if (parts.length == 2 && parts[1].equals("gui.")) {
                        frame.dispose();
                        gameState.setPanel(null);
                    }
                    break;
                case "help." :
                    System.out.println("""
                            
                            clear.                                      clears the current board
                            quit.                                       exits the program
                            add [colour] bead to [position].            add a bead to a specified location
                            remove bead from [position].                remove a bead from a specified location
                            get [colour] move.                          have the computer propose a move
                            show board.                                 shows a console representation of the board
                            draw board.                                 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            go gui.                                     displays the user interface
                            stop gui.                                   hides the user interface
                            """);
                    break;
            }
            //Temporary thread tracker
            //System.out.println(Thread.activeCount());
        }
    }

    public void startGui() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cappuccino Score Four -- DEBUG MODE");

        panel = new Panel(board);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}