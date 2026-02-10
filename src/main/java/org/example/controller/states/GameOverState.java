package org.example.controller.states;
import org.example.controller.GameState;
import org.example.view.SnakeGame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState implements GameState {
    private SnakeGame game;
    private int finalScore;
    private boolean isNewHighScore;

    public GameOverState(SnakeGame game) {
        this.game = game;
    }

    public void setFinalScore(int score) {
        this.finalScore = score;
        this.isNewHighScore = score >= game.getHighScoreManager().getHighScore();
    }

    @Override
    public void update() {
        // No updates in game over state
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getBoardWidth(), game.getBoardHeight());

        // Game Over title
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.red);
        g.drawString("GAME OVER", game.getBoardWidth()/2 - 160, 200);

        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.setColor(Color.white);
        g.drawString("Score: " + finalScore, game.getBoardWidth()/2 - 70, 280);

        // High score indicator
        if (isNewHighScore && finalScore > 0) {
            g.setColor(Color.yellow);
            g.drawString("NEW HIGH SCORE!", game.getBoardWidth()/2 - 130, 320);
        } else {
            g.setColor(Color.lightGray);
            g.drawString("High Score: " + game.getHighScoreManager().getHighScore(),
                    game.getBoardWidth()/2 - 100, 320);
        }

        // Instructions
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Press SPACE or ENTER to Play Again", game.getBoardWidth()/2 - 180, 400);
        g.drawString("Press M for Menu", game.getBoardWidth()/2 - 90, 440);
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            game.startNewGame();
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            game.transitionToMenu();
        }
    }

    @Override
    public void enter() {}

    @Override
    public void exit() {}
}
