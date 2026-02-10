package org.example.view;

import org.example.controller.*;
import org.example.controller.states.*;
import org.example.model.HighScoreManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class

SnakeGame extends JPanel implements ActionListener {
    private int boardWidth;
    private int boardHeight;
    private int tileSize = 25;

    private GameStateContext stateContext;
    private MenuState menuState;
    private PlayingState playingState;
    private PauseState pauseState;
    private GameOverState gameOverState;

    private Timer gameLoop;
    private HighScoreManager highScoreManager;

    public SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(new KeyInputs(this));
        setFocusable(true);

        highScoreManager = new HighScoreManager();

        // Initialize all states
        menuState = new MenuState(this);
        playingState = new PlayingState(this, tileSize);
        pauseState = new PauseState(this, playingState);
        gameOverState = new GameOverState(this);

        // Start with menu state
        stateContext = new GameStateContext(menuState);

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        stateContext.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateContext.update();
        repaint();
    }

    public void handleKeyPress(KeyEvent e) {
        stateContext.handleKeyPress(e);
    }

    // State transition methods
    public void transitionToMenu() {
        stateContext.setState(menuState);
    }

    public void startNewGame() {
        playingState.resetGame();
        stateContext.setState(playingState);
    }

    public void transitionToPlaying() {
        stateContext.setState(playingState);
    }

    public void transitionToPause() {
        stateContext.setState(pauseState);
    }

    public void transitionToGameOver(int finalScore) {
        gameOverState.setFinalScore(finalScore);
        stateContext.setState(gameOverState);
    }

    // Getters
    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }
}