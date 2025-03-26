package scorefour.view;

import scorefour.controller.MouseInputs;
import scorefour.controller.ProgramController;

import javax.swing.*;
import java.awt.*;

import static scorefour.view.ProgramView.PANEL_HEIGHT;
import static scorefour.view.ProgramView.PANEL_WIDTH;

/**
 * A {@code Panel} which acts as the main rendering and input handler for a {@link ProgramController}
 */
public class Panel extends JPanel {

    private final ProgramController programController;

    /**
     * Constructs a new {@code Panel}.
     * <p>
     * Accesses a {@link ProgramController} to render, and creates listeners for user input.
     *
     * @param programController the {@link ProgramController} to be rendered
     */
    public Panel(ProgramController programController) {
        this.programController = programController;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        MouseInputs mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    /**
     * Paints the contents of a {@link ProgramController} to the {@link Panel}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        programController.render(g);
    }

    /**
     * @return {@link ProgramController} object passed to the {@link Panel}
     */
    public ProgramController getProgramController() {
        return programController;
    }
}
