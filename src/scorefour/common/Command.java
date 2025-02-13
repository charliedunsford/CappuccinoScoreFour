package scorefour.common;

import scorefour.core.Game;

public interface Command {
    boolean parse(String input);

    void execute(Game game);

    String getHelp();

}