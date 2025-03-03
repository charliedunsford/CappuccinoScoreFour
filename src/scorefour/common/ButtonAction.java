package scorefour.common;

import scorefour.controller.ButtonController;

/**
 * The {@code ButtonAction} to be executed when a {@link ButtonController} is interacted with.
 */
public interface ButtonAction {

    /**
     * Executes the {@link ButtonAction}.
     */
    void execute();
}
