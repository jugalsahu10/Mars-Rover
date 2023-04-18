package com.problems.marsrover;

import exceptions.CustomException;

import java.util.Arrays;

/**
 * Maintains 4 directions (North, East, South, West) with its direction upon turning left or right. Also maintains change in coordinates if moved in any directions
 */
public enum Direction {
    EAST("E", "S", "N", 1, 0),
    SOUTH("S", "W", "E", 0, -1),
    WEST("W", "N", "S", -1, 0),
    NORTH("N", "E", "W", 0, 1);

    public final String code;
    public final String right;
    public final String left;
    public final Integer moveX;
    public final Integer moveY;

    Direction(String direction, String rightDirection, String leftDirection, Integer moveX, Integer moveY) {
        this.code = direction;
        this.right = rightDirection;
        this.left = leftDirection;
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public static Direction parse(String code) {
        return Arrays.stream(values())
                .filter(direction -> direction.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new CustomException("Invalid Direction Code: " + code));
    }
}
