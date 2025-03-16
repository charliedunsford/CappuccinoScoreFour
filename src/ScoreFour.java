import scorefour.controller.GameController;
import scorefour.gamemodes.*;
import scorefour.common.GameMode;

/**
 * This program simulates a Score Four game.
 *
 * @author Team Cappuccino
 */
public class ScoreFour {

    /**
     * The {@code main} method is the starting point of the program. It can be passed
     * arguments to enter different {@link GameMode}'s. If no arguments are passed the
     * program will run in its default mode.
     *
     * @param args string arguments which define the behavior of the program
     */
    public static void main(String[] args) {
        GameMode mode;

        if (args.length == 0) {
            mode = new NormalMode();
        } else if (args[0].equalsIgnoreCase("test")) {
            mode = new TestMode();
        } else {
            System.err.println("java ScoreFour [test]");
            return;
        }

        mode.setup(new GameController());
    }
}