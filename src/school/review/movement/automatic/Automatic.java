package school.review.movement.automatic;

import school.review.entity.Player;
import school.review.items.Mark;
import school.review.movement.Direction;
import school.review.movement.PlayerMovement;
import school.review.world.Location;

import java.util.ArrayList;
import java.util.List;

public class Automatic
{

    private final Player PLAYER;
    private final PlayerMovement PLAYER_MOVEMENT;

    private final Location START_LOCATION;

    public Automatic(Player player)
    {
        this.PLAYER = player;
        this.START_LOCATION = player.getLocation();
        this.PLAYER_MOVEMENT = player.getActorMovement();
    }

    /**
     * The goal is to find the entrance of the generated object and within it to drive
     * over the set marker.
     */
    public void start()
    {
        PLAYER_MOVEMENT.moveToLocation(0, 0);
        serpentineDriving();
        Location entrance = PLAYER.getLocation();
        findMark();
        PLAYER_MOVEMENT.setDirection(Direction.LEFT_SIDE);
        PLAYER_MOVEMENT.moveForward(1);
        PLAYER.getLocation().getPlanet().removeObject(PLAYER.getLocation().getPlanet().getActor(PLAYER.getX()-1, PLAYER.getY()));
        PLAYER_MOVEMENT.moveToLocation(entrance.getX(), entrance.getY());
        PLAYER_MOVEMENT.moveForward(2);
        PLAYER_MOVEMENT.moveToLocation(START_LOCATION.getX(), START_LOCATION.getY());
        PLAYER.getLocation().getPlanet().addObject(new Mark(), PLAYER.getX(), PLAYER.getY());

    }

    private void serpentineDriving()
    {
        boolean last = true;

        while (goToEnd(last, false) != -2)
        {
            last = !last;
            PLAYER_MOVEMENT.setDirection(Direction.DOWN_FRONT_SIDE);
            PLAYER_MOVEMENT.moveForward(1);
        }

        bypassObject();
    }

    private int goToEnd(boolean end, boolean vertical)
    {
        int max = PLAYER.getWorld().getWidth();
        int min = 0;

        while (end ? PLAYER.getX() != max : PLAYER.getX() != min)
        {
            PLAYER_MOVEMENT.setDirection(vertical ? (end ? Direction.DOWN_FRONT_SIDE : Direction.UP_FRONT_SIDE) :
                    end ? Direction.RIGHT_SIDE : Direction.LEFT_SIDE);
            int move = PLAYER_MOVEMENT.moveForward(1);

            if (move < 0)
            {
                return move;
            }
        }
        return 1;
    }

    /**
     * This method circles the object and looks for the entrance
     */
    private void bypassObject()
    {
        while (!recognizeGap())
        {
            driveAround();
        }
    }

    /**
     * This method searches for the entrance of the object
     */
    private boolean recognizeGap()
    {
        return this.PLAYER.hillAvailable(Direction.LEFT_SIDE) && this.PLAYER.hillAvailable(Direction.RIGHT_SIDE);
    }

    /**
     * This method searches the marker
     */
    private void findMark()
    {
        while (!(PLAYER.getLocation().containsMark()))
        {
            driveAround();
        }
    }


    private void driveAround()
    {
        while (this.PLAYER.hillAvailable(Direction.UP_FRONT_SIDE))
        {
            this.PLAYER.setRotation(PLAYER.getRotation() - 90);
            System.out.println("HILL");
        }
        this.PLAYER_MOVEMENT.moveForward(1);
        while (this.PLAYER.hillAvailable(Direction.RIGHT_SIDE))
        {
            this.PLAYER_MOVEMENT.moveForward(1);
        }

        this.PLAYER_MOVEMENT.setPlayerDirection(Direction.RIGHT_SIDE);
        this.PLAYER_MOVEMENT.moveForward(1);
    }

}
