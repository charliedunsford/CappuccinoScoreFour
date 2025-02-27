package scorefour.view;

import scorefour.common.Viewable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MenuView implements Viewable {

    private ImageIcon menuBackground;

    public MenuView() {
        loadBackground();
    }

    public void loadBackground() {
        try {
            menuBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/menu.gif")));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load menu background.");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackground.getImage(),0,0,null);
    }
}
