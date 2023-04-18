package com.problems.marsrover;

/**
 * Virtual grid of size of plateau where bottom left corner coordinates are [0, 0] and top right corner coordinates are [xCoordinate, yCoordinate]
 */
public class Plateau {
    private Integer xCoordinate;
    private Integer yCoordinate;

    public Plateau(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public boolean hasMovedOut(Integer xCoordinate, Integer yCoordinate) {
        return !isCoordinateInsidePlateau(xCoordinate, yCoordinate);
    }

    private boolean isCoordinateInsidePlateau(Integer xCoordinate, Integer yCoordinate) {
        return xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate <= this.xCoordinate && yCoordinate <= this.yCoordinate;
    }
}
