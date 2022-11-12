package school.review.world.objects.customs;

import school.review.Planet;
import school.review.items.Hill;
import school.review.world.Location;
import school.review.world.objects.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Circle implements WorldObject
{
    private final List<Location> CONTENT;
    private final Random RANDOM;
    private Location start;
    private Planet planet;
    private int radius;


    public Circle()
    {
        this.CONTENT = new ArrayList<>();
        this.RANDOM = new Random();
    }

    @Override
    public List<Location> getContent()
    {
        return this.CONTENT;
    }

    @Override
    public WorldObject generateBorder()
    {
        int d = -getRadius();
        int x = getRadius();
        int y = 0;

        while (y <= x)
        {
            addLocation(x, y, true, true, false);
            addLocation(x, y, false, false, false);
            addLocation(x, y, false, true, false);
            addLocation(x, y, true, false, false);

            addLocation(x, y, true, true, true);
            addLocation(x, y, false, false, true);
            addLocation(x, y, false, true, true);
            addLocation(x, y, true, false, true);

            d += 2 * y + 1;
            y += 1;

            if (d > 0)
            {
                d -= 2 * x + 2;
                x -= 1;
            }
        }
        return this;
    }

    private void addLocation(int x, int y, boolean xAdd, boolean yAdd, boolean reverse)
    {
        addLocation(xAdd ? getStart().getX() + (reverse ? y : x) : getStart().getX() - (reverse ? y : x), yAdd ?
                getStart().getY() + (reverse ? x : y) :
                getStart().getY() - (reverse ? x : y));
    }

    private boolean addLocation(int x, int y)
    {
        if (containsLocation(x, y))
            return false;
        CONTENT.add(new Location(getPlanet(), x, y, false, false).setClassName(getStart().getClassName()));
        planet.getLocation(x, y).setWalkable(false);
        planet.addObject(new Hill(), x, y);
        return true;
    }

    private boolean containsLocation(int x, int y)
    {
        return CONTENT.stream().filter(location -> location.getX() == x && location.getY() == y).findFirst().orElse(null) != null;
    }

    public int getRadius()
    {
        return radius;
    }

    public Circle setRadius(int radius)
    {
        this.radius = radius;
        return this;
    }

    public Location getStart()
    {
        return start;
    }

    public Circle setStart(Location start)
    {
        this.start = start;
        return this;
    }

    @Override
    public Planet getPlanet()
    {
        return planet;
    }

    public Circle setPlanet(Planet planet)
    {
        this.planet = planet;
        return this;
    }

    @Override
    public Random getRandom()
    {
        return RANDOM;
    }

}
