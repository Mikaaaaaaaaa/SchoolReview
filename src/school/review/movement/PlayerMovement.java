package school.review.movement;

import greenfoot.Greenfoot;
import school.review.algorithm.Point;
import school.review.entity.Player;
import school.review.world.Location;


public class PlayerMovement
{
    private final Player PLAYER;
    private final AllowedMovement ALLOWED_MOVEMENT;

    /**
     * This class is responsible for the mobility and freedom of an actor.
     *
     * @param player is the object that you can move freely afterwards.
     */
    public PlayerMovement(Player player)
    {
        this.PLAYER = player;
        this.ALLOWED_MOVEMENT = new AllowedMovement();
    }

    /**
     * Executes the {@link #moveForward()} method for a specified number of times in a row.
     *
     * @param steps is the number of times the method should be executed
     */
    public void moveForward(int steps)
    {
        for (int i = 0; i < steps; i++)
        {
            this.moveForward();
        }
    }

    /**
     * This method lets the player step forward if allowed.
     */
    private void moveForward()
    {
        if(!ALLOWED_MOVEMENT.isPlayerAllowedToMove(PLAYER))
        {
            return;
        }

        Location location = Location.increaseLocation(PLAYER.getLocation(), PLAYER.getRotation(), 1);
        this.PLAYER.setLocation(location.getX(), location.getY());
        Greenfoot.delay(1);
    }

    /**
     * This method rotates the player in a specific direction
     *
     * @param direction is the direction in which the player turns
     */
    public void setDirection(Direction direction)
    {
        PLAYER.setRotation(direction.getDirection());
    }

    public void setPlayerDirection(Direction direction)
    {
        switch (direction)
        {
            case RIGHT_SIDE -> PLAYER.setRotation(PLAYER.getRotation() + 90);
            case LEFT_SIDE -> PLAYER.setRotation(PLAYER.getRotation() - 90);
            case DOWN_FRONT_SIDE -> PLAYER.setRotation(PLAYER.getRotation() - 180);
        }
    }

    public void move(Point point)
    {
        PLAYER.setLocation(point.getX(), point.getY());
        Greenfoot.delay(1);
    }

}
