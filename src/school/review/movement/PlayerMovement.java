package school.review.movement;

import greenfoot.Actor;
import greenfoot.Greenfoot;
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
    public int moveForward(int steps)
    {
        for (int i = 0; i < steps; i++)
        {
            int move = this.moveForward();
            if(move < 0)
            {
                return move;
            }
        }
        return 1;
    }

    /**
     * This method lets the player step forward if allowed.
     */
    private int moveForward()
    {
        int move = ALLOWED_MOVEMENT.isPlayerAllowedToMove(PLAYER);
        if(move < 0) {
            return move;
        }
        if(PLAYER.getLocation().containsMark())
        {
            PLAYER.sendMessage("You have found a mark!");
        }

        Location location = Location.increaseLocation(PLAYER.getLocation(), PLAYER.getRotation(), 1);
        this.PLAYER.setLocation(location.getX(), location.getY());
        Greenfoot.delay(1);
        return 1;
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

    /**
     * With this method the bot moves to the point x = 0 / y = 0
     * So searching for the object can be done more easily due to exact knowledge of the bots position.
     */
    public void moveToLocation(int x, int y)
    {
        while (PLAYER.getX() != x || PLAYER.getY() != y)
        {
            if (this.PLAYER.hillAvailable(Direction.LEFT_SIDE))
            {
                setDirection(Direction.UP_FRONT_SIDE);
                if (!this.PLAYER.hillAvailable(Direction.UP_FRONT_SIDE))
                {
                    moveForward(1);
                    continue;
                }

                if (!this.PLAYER.hillAvailable(Direction.RIGHT_SIDE))
                {

                    while (!this.PLAYER.hillAvailable(Direction.RIGHT_SIDE) && this.PLAYER.hillAvailable(Direction.UP_FRONT_SIDE))
                    {
                        setDirection(Direction.RIGHT_SIDE);
                        moveForward(1);
                    }


                    setDirection(Direction.UP_FRONT_SIDE);
                    moveForward(1);
                    continue;
                }
                System.out.println("I don't know what to do.");
                continue;
            }

            boolean moveLeft = calculateDirection(x, y);

            setDirection(moveLeft ? Direction.LEFT_SIDE : Direction.UP_FRONT_SIDE);
            moveForward(1);
        }
    }

    /**
     * If the value is false, the bot has to move up
     * If the value is true, the bot moves left
     * g(x) = m * x - y
     *
     * @param x the current position x where the bot is.
     * @param y the current position y where the bot is.
     * @return whether the formula has been giving a negative, or positive outcome.
     */
    private boolean calculateDirection(int x, int y)
    {
        int botX = this.PLAYER.getX();
        int botY = this.PLAYER.getY();

        int xDifference = x - botX;
        int yDifference = y - botY;

        return Math.abs(xDifference) > Math.abs(yDifference);
    }

    /* 0 1 2 3 4 5 6 7 8 9 x     y
     * o - - - - - - - - - -     0
     * - - - - # # - # # - -     1
     * - - # - - - - - - # -     2
     * - - # - - - - - - # -     3
     * - - - # - - # # # - -     4
     * - - - - # # - - - - -     5
     */

}
