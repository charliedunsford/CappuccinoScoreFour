import scorefour.core.Game;
import scorefour.gamemodes.*;
import scorefour.common.GameMode;

/**
 * This program simulates a Score Four game.
 *
 * @author Team Cappuccino
 */
public class ScoreFour {

    /**
     * The {@code main} method is the starting point of the program and can be passed
     * arguments to enter different {@link GameMode}'s.
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

        mode.setup(new Game());
    }
}