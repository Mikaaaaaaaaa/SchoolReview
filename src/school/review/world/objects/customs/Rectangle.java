package school.review.world.objects.customs;


import school.review.Planet;
import school.review.items.Hill;
import school.review.items.Rock;
import school.review.world.Location;
import school.review.world.objects.WorldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rectangle implements WorldObject
{

    private final List<Location> CONTENT;
    private final Random RANDOM;
    private Planet planet;
    private int width, height;
    private Location location;

    public Rectangle()
    {
        CONTENT = new ArrayList<>();
        RANDOM = new Random();
    }

    @Override
    public List<Location> getContent()
    {
        return CONTENT;
    }


    @Override
    public WorldObject generateBorder()
    {
        for (int x = getLocation().getX(); x < (getLocation().getX() + getWidth()-1); x++)
        {
            this.getPlanet().addObject(new Hill(), x, getLocation().getY());
            this.getPlanet().addObject(new Hill(), x, getHeight());
            this.getContent().add(new Location(getPlanet(), x, getLocation().getY(), false).setClassName("rock"));
            this.getContent().add(new Location(getPlanet(), x, getHeight(), false).setClassName("rock"));
        }
        for (int y = getLocation().getY(); y < (getLocation().getY() + getHeight()-1); y++)
        {
            this.getPlanet().addObject(new Hill(), getLocation().getX(), y);
            this.getPlanet().addObject(new Hill(), getWidth(), y);
            this.getContent().add(new Location(getPlanet(), getLocation().getX(), y, false).setClassName("rock"));
            this.getContent().add(new Location(getPlanet(), getWidth(), y, false).setClassName("rock"));
        }

        return this;
    }

    public Planet getPlanet()
    {
        return planet;
    }

    public Rectangle setPlanet(Planet planet)
    {
        this.planet = planet;
        return this;
    }

    @Override
    public Random getRandom()
    {
        return RANDOM;
    }

    public int getWidth()
    {
        return width;
    }

    public Rectangle setWidth(int width)
    {
        this.width = width;
        return this;
    }

    public int getHeight()
    {
        return height;
    }

    public Rectangle setHeight(int height)
    {
        this.height = height;
        return this;
    }

    public Location getLocation()
    {
        return location;
    }

    public Rectangle setLocation(Location location)
    {
        this.location = location;
        this.planet = location.getPlanet();
        return this;
    }
}
