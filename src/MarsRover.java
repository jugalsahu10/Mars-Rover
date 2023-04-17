import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MarsRover {
    public static List<String> readFile(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public enum Instruction {
        L, R, M
    }

    public enum Direction {
        E("E", "S", "N", 1, 0),
        S("S", "W", "E", 0, -1),
        W("W", "N", "S", -1, 0),
        N("N", "E", "W", 0, 1);

        public final String direction;
        public final String right;
        public final String left;
        public final Integer moveX;
        public final Integer moveY;

        Direction(String direction, String rightDirection, String leftDirection, Integer moveX, Integer moveY) {
            this.direction = direction;
            this.right = rightDirection;
            this.left = leftDirection;
            this.moveX = moveX;
            this.moveY = moveY;
        }
    }

    public static class Position {
        private Integer xCoordinate;
        private Integer yCoordinate;
        private Direction direction;

        public Position(Integer xCoordinate, Integer yCoordinate, Direction direction) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            this.direction = direction;
        }

        public void turnLeft() {
            direction = Direction.valueOf(direction.left);
        }

        public void turnRight() {
            direction = Direction.valueOf(direction.right);
        }

        public void move() {
            xCoordinate = xCoordinate + direction.moveX;
            yCoordinate = yCoordinate + direction.moveY;
        }

        @Override
        public String toString() {
            return xCoordinate + " " + yCoordinate + " " + direction;
        }
    }

    public static class Instructions {
        private String instructions;

        public Instructions(String instructions) {
            this.instructions = instructions;
        }

        public Position execute(Position position) {
            for (int i = 0; i < instructions.length(); i++) {
                char instruction = instructions.charAt(i);

                executeSingle(position, instruction);
            }
            return position;
        }

        private void executeSingle(Position position, char instruction) {
            switch (instruction) {
                case 'L':
                    position.turnLeft();
                    break;
                case 'R':
                    position.turnRight();
                    break;
                case 'M':
                    position.move();
                    break;
                default:
                    throw new RuntimeException("Invalid instruction" + instruction);
            }
        }
    }

    private void printPosition(Position position) {
        System.out.println(position);
    }

    public static List<Position> getSolution(List<String> inputLines) {
        if (inputLines.size() < 1) {
            return Collections.EMPTY_LIST;
        }
        if (inputLines.size() % 2 != 1) {
            throw new RuntimeException("Invalid Input Lines");
        }
        // plateau
        Integer[] plateau = Arrays.stream(inputLines.get(0).split(" ")).map(s -> Integer.parseInt(s)).toArray(Integer[]::new);

        Integer plateauX = plateau[0];
        Integer plateauY = plateau[1];

        List<Position> roversPositions = new ArrayList<>();
        for (int i = 1; i < inputLines.size(); i += 2) {
            Position resultantPosition = getResultantPosition(inputLines.get(i), inputLines.get(i + 1));
            roversPositions.add(resultantPosition);
        }
        return roversPositions;
    }

    private static Position getResultantPosition(String positionStr, String instructionsLine) {
        Position position = getPosition(positionStr);
        Instructions instructions = new Instructions(instructionsLine);
        Position resultantPosition = instructions.execute(position);
        return resultantPosition;
    }

    private static Position getPosition(String positionStr) {
        String[] roverPosition = Arrays.stream(positionStr.split(" ")).toArray(String[]::new);
        Integer roverPositionX = Integer.parseInt(roverPosition[0]);
        Integer roverPositionY = Integer.parseInt(roverPosition[1]);
        String roverPositionN = roverPosition[2];
        Position position = new Position(roverPositionX, roverPositionY, Direction.valueOf(roverPositionN));
        return position;
    }

    public static void main(String[] args) {
        List<String> inputLines = readFile("resource/testcase-01.txt");
        List<Position> solutionList = getSolution(inputLines);
        for (Position resultantPosition : solutionList) {
            System.out.println(resultantPosition);
        }
    }
}
