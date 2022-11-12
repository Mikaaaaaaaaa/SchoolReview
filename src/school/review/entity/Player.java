package school.review.entity;

import greenfoot.Actor;
import greenfoot.World;
import school.review.items.Hill;
import school.review.keyboard.Keyboard;
import school.review.movement.Direction;
import school.review.movement.PlayerMovement;
import school.review.movement.automatic.AutomaticAlgorithm;
import school.review.world.Location;

public class Player extends Actor
{
    private final PlayerMovement ACTOR_MOVEMENT;
    private final Keyboard KEY_BOARD;

    private final Location LOCATION;

    private final AutomaticAlgorithm AUTOMATIC;

    /**
     * When this constructor is called, an object class Player is created and initialized.
     * This creates the movement and the possibility to send pop-up messages.
     */
    public Player(Location location)
    {
        this.ACTOR_MOVEMENT = new PlayerMovement(this);
        this.KEY_BOARD = new Keyboard(this);
        this.LOCATION = location;
        this.AUTOMATIC = new AutomaticAlgorithm(this);
    }

    public void act()
    {
        this.KEY_BOARD.startListening();
    }

    public void sendMessage(String message)
    {
        System.out.println(message);
    }

    public boolean hillAvailable(Direction direction)
    {
        int rot = getRotation();

        if (getOffset(direction, rot, 0, 270, 90, 1, 0))
        {
            return true;
        }

        if (getOffset(direction, rot, 180, 90, 270, -1, 0))
        {
            return true;
        }

        if (getOffset(direction, rot, 90, 0, 180, 0, 1))
        {
            return true;
        }

        if (getOffset(direction, rot, 270, 180, 0, 0, -1))
        {
            return true;
        }

        if (direction.equals(Direction.DOWN_FRONT_SIDE))
        {
            System.err.println("Befehl nicht korrekt!");
        }

        return false;
    }

    private boolean getOffset(Direction direction, int rotation, int checkRot1, int checkRot2, int checkRot3, int dx, int dy)
    {
        if (direction.equals(Direction.UP_FRONT_SIDE) && rotation == checkRot1 || direction.equals(Direction.RIGHT_SIDE) && rotation == checkRot2 || direction.equals(Direction.LEFT_SIDE) && rotation == checkRot3)
        {
            if (getOneObjectAtOffset(dx, dy, Hill.class) != null && ((Hill) getOneObjectAtOffset(dx, dy, Hill.class)).getSlope() > 30)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method adds the player to the world
     *
     * @param world The world the object was added to.
     */
    protected void addedToWorld(World world)
    {
        this.setImage("images/rover.png");
        if (this.getY() == 0)
        {
            this.setLocation(getX(), 1);
        }
    }

    @Override
    public void setLocation(int x, int y)
    {
        LOCATION.setX(x);
        LOCATION.setY(y);
        super.setLocation(x, y);
    }

    public Location getLocation()
    {
        return LOCATION;
    }

    public PlayerMovement getActorMovement()
    {
        return ACTOR_MOVEMENT;
    }

    public AutomaticAlgorithm getAutomatic()
    {
        return AUTOMATIC;
    }
}
