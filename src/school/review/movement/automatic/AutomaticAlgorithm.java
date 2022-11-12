package school.review.movement.automatic;

import school.review.Planet;
import school.review.algorithm.Point;
import school.review.entity.Player;

import java.util.List;

public class AutomaticAlgorithm
{

    private final Algorithm algorithm;

    private final Player player;
    private Planet planet;


    public AutomaticAlgorithm(Player player)
    {
        this.algorithm = new Algorithm();
        this.player = player;
        this.planet = player.getLocation().getPlanet();
    }

    public void start(Point end)
    {
        Point start = new Point(player.getX(), player.getY(), null);

        List<Point> route = getRoute(start, end);

        if (route == null)
        {
            System.out.println("No route found");
            return;
        }

        for (Point point : route)
        {
            player.getActorMovement().move(point);
            System.out.println(point);
        }
    }

    private int[][] getMap()
    {
        return this.algorithm.convertPlanetToMap(this.planet);
    }

    private List<Point> getRoute(Point start, Point end)
    {
        return this.algorithm.findPath(getMap(), start, end);
    }

    private void printMap()
    {
        for (int[] ints : this.getMap())
        {
            for (int anInt : ints)
            {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
