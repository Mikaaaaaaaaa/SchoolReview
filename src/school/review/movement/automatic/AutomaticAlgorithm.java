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


    /**
     * Creates a new automatic algorithm for the given player.
     * @param player The player to create the algorithm for.
     */
    public AutomaticAlgorithm(Player player)
    {
        this.algorithm = new Algorithm();
        this.player = player;
        this.planet = player.getLocation().getPlanet();
    }

    /**
     * This method starts the algorithm, to find the shortest path to the given point.
     * @param end
     */
    public void start(Point end)
    {
        Point start = new Point(player.getX(), player.getY(), null);

        List<Point> route = getRoute(start, end);

        if (route == null)
        {
            System.out.println("There is no route to the destination.");
            return;
        }
        handle(route, true);
    }

    /**
     * This method returns the route from the start to the end point.
     * @param route The route to the end point.
     */
    private void handle(List<Point> route, boolean print)
    {
        for (Point point : route)
        {
            if(print)
            {
                System.out.println(point);
            }
            player.getActorMovement().move(point);
        }
    }

    /**
     * This method gets the converted route from the algorithm.
     * @return The map of the route as a 2D array.
     */
    private int[][] getMap()
    {
        return this.algorithm.convertPlanetToMap(this.planet);
    }

    /**
     * This method gets the route from the algorithm.
     * @param start The starting point.
     * @param end The ending point.
     * @return The route.
     */
    private List<Point> getRoute(Point start, Point end)
    {
        return this.algorithm.findPath(getMap(), start, end);
    }

    /**
     * This method print the map.
     */
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
