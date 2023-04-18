package com.problems.marsrover;

import exceptions.CustomException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Solution Approach:
 * {@link com.problems.marsrover.Plateau} - Virtual grid of size of plateau where bottom left corner coordinates are [0, 0] and top right corner coordinates are [xCoordinate, yCoordinate]
 * {@link com.problems.marsrover.Direction} - Maintain 4 directions (North, East, South, West) with its direction upon turning left or right. Also maintain change in coordinates if moved in any directions
 * {@link com.problems.marsrover.Position} -  For each rover maintain its position for xCoordinate, xCoordinate and direction. Also keep track if rover has moved out of plateau. Provide methods to perform turnLeft, turnRight and move
 * {@link com.problems.marsrover.Instructions} - Parse instructions and execute them
 * <p>
 * Assumption:
 * Each rover doesn't obstruct each other while navigating or in other words a position in plateau grid can occupy as many rovers.
 */
public class MarsRover {
    /**
     * Read input from file
     *
     * @param fileName
     * @return
     */
    public static List<String> readFile(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 1. Parse input lines
     * 2. Find each rover's final coordinates
     *
     * @param inputLines
     * @return
     */
    public static List<Position> getAllRoversFinalCoordinates(List<String> inputLines) {
        if (inputLines.isEmpty()) {
            return Collections.emptyList();
        }
        if (inputLines.size() % 2 != 1) {
            throw new CustomException("Invalid Input Lines: " + inputLines);
        }
        Plateau plateau = getPlateau(inputLines.get(0));

        List<Position> roversFinalCoordinates = new ArrayList<>();
        for (int i = 1; i < inputLines.size(); i += 2) {
            String roverPositionInput = inputLines.get(i);
            String roverInstructionsInput = inputLines.get(i + 1);
            Position roverFinalCoordinates = getRoverFinalCoordinates(plateau, roverPositionInput, roverInstructionsInput);
            roversFinalCoordinates.add(roverFinalCoordinates);
        }
        return roversFinalCoordinates;
    }

    /**
     * It will find final coordinates of a rover by using plateau and instructions
     *
     * @param plateau
     * @param roverPositionInput
     * @param roverInstructionsInput
     * @return
     */
    private static Position getRoverFinalCoordinates(Plateau plateau, String roverPositionInput, String roverInstructionsInput) {
        Position roverPosition = getPosition(plateau, roverPositionInput);
        Instructions instructions = new Instructions(roverInstructionsInput);
        instructions.execute(roverPosition);
        return roverPosition;
    }

    /**
     * It parses plateau input
     *
     * @param plateauLine
     * @return
     */
    private static Plateau getPlateau(String plateauLine) {
        Integer[] plateauInput = Arrays.stream(plateauLine.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        return new Plateau(plateauInput[0], plateauInput[1]);
    }

    /**
     * It parses rover's position input
     *
     * @param plateau
     * @param positionStr
     * @return
     */
    private static Position getPosition(Plateau plateau, String positionStr) {
        String[] roverPosition = Arrays.stream(positionStr.split(" ")).toArray(String[]::new);
        Integer roverPositionX = Integer.parseInt(roverPosition[0]);
        Integer roverPositionY = Integer.parseInt(roverPosition[1]);
        String roverPositionN = roverPosition[2];
        return new Position(plateau, roverPositionX, roverPositionY, Direction.parse(roverPositionN));
    }

    /**
     * Starter
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> inputLines = readFile("resource/testcase-01.txt");
        List<Position> solutionList = getAllRoversFinalCoordinates(inputLines);
        for (Position resultantPosition : solutionList) {
            System.out.println(resultantPosition);
        }
    }
}
