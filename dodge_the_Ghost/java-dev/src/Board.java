import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {
    Timer timer;
    BufferedImage backgroundImage;
    Player player;
    Enemy enemies[] = new Enemy[7];

    public Board() {
        setSize(1500, 900);
        loadBackgroundImage();
        player = new Player();
        loadEnemies();
        gameloop();
        bindEvents();
        setFocusable(true);
    }

    private void gameOver(Graphics pen) {
        if (player.outOfScreen()) {
            pen.setFont(new Font("times", Font.BOLD, 50));
            pen.setColor(Color.RED);
            pen.drawString("GAME WIN", 1500 / 2, 900 / 2);
            timer.stop();
            return;
        }
        for (Enemy enemy : enemies) {
            if (isCollide(enemy)) {
                pen.setFont(new Font("times", Font.BOLD, 50));
                pen.setColor(Color.RED);
                pen.drawString("GAME OVER", 1500 / 2, 900 / 2);
                timer.stop();
            }
        }
    }

    private boolean isCollide(Enemy enemy) {
        // for(Enemy)
        int xDistance = Math.abs(player.x - enemy.x);
        int yDistance = Math.abs(player.y - enemy.y);
        int maxH = Math.max(player.h, enemy.h);
        int maxW = Math.max(player.w, enemy.w);
        return xDistance <= maxW - 50 && yDistance <= maxH - 50;
        // return false;
    }

    private void bindEvents() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.speed = 15;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.speed = -15;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.speed = 0;
            }
        });
    }

    private void loadEnemies() {
        int x = 300;
        int gap = 150;
        int speed = 5;
        for (int i = 0; i < enemies.length; i++) {
            // int x = 300;
            // int gap = 100;
            // int speed = 5;
            enemies[i] = new Enemy(x, speed);
            x = x + gap;
            speed = speed + 3;
        }
    }

    private void gameloop() {
        timer = new Timer(50, (e) -> {
            repaint();
            // gameOver(getGraphics());
        });
        timer.start();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(Board.class.getResource("game_bg.jpg"));
        } catch (IOException e) {
            System.out.println("Background Image not found....");
            System.exit(1);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void printEnemies(Graphics pen) {
        for (Enemy enemy : enemies) {
            enemy.draw(pen);
            enemy.move();
        }
    }

    @Override
    public void paintComponent(Graphics pen)
    // all printing logic will be here
    {
        super.paintComponent(pen);
        pen.drawImage(backgroundImage, 0, 0, 1500, 920, null);
        player.draw(pen);
        player.move();
        printEnemies(pen);
        gameOver(pen);
    }
}