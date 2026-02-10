package org.example.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface GameState {
    void update();
    void render(Graphics g);
    void handleKeyPress(KeyEvent e);
    void enter();
    void exit();
}