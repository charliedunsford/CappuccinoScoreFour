package scorefour.controller;

import scorefour.common.ButtonAction;
import scorefour.common.Updatable;
import scorefour.common.GameState;
import scorefour.view.ButtonView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * {@code ButtonController} manages a buttons interactions, updates, drawing, and
 * user input handling.
 * <p>
 * It coordinates with {@link Rectangle} bounds, {@link ButtonView}, and {@link GameState} to render and
 * control a button by changing the {@link GameState}.
 */
public class ButtonController implements Updatable {

    private final Rectangle bounds;
    private final ButtonView view;
    private final AudioController audioController;
    private final ButtonAction action;

    private final int hoverSound;
    private boolean hoverSoundPlayed = false;
    protected boolean mouseOver, mousePressed;

    /**
     * Constructs a {@code ButtonController} object with the given {@link ButtonView} used to display the button.
     * <p>
     * The button also depends on {@link Rectangle} bounds to set its size and a {@link ButtonAction} for the button
     * to execute when interacted with.
     * <p>
     * The {@code ButtonController} also initializes its own {@link AudioController} to create sound with.
     *
     * @param bounds the {@link Rectangle} object size of the button
     * @param view the {@link ButtonView} to render the object
     * @param action the {@link ButtonAction} for the object execute
     */
    public ButtonController(Rectangle bounds, ButtonView view, ButtonAction action, int hoverSound, AudioController audioController) {
        this.bounds = bounds;
        this.view = view;
        this.action = action;
        this.hoverSound = hoverSound;
        this.audioController = audioController;
    }

    /**
     * When a button is interacted with the {@link ButtonView} will update its image to the "hover" button image
     * to indicate that the button is being hovered over by the user. This will be accompanied by a sound played
     * by the {@link AudioController}.
     * <p>
     * The button will reset to its default state once the button is no longer being hovered over.
     */
    @Override
    public void update() {
        view.setIndex(0);
        if (mouseOver) {
            view.setIndex(1);
            if (!hoverSoundPlayed) {
                hoverSoundPlayed = true;
                audioController.playEffect(hoverSound);
            }
        }
        if (!mouseOver && hoverSoundPlayed) {
            hoverSoundPlayed = false;
        }
    }

    /**
     * Informs the {@link ButtonController} that the mouse is over its bounds.
     *
     * @param mouseOver {@code true} if the mouse is over the button; {@code false} otherwise
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * A method which provides the calling method with the current status of the mouse press.
     *
     * @return (@code true} if the mouse has pressed the button; {@code false} otherwise
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Sets the buttons status as being pressed.
     *
     * @param mousePressed {@code true} for pressed, {@code false} otherwise
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Applied the {@link ButtonAction} passed to the button's constructor.
     */
    public void applyAction() {
        action.execute();
    }

    /**
     * Detects if the {@link MouseInputs} is in the buttons bounds.
     *
     * @param e the {@link MouseEvent} containing the current mouse position
     * @return {@code true} if the cursor is inside the button; {@code false} otherwise
     */
    public boolean inBounds(MouseEvent e) {
        return bounds.contains(e.getX(), e.getY());
    }

    /**
     * Draws the {@link ButtonView}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
    }

    /**
     * Sets the y-axis coordinate of the button
     *
     * @param y integer of button y-axis coordinate
     */
    public void setY(int y) {
        bounds.setLocation(bounds.x, y);
    }

    /**
     * @return integer of button y-axis coordinate
     */
    public int getY() {
        return bounds.y;
    }

    public ButtonView getButtonView() {
        return view;
    }
}
