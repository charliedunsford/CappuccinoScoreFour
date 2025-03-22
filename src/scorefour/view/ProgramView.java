package scorefour.view;

import scorefour.controller.ProgramController;

/**
 *  The main view of the {@link ProgramController}
 */
public class ProgramView {

    private final ProgramController programController;

    private Panel panel;
    private Frame frame;

    /**
     * {@code PANEL_WIDTH} defines the width of the {@link Panel}.
     */
    public static final int PANEL_WIDTH = 800;
    /**
     * {@code PANEL_HEIGHT} defines the height of the {@link Panel}.
     */
    public static final int PANEL_HEIGHT = 600;

    /**
     * Constructs a new {@link ProgramView}
     *
     * @param programController the controller of the program
     */
    public ProgramView(ProgramController programController) {
        this.programController = programController;
    }

    /**
     * Starts the graphical user interface if a {@link Panel} object does not already exist.
     */
    public void startGUI() {
        if (panel != null) {
            return;
        }
        panel = new Panel(programController);
        frame = new Frame(panel);
    }

    /**
     * Stops the graphical user interface.
     */
    public void stopGUI() {
        panel = null;
        if (frame != null) {
            frame.dispose();
        }
    }

    /**
     *  Schedules the {@link Panel} to call paintComponent which triggers
     *  the {@link ProgramController} to render.
     */
    public void repaint() {
        if (panel != null) {
            panel.repaint();
        }
    }

    /**
     * Returns the programs {@link Panel}.
     *
     * @return Panel
     */
    public Panel getPanel() {
        return panel;
    }
}
