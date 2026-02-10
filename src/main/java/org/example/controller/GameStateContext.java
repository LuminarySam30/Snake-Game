package org.example.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameStateContext {
    private GameState currentState;

    public GameStateContext(GameState initialState) {
        this.currentState = initialState;
        if (currentState != null) {
            currentState.enter();
        }
    }

    public void setState(GameState newState) {
        if (currentState != null) {
            currentState.exit();
        }
        this.currentState = newState;
        if (currentState != null) {
            currentState.enter();
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    public void render(Graphics g) {
        if (currentState != null) {
            currentState.render(g);
        }
    }

    public void handleKeyPress(KeyEvent e) {
        if (currentState != null) {
            currentState.handleKeyPress(e);
        }
    }
}