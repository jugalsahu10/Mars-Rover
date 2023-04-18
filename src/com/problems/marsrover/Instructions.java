package com.problems.marsrover;

import exceptions.CustomException;

import java.util.List;
import java.util.stream.Collectors;

public class Instructions {
    private List<Character> data;

    public Instructions(String instructions) {
        this.data = instructions
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
    }

    public void execute(Position position) {
        data.forEach(instruction -> execute(position, instruction));
    }

    private void execute(Position position, char instruction) {
        switch (instruction) {
            case Instruction.L:
                position.turnLeft();
                break;
            case Instruction.R:
                position.turnRight();
                break;
            case Instruction.M:
                position.move();
                break;
            default:
                throw new CustomException("Invalid instruction: " + instruction);
        }
    }
}
