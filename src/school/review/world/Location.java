package school.review.world;

import school.review.Planet;
import school.review.items.Mark;
import school.review.movement.Direction;

public class Location
{

    private final boolean CHANGEABLE;

    private Planet planet;
    private int x, y;
    private String className;

    private boolean walkable;

    public Location(Planet planet, int x, int y, boolean changeable, boolean walkable)
    {
        this.planet = planet;
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        CHANGEABLE = changeable;
    }

    public int getX()
    {
        return x;
    }

    public Location setX(int x)
    {
        if (isChangeable())
        {
            this.x = x;
            return this;
        } else
        {
            return new Location(planet, x, y, true, walkable);
        }
    }

    public Location setClassName(String className)
    {
        this.className = className;
        return this;
    }

    public int getY()
    {
        return y;
    }

    public Location setY(int y)
    {
        if (isChangeable())
        {
            this.y = y;
            return this;
        } else
        {
            return new Location(planet, x, y, true, walkable);
        }
    }

    public Location copyY(int y)
    {
        return new Location(planet, x, y, false, walkable);
    }

    public Location copyX(int x)
    {
        return new Location(planet, x, y, false, walkable);
    }

    public Planet getPlanet()
    {
        return planet;
    }

    public String getClassName()
    {
        return className;
    }

    public static Location increaseLocation(Location location, int direction, int value)
    {
        return switch (Direction.toDirection(direction))
                {
                    case DOWN_FRONT_SIDE -> location.copyY(location.getY() + value);
                    case UP_FRONT_SIDE -> location.copyY(location.getY() - value);
                    case LEFT_SIDE -> location.copyX(location.getX() - value);
                    case RIGHT_SIDE -> location.copyX(location.getX() + value);
                };
    }

    public boolean containsMark()
    {
        return getPlanet().getObjectsAt(getX(), getY(), Mark.class).stream().anyMatch(o -> o instanceof Mark);
    }



    public boolean isChangeable()
    {
        return CHANGEABLE;
    }

    public boolean isWalkable()
    {
        return this.walkable;
    }

    public void setWalkable(boolean walkable)
    {
        this.walkable = walkable;
    }
}
