package org.example.controller.states;

import org.example.controller.GameState;
import org.example.model.Food;
import org.example.model.Snake;
import org.example.view.SnakeGame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayingState implements GameState {
    private SnakeGame game;
    private Snake snake;
    private Food food;
    private Random random;
    private boolean gameStarted = false;
    private int tileSize;

    public PlayingState(SnakeGame game, int tileSize) {
        this.game = game;
        this.tileSize = tileSize;
        this.random = new Random();
        initializeGame();
    }

    private void initializeGame() {
        snake = new Snake(5, 5, tileSize);
        food = new Food(10, 10, tileSize);
        food.placeRandomly(game.getBoardWidth(), game.getBoardHeight(), random);
        gameStarted = false;
    }

    public void resetGame() {
        initializeGame();
    }

    @Override
    public void update() {
        if (!gameStarted) {
            return;
        }

        if (snake.collidesWith(food)) {
            snake.grow(food.getPosition());
            food.placeRandomly(game.getBoardWidth(), game.getBoardHeight(), random);
        }

        snake.move();

        if (snake.collidesWithSelf() || snake.isOutOfBounds(game.getBoardWidth(), game.getBoardHeight())) {
            game.getHighScoreManager().saveHighScore(snake.getScore());
            game.transitionToGameOver(snake.getScore());
        }
    }

    @Override
    public void render(Graphics g) {
        // Draw grid
        g.setColor(Color.darkGray);
        for (int i = 0; i < game.getBoardWidth() / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, game.getBoardHeight());
            g.drawLine(0, i * tileSize, game.getBoardWidth(), i * tileSize);
        }

        food.render(g);
        snake.render(g);

        // Draw score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setColor(Color.white);
        g.drawString("Score: " + snake.getScore(), tileSize - 16, tileSize);

        // Draw high score
        g.drawString("High Score: " + game.getHighScoreManager().getHighScore(),
                game.getBoardWidth() - 150, tileSize);

        // Draw pause instruction
        if (gameStarted) {
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.setColor(Color.lightGray);
            g.drawString("Press P to Pause", game.getBoardWidth()/2 - 50, tileSize);
        }
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            game.transitionToPause();
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.moveUp();
            gameStarted = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.moveDown();
            gameStarted = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.moveLeft();
            gameStarted = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.moveRight();
            gameStarted = true;
        }
    }

    @Override
    public void enter() {
        gameStarted = false;
    }

    @Override
    public void exit() {}

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}