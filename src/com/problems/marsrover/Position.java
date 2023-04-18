package com.problems.marsrover;

/**
 * For each rover maintain its position for xCoordinate, xCoordinate and direction. Also keep track if rover has moved out of plateau. Provide methods to perform turnLeft, turnRight and move
 */
public class Position {
    private Plateau plateau;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private Direction direction;
    private boolean movedOutOfGrid = false;

    public Position(Plateau plateau, Integer xCoordinate, Integer yCoordinate, Direction direction) {
        this.plateau = plateau;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = Direction.parse(direction.left);
    }

    public void turnRight() {
        direction = Direction.parse(direction.right);
    }

    public void move() {
        if (!movedOutOfGrid) {
            xCoordinate = xCoordinate + direction.moveX;
            yCoordinate = yCoordinate + direction.moveY;
            if (plateau.hasMovedOut(xCoordinate, yCoordinate)) {
                movedOutOfGrid = true;
            }
        }
    }

    @Override
    public String toString() {
        if (movedOutOfGrid) {
            return "Rover has moved out of Grid";
        }
        return xCoordinate + " " + yCoordinate + " " + direction.code;
    }
}
