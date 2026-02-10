package org.example.controller.states;
import org.example.controller.GameState;
import org.example.view.SnakeGame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseState implements GameState {
    private SnakeGame game;
    private PlayingState playingState;

    public PauseState(SnakeGame game, PlayingState playingState) {
        this.game = game;
        this.playingState = playingState;
    }

    @Override
    public void update() {
        // No updates while paused
    }

    @Override
    public void render(Graphics g) {
        // First render the game state in the background
        g.setColor(Color.darkGray);
        for (int i = 0; i < game.getBoardWidth() / 25; i++) {
            g.drawLine(i * 25, 0, i * 25, game.getBoardHeight());
            g.drawLine(0, i * 25, game.getBoardWidth(), i * 25);
        }

        playingState.getFood().render(g);
        playingState.getSnake().render(g);

        // Draw semi-transparent overlay
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, game.getBoardWidth(), game.getBoardHeight());

        // Draw pause text
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.yellow);
        g.drawString("PAUSED", game.getBoardWidth()/2 - 110, game.getBoardHeight()/2 - 50);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Press P to Resume", game.getBoardWidth()/2 - 90, game.getBoardHeight()/2 + 20);
        g.drawString("Press M for Menu", game.getBoardWidth()/2 - 85, game.getBoardHeight()/2 + 50);
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            game.transitionToPlaying();
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            game.transitionToMenu();
        }
    }

    @Override
    public void enter() {}

    @Override
    public void exit() {}
}
