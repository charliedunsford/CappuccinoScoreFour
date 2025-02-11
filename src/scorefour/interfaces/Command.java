package scorefour.interfaces;

import scorefour.core.GameState;

public interface Command {

    boolean parse(String input);
    void execute(GameState gameState);
    String getHelp();

}
