package school.review.movement;

import java.util.Arrays;

public enum Direction
{
    LEFT_SIDE(180),
    RIGHT_SIDE(0),
    UP_FRONT_SIDE(270),
    DOWN_FRONT_SIDE(90);
    private int direction;

    Direction(int direction)
    {
        this.direction = direction;
    }

    public int getDirection()
    {
        return direction;
    }

    public static Direction toDirection(int value) {
        return Arrays.stream(values()).filter(direction -> direction.getDirection() == value).findFirst().orElse(null);
    }
}
