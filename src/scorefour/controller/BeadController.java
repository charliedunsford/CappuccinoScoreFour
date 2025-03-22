package scorefour.controller;

import scorefour.common.Updatable;
import scorefour.model.Bead;
import scorefour.model.Board;
import scorefour.view.BeadView;

import java.awt.*;

/**
 * {@link BeadController} manages a beads interactions, updates, and drawing.
 * <p>
 * It coordinates with a {@link Bead}, {@link BeadView}, and location information to render and
 * control a bead on a {@link Board}.
 */
public class BeadController implements Updatable {

    private final Bead bead;
    private final BeadView view;
    private final int targetY;
    private boolean atTarget;

    /**
     * Builds a {@link BeadController} object which modifies the state of a {@link Bead} object,
     * and renders these changes to the {@link BeadView}.
     *
     * @param bead the {@link Bead} object to modify
     * @param view the {@link BeadView} to render with
     * @param x the {@code x} coordinate of the bead
     * @param targetY the target {@code y} coordinate for the bead to move towards
     * @param startY the starting {@code y} coordinate of the bead
     */
    public BeadController(Bead bead, BeadView view, int x, int targetY, int startY) {
        this.bead = bead;
        this.view = view;
        this.targetY = targetY;

        bead.setX(x);
        bead.setY(startY);
    }

    /**
     * Checks if the {@link Bead} is at the {@code targetY} coordinate, if not, {@code update} moves
     * the bead closer until it is.
     */
    @Override
    public void update() {
        if (atTarget) {
            return;
        }

        int y = bead.getY();

        if (y < targetY) {
            y += 2;
            if (y >= targetY) {
                atTarget = true;
            }
            bead.setY(y);
        }
    }

    /**
     * Draws the {@link BeadView}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
    }
}
