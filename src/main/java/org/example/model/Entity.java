package org.example.model;

import java.awt.Graphics;

public abstract class Entity {
    protected Tile position;
    protected int tileSize;

    public Entity(int x, int y, int tileSize) {
        this.position = new Tile(x, y);
        this.tileSize = tileSize;
    }

    public Tile getPosition() { return position; }
    public int getX() { return position.getX(); }
    public int getY() { return position.getY(); }
    public int getTileSize() { return tileSize; }

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    public void setX(int x) { position.setX(x); }
    public void setY(int y) { position.setY(y); }

    public boolean collidesWith(Entity other) {
        return this.position.equals(other.getPosition());
    }

    public abstract void render(Graphics g);
}