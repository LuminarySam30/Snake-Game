package org.example.controller;

import org.example.view.SnakeGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private SnakeGame game;

    public KeyInputs(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.handleKeyPress(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}