package scorefour.controller;

import scorefour.common.ButtonAction;
import scorefour.common.Updatable;
import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.view.ButtonView;
import scorefour.view.MenuView;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * {@link MenuController} manages the menu state interactions, updates, drawing, and
 * user input handling.
 * <p>
 * It coordinates the {@link ButtonController} to render and control the in game interface by changing
 * the {@link GameState}.
 */
public class MenuController implements Updatable, Interactable {

    private final ArrayList<ButtonController> buttons;
    private final MenuView view;
    private final AudioController audioController;
    private final AudioController effectAudioController;

    private int vsSelectorRow;

    /**
     * Constructs a {@link MenuController} object with the given {@link MenuView} used to display the menu.
     * <p>
     * Initializes the menu buttons and audio.
     *
     * @param view the playing view used to render the menu to the panel.
     */
    public MenuController(MenuView view, AudioController audioController) {
        this.view = view;
        this.audioController = audioController;
        this.effectAudioController = new AudioController();
        buttons = new ArrayList<>();
        vsSelectorRow = 5;
        loadButtons();
    }

    // Loads the menu buttons.
    private void loadButtons() {
        ButtonAction playGame = () -> {
            audioController.stopSong();
            GameState.state = GameState.GAME;
            audioController.playSong(AudioController.GAME);
        };
        ButtonAction quitGame = () -> { GameState.state = GameState.QUIT; };

        ButtonAction vsSelector = () -> {
            // Set players here
            if (vsSelectorRow < 7) {
                buttons.getFirst().getButtonView().setRow(++vsSelectorRow);
            } else if (vsSelectorRow == 7) {
                buttons.getFirst().getButtonView().setRow(vsSelectorRow = 5);
            }
            audioController.playEffect(AudioController.OPTION_HOVER);
        };

        Rectangle vsSelectorBounds = new Rectangle(185, 550, 0, 0);
        ButtonView vsSelectorView = new ButtonView(vsSelectorBounds, 5);
        buttons.add(new ButtonController(vsSelectorBounds, vsSelectorView, vsSelector, AudioController.OPTION_HOVER, effectAudioController));

        Rectangle playBounds = new Rectangle(160, 336, 0, 0);
        ButtonView playButtonView = new ButtonView(playBounds, 0);
        buttons.add(new ButtonController(playBounds, playButtonView, playGame, AudioController.OPTION_HOVER, effectAudioController));

        Rectangle quitBounds = new Rectangle(448, 336, 0, 0);
        ButtonView quitButtonView = new ButtonView(quitBounds, 1);
        buttons.add(new ButtonController(quitBounds, quitButtonView, quitGame, AudioController.OPTION_HOVER, effectAudioController));
    }

    /**
     * Updates the {@link ButtonController}'s associated with the menu.
     */
    @Override
    public void update() {
        for (ButtonController button : buttons) {
            button.update();
        }
    }

    /**
     * Draws the {@link MenuView} and {@link ButtonController}'s.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
        for (ButtonController button : buttons) {
            button.draw(g);
        }
    }

    /**
     * Communicates with {@link ButtonController} when a mouse button has been pressed.
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (ButtonController button : buttons) {
            if (button.inBounds(e)) {
                button.setMousePressed(true);
            }
        }
    }

    /**
     * Communicates with {@link ButtonController} to set the {@link GameState} depending on the button
     * released.
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (ButtonController button : buttons) {
            if (button.inBounds(e) && button.isMousePressed()) {
                button.applyAction();
            }
        }
    }

    /**
     * Checks when a mouse has been moved and detects where the mouse is in a {@link Panel}.
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for (ButtonController button : buttons) {
            button.setMouseOver((button.inBounds(e)));
        }
    }

    /**
     * @return the {@link AudioController} used by the menu
     */
    public AudioController getAudioController() {
        return audioController;
    }
}
