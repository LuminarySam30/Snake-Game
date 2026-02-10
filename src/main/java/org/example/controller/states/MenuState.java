package org.example.controller.states;

import org.example.controller.GameState;
import org.example.view.SnakeGame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState implements GameState {
    private SnakeGame game;
    private int selectedOption = 0;
    private final String[] options = {"Start Game", "Quit"};

    public MenuState(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void update() {
        // Menu doesn't need continuous updates
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getBoardWidth(), game.getBoardHeight());

        // Title
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.green);
        g.drawString("SNAKE", game.getBoardWidth()/2 - 100, 150);

        // High Score
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.yellow);
        g.drawString("High Score: " + game.getHighScoreManager().getHighScore(),
                game.getBoardWidth()/2 - 80, 220);

        // Menu options
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                g.setColor(Color.white);
                g.drawString("> " + options[i], game.getBoardWidth()/2 - 80, 300 + i * 50);
            } else {
                g.setColor(Color.gray);
                g.drawString("  " + options[i], game.getBoardWidth()/2 - 80, 300 + i * 50);
            }
        }

        // Instructions
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.lightGray);
        g.drawString("Use UP/DOWN arrows to navigate, ENTER to select", game.getBoardWidth()/2 - 180, 450);
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            selectedOption = (selectedOption - 1 + options.length) % options.length;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            selectedOption = (selectedOption + 1) % options.length;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (selectedOption == 0) {
                game.startNewGame();
            } else if (selectedOption == 1) {
                System.exit(0);
            }
        }
    }

    @Override
    public void enter() {
        selectedOption = 0;
    }

    @Override
    public void exit() {}
}