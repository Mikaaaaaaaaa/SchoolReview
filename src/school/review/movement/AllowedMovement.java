package school.review.movement;

import school.review.entity.Player;
import school.review.items.Hill;
import school.review.world.Location;

public class AllowedMovement
{

    public boolean isPlayerAllowedToMove(Player player)
    {
        Location location = player.getLocation();
        if (location.getPlanet().isObjectInRange(location, 2,
                Direction.toDirection(player.getRotation()),
                Hill.class))
        {
            System.err.println("You must not run into any blocks!");
            return false;
        }
        if (!isDirectionInMap(location, Direction.toDirection(player.getRotation())))
        {
            System.err.println("You are not authorized to move outside the map!");
            return false;
        }
        return true;
    }

    public boolean isDirectionInMap(Location location, Direction direction)
    {
        Location temp = Location.increaseLocation(location, direction.getDirection(), 1);
        return location.getPlanet().getLocation(temp.getX(), temp.getY()) != null;
    }
}
