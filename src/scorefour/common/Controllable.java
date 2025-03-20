package scorefour.common;

/**
 * A {@code Controllable} class is a class which need to be updated every game loop.
 */
public interface Controllable {

    /**
     * Calls for updates.
     */
    void update();
}
