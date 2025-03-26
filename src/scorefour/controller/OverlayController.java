package scorefour.controller;

import scorefour.common.*;
import scorefour.view.ButtonView;
import scorefour.view.OverlayView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * {@link OverlayController} manages the in game overlay interactions, updates, drawing, and
 * user input handling.
 * <p>
 * It coordinates the {@link OverlayView} and {@link ButtonController}
 * to render and control the in game overlay.
 */
public class OverlayController implements Updatable, Interactable {

    private final BoardController boardController;
    private final AudioController effectAudioController;
    private final GameManager gameManager;

    private final ArrayList<ButtonController> buttons;
    private final Rectangle bounds;
    private final OverlayView view;

    protected boolean mouseOver;

    /**
     * Constructs a {@link OverlayController} object with the given {@link Rectangle} bounds and {@link OverlayView}.
     * <p>
     * Initializes the button components to be displayed on the overlay.
     *
     * @param bounds the size of the interactable overlay
     * @param view the overlay view to be displayed
     */
    public OverlayController(Rectangle bounds, OverlayView view, BoardController boardController, GameManager gameManager) {
        this.bounds = bounds;
        this.view = view;
        this.boardController = boardController;
        this.effectAudioController = new AudioController();
        this.gameManager = gameManager;
        buttons = new ArrayList<>();
        loadButtons();
    }

    // Loads all buttons on the overlay.
    private void loadButtons() {
        ButtonAction quitGame = () -> { GameState.state = GameState.QUIT; };
        ButtonAction clearBoard = () -> { boardController.clearBoard(); };
        ButtonAction reset = () -> {
            gameManager.resetGame();
            gameManager.resetScore();
        };

        Rectangle clearButtonBounds = new Rectangle(5, 600, 0, 0);
        ButtonView clearButtonView = new ButtonView(clearButtonBounds, 2);
        buttons.add(new ButtonController(clearButtonBounds, clearButtonView, clearBoard, AudioController.BUTTON_HOVER, effectAudioController));

        Rectangle quitButtonBounds = new Rectangle(670, 600, 0, 0);
        ButtonView quitButtonView = new ButtonView(quitButtonBounds, 3);
        buttons.add(new ButtonController(quitButtonBounds, quitButtonView, quitGame, AudioController.BUTTON_HOVER, effectAudioController));

        Rectangle resetButtonBounds = new Rectangle(330, 600, 0, 0);
        ButtonView resetButtonView = new ButtonView(resetButtonBounds, 8);
        buttons.add(new ButtonController(resetButtonBounds, resetButtonView, reset, AudioController.BUTTON_HOVER, effectAudioController));
    }

    /**
     * Updates all components which may change in the {@link OverlayController}.
     * <p>
     * Raises the {@link OverlayView} when the {@link MouseInputs} detect that the mouse is over the overlay.
     */
    @Override
    public void update() {
        if (mouseOver) {
            if (view.getY() != 0) {
                view.setY(view.getY() - 3);
                for (ButtonController button : buttons) {
                    button.setY(button.getY() - 3);
                }
            }
        } else {
            if (view.getY() != 45) {
                view.setY(view.getY() + 3);
                for (ButtonController button : buttons) {
                    button.setY(button.getY() + 3);
                }
            }
        }

        view.setScore(gameManager.getScore());

        if (boardController.getGameManager().getCurrentPlayer().getColour() == BeadColour.WHITE) {
            view.setCurrentPlayerColour(BeadColour.WHITE);
        } else {
            view.setCurrentPlayerColour(BeadColour.BLACK);
        }

        for (ButtonController button : buttons) {
            button.update();
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
     * Informs the {@link OverlayController} that the mouse is over its bounds.
     *
     * @param mouseOver {@code true} if the mouse is over the overlay; {@code false} otherwise
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Detects if the {@link MouseInputs} is in the overlays bounds.
     *
     * @param e the {@link MouseEvent} containing the current mouse position
     * @return {@code true} if the cursor is inside the overlay; {@code false} otherwise
     */
    public boolean inBounds(MouseEvent e) {
        return bounds.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Draws the {@link OverlayView} and {@link ButtonController}'s.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
        for (ButtonController button : buttons) {
            button.draw(g);
        }
    }
}
