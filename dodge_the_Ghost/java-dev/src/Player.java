import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player extends Sprite {

    public Player() {
        w = 120;
        h = 120;
        x = 50;
        y = 600;
        image = new ImageIcon(Player.class.getResource("player.gif"));
    }

    public void move() {
        // if (x > 900) {
        // y = 0;
        // }
        x = x + speed;

    }

    public boolean outOfScreen() {
        return x > 1500;
    }
}
