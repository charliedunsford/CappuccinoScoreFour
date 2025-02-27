package scorefour.core;

import scorefour.common.GameState;
import scorefour.controller.Audio;

public abstract class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGameState(GameState state) {
        switch (state) {
            case MENU -> game.getAudio().playSong(Audio.MENU);
            case PLAYING -> game.getAudio().playSong(Audio.GAME);
        }
        GameState.state = state;
    }

}
