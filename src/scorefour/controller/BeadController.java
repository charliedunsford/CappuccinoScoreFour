package scorefour.controller;

import scorefour.common.Controllable;
import scorefour.model.Bead;
import scorefour.view.BeadView;

// Controllers should handle user input and events, and update the objects model accordingly
public class BeadController implements Controllable {

    private final Bead model;
    private final BeadView view;
    private int x, y;

    public BeadController(Bead model, BeadView view) {
        this.model = model;
        this.view = view;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void update() {

    }
}
