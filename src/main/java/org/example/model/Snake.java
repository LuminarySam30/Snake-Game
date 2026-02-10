package org.example.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake extends Entity {
    private ArrayList<Tile> body;
    private int velocityX;
    private int velocityY;
    private Color headColor;
    private Color bodyColor;

    public Snake(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.body = new ArrayList<>();
        this.velocityX = 1;
        this.velocityY = 0;
        this.headColor = Color.green;
        this.bodyColor = Color.green;
    }

    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }
    public ArrayList<Tile> getBody() { return body; }
    public int getScore() { return body.size(); }

    public void setVelocity(int vx, int vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }

    public void move() {
        for (int i = body.size() - 1; i >= 0; i--) {
            Tile segment = body.get(i);
            if (i == 0) {
                segment.setX(getX());
                segment.setY(getY());
            } else {
                Tile prevSegment = body.get(i - 1);
                segment.setX(prevSegment.getX());
                segment.setY(prevSegment.getY());
            }
        }
        setX(getX() + velocityX);
        setY(getY() + velocityY);
    }

    public void grow(Tile foodPosition) {
        body.add(new Tile(foodPosition.getX(), foodPosition.getY()));
    }

    public boolean collidesWithSelf() {
        for (Tile segment : body) {
            if (position.equals(segment)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds(int boardWidth, int boardHeight) {
        int pixelX = getX() * tileSize;
        int pixelY = getY() * tileSize;
        return pixelX < 0 || pixelX >= boardWidth ||
                pixelY < 0 || pixelY >= boardHeight;
    }

    public void moveUp() {
        if (velocityY != 1) setVelocity(0, -1);
    }

    public void moveDown() {
        if (velocityY != -1) setVelocity(0, 1);
    }

    public void moveLeft() {
        if (velocityX != 1) setVelocity(-1, 0);
    }

    public void moveRight() {
        if (velocityX != -1) setVelocity(1, 0);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(headColor);
        g.fill3DRect(getX() * tileSize, getY() * tileSize, tileSize, tileSize, true);

        drawFace(g);

        g.setColor(bodyColor);
        for (Tile segment : body) {
            g.fill3DRect(segment.getX() * tileSize, segment.getY() * tileSize, tileSize, tileSize, true);
        }
    }

    private void drawFace(Graphics g) {
        int headX = getX() * tileSize;
        int headY = getY() * tileSize;

        int leftEyeX = headX + 5;
        int rightEyeX = headX + 15;
        int leftEyeY = headY + 8;
        int rightEyeY = headY + 8;

        if (velocityX == 1) {
            leftEyeX = headX + 10;
            rightEyeX = headX + 18;
        } else if (velocityX == -1) {
            leftEyeX = headX + 2;
            rightEyeX = headX + 10;
        } else if (velocityY == -1) {
            leftEyeY = headY + 5;
            rightEyeY = headY + 5;
        } else if (velocityY == 1) {
            leftEyeY = headY + 12;
            rightEyeY = headY + 12;
        }

        g.setColor(Color.white);
        g.fillOval(leftEyeX, leftEyeY, 6, 6);
        g.fillOval(rightEyeX, rightEyeY, 6, 6);

        g.setColor(Color.black);
        g.fillOval(leftEyeX + 2, leftEyeY + 2, 3, 3);
        g.fillOval(rightEyeX + 2, rightEyeY + 2, 3, 3);

        g.setColor(Color.red);
        if (velocityX == 1) {
            g.fillRect(headX + tileSize - 2, headY + tileSize/2 - 1, 4, 2);
        } else if (velocityX == -1) {
            g.fillRect(headX - 2, headY + tileSize/2 - 1, 4, 2);
        } else if (velocityY == -1) {
            g.fillRect(headX + tileSize/2 - 1, headY - 2, 2, 4);
        } else if (velocityY == 1) {
            g.fillRect(headX + tileSize/2 - 1, headY + tileSize - 2, 2, 4);
        }
    }
}