package school.review;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;
import school.review.entity.Player;
import school.review.items.Hill;
import school.review.items.Mark;
import school.review.items.Rock;
import school.review.movement.Direction;
import school.review.world.Location;
import school.review.world.WorldGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planet extends World
{
    private static final int CELL_SIZE = 50;

    private final WorldGenerator WORLD_GENERATOR;

    private final List<Actor> ACTORS;
    private final List<Player> PLAYERS;
    private final List<Location> CONTENT;
    private final List<Mark> MARKS;
    private final Location CENTER;

    /**
     * Is the basic building block of the world and acts as the main class
     */
    public Planet()
    {
        super(11, 11, CELL_SIZE);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Player.class, Mark.class, Rock.class, Hill.class);
        Greenfoot.setSpeed(20);


        ACTORS = new ArrayList<>();
        CONTENT = new ArrayList<>();
        MARKS = new ArrayList<>();
        PLAYERS = new ArrayList<>();

        generateContent(11, 11);

        CENTER = new Location(this, getWidth() / 2, getHeight() / 2, false, true);

        WORLD_GENERATOR = new WorldGenerator(this);
        WORLD_GENERATOR.calculateCircle(new Location(this, 4, 4, false, false), 3);
        Player player = new Player(new Location(this, 0, 1, true, true));
        this.addMarker(5, 5);
        addPlayer(player, 0, 1);
    }

    public void addPlayer(Player player, int x, int y)
    {
        this.PLAYERS.add(player);
        this.addObject(player, x, y);
    }

    public Actor getActor(int x, int y)
    {
        return ACTORS.stream().filter(actor -> actor.getWorld() != null && actor.getX() == x && actor.getY() == y).findFirst().orElse(null);
    }

    @Override
    public void addObject(Actor object, int x, int y)
    {
        if (getActor(x, y) != null)
        {
            return;
        }
        ACTORS.add(object);
        if(object instanceof Mark)
        {
            MARKS.add((Mark) object);
        }
        super.addObject(object, x, y);
    }

    @Override
    public void removeObject(Actor object)
    {
        try
        {
            super.removeObject(object);
            this.ACTORS.remove(object);
            if(object instanceof Mark)
            {
                this.MARKS.remove(object);
            }
        } catch (IllegalAccessError ex)
        {
            System.out.println("The object has already been removed.");
        }
    }

    public Location getLocation(int x, int y)
    {
        return getContent().stream().filter(location -> location.getX() == x && location.getY() == y).findFirst().orElse(null);
    }

    private void generateContent(int length, int height)
    {
        for (int x = 0; x < length; x++)
        {
            for (int y = 0; y < height; y++)
            {
                CONTENT.add(new Location(this, x, y, false, true));
            }
        }
    }

    public boolean isObjectInFront(Location location, int rotation, Direction direction, Class<? extends Actor> targetObject)
    {
        final int factor = 1;
        int x = location.getX();
        int y = location.getY();

        return switch (rotation)
                {
                    case 0 -> this.isObjectNearby(x + factor, y, targetObject); // infront: x+f , y ; right: x, y+f;
                    // behind x - f, y; left: x, y-f;
                    case 90 -> this.isObjectNearby(x, y - factor, targetObject);
                    case 180 -> this.isObjectNearby(x - factor, y, targetObject);
                    case 270 -> this.isObjectNearby(x, y + factor, targetObject);
                    default -> false;
                };
    }

    public boolean isObjectNearby(Location location, Class<? extends Actor> targetObject)
    {
        return !this.getObjectsAt(location.getX(), location.getY(), targetObject).isEmpty();
    }

    public boolean isObjectNearby(int x, int y, Class<? extends Actor> targetObject)
    {
        return !this.getObjectsAt(x, y, targetObject).isEmpty();
    }

    public boolean isObjectInRange(Location location, int range, Direction direction, Class<? extends Actor> targetObject)
    {
        boolean add = false;

        switch (direction)
        {
            case UP_FRONT_SIDE, LEFT_SIDE -> add = false;
            case DOWN_FRONT_SIDE, RIGHT_SIDE -> add = true;
        }

        boolean xValue = direction.equals(Direction.LEFT_SIDE) || direction.equals(Direction.RIGHT_SIDE);

        for (int i = 0; i < range; i++)
        {
            if (isObjectNearby(xValue ? (add ? location.getX() + i : location.getX() - i) : location.getX(), xValue ?
                    location.getY() : (add ? location.getY() + i : location.getY() - i), targetObject))
            {
                return true;
            }
        }

        return false;
    }

    public void addMarker(int x, int y) {
        this.addObject(new Mark(), x, y);
    }


    public boolean existsLocation(int x, int y)
    {
        return this.getLocation(x, y) != null;
    }

    public List<Actor> getActors()
    {
        return ACTORS;
    }

    public Location getCenter()
    {
        return CENTER;
    }

    public List<Player> getPlayers()
    {
        return PLAYERS;
    }

    public List<Location> getContent()
    {
        return CONTENT;
    }

    public boolean isWalkable(int x, int y)
    {
        return getLocation(x, y).isWalkable();
    }

    public List<Mark> getMarks()
    {
        return MARKS;
    }
}
