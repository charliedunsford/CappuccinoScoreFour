import scorefour.gamemodes.*;
import scorefour.interfaces.GameMode;

public class ScoreFour {
    public static void main(String[] args) {

        GameMode mode;

        if (args.length == 0) {
            mode = new NormalMode();
        } else if (args[0].equals("test")) {
            mode = new TestMode();
        } else {
            throw new IllegalArgumentException("Invalid argument");
        }

        mode.setup();
    }
}