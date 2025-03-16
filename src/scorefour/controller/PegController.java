package scorefour.controller;

import scorefour.common.*;
import scorefour.model.Bead;
import scorefour.model.Peg;
import scorefour.view.BeadView;
import scorefour.view.ButtonView;
import scorefour.view.Panel;
import java.util.List;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Not fully documented yet!
 */
public class PegController implements Updatable, Interactable {

    private final Peg peg;
    private final int row;
    private final int col;
    private final ButtonController button;
    private final List<BeadController> beadControllers;
    private final AudioController audioController;

    private static final int BEAD_SPACING = 37;

    public PegController(Peg peg, int row, int col, AudioController audioController) {
        this.peg = peg;
        this.row = row;
        this.col = col;
        this.audioController = audioController;
        this.button = createPegButton();
        this.beadControllers = new ArrayList<>();
    }

    private ButtonController createPegButton() {
        ButtonAction addBead = () -> {
            peg.addBead(BeadColour.BLACK);
            audioController.playEffect(AudioController.FALLING);
        };

        int x = 218 + col * 162 - (row * 53);
        int y = 29 + row * 61;
        Rectangle bounds = new Rectangle(x, y, 30, 180);
        ButtonView buttonView = new ButtonView(bounds, 4);
        return new ButtonController(bounds, buttonView, addBead, AudioController.PEG_HOVER, audioController);
    }

    /**
     * Calls for updates.
     */
    @Override
    public void update() {
        Bead[] beads = peg.getBeads();

        // Checks how many beads are on the peg.
        int currentBeadCount = 0;
        for (Bead bead : beads) {
            if (bead != null) {
                currentBeadCount++;
            } else {
                break;
            }
        }

        // Removes all controllers for blank peg positions.
        while (beadControllers.size() > currentBeadCount) {
            beadControllers.removeLast();
        }

        // Adds controller for each new bead on a peg.
        int currentControllers = beadControllers.size();
        for (int i = currentControllers; i < beads.length; i++) {
            if (beads[i] != null) {
                int x = 207 + col * 162 - (row * 53);
                int startY = peg.getY() - 145;
                int targetY = peg.getY() - 25 - (i * BEAD_SPACING);
                BeadController newBeadController = new BeadController(beads[i], new BeadView(beads[i]), x, targetY, startY);
                beadControllers.add(newBeadController);
            }
        }

        // Updates bead controller.
        for (BeadController controller : beadControllers) {
            controller.update();
        }

        // Updates buttons.
        button.update();
    }

    public void draw(Graphics g) {
        button.draw(g);
        for (BeadController controller : beadControllers) {
            controller.draw(g);
        }
    }

    /**
     * Communicates with controllers when a mouse button has been pressed.
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (button.inBounds(e)) {
            button.setMousePressed(true);
        }
    }

    /**
     * Communicates with controllers to set the {@link GameState} and any additional changes depending on the button
     * released.
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (button.inBounds(e) && button.isMousePressed()) {
            button.applyAction();
        }
        button.setMousePressed(false);
    }

    /**
     * Checks when a mouse has been moved and detects where the mouse is in a {@link Panel}.
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        button.setMouseOver(button.inBounds(e));
    }
}
