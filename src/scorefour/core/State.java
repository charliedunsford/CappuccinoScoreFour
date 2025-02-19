package scorefour.core;

import scorefour.common.GameState;
import scorefour.ui.AudioPlayer;
import scorefour.ui.MenuButton;

import java.awt.event.MouseEvent;

public abstract class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }

    public void setGameState(GameState state) {
        switch (state) {
            case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU);
            case PLAYING -> game.getAudioPlayer().playSong(AudioPlayer.GAME);
        }
        GameState.state = state;
    }

}
