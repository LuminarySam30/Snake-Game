package org.example.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food extends Entity {
    private Color color;

    public Food(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.color = Color.red;
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public void placeRandomly(int boardWidth, int boardHeight, Random random) {
        int maxX = boardWidth / tileSize;
        int maxY = boardHeight / tileSize;
        setPosition(random.nextInt(maxX), random.nextInt(maxY));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fill3DRect(getX() * tileSize, getY() * tileSize, tileSize, tileSize, true);
    }
}