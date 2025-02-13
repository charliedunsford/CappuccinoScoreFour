import scorefour.core.Game;
import scorefour.gamemodes.*;
import scorefour.common.GameMode;

public class ScoreFour {
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