package com.problems.marsrover;

public class Plateau {
    private Integer xCoordinate;
    private Integer yCoordinate;

    public Plateau(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Boolean hasMovedOut(Integer xCoordinate, Integer yCoordinate) {
        return !isCoordinateInsidePlateau(xCoordinate, yCoordinate);
    }

    private boolean isCoordinateInsidePlateau(Integer xCoordinate, Integer yCoordinate) {
        return xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate <= this.xCoordinate && yCoordinate <= this.yCoordinate;
    }
}
