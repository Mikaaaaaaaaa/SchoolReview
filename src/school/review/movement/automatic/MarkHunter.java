package school.review.movement.automatic;

import school.review.Planet;
import school.review.algorithm.Point;
import school.review.entity.Player;
import school.review.items.Mark;

public class MarkHunter
{

    private final Player PLAYER;
    private final Planet PLANET;

    /**
     * Creates a logical execution of the task using the algorithm
     * @param player The player to create the algorithm for.
     */
    public MarkHunter(Player player)
    {
        PLAYER = player;
        PLANET = player.getLocation().getPlanet();
    }

    /**
     * This method finds the nearest mark.
     */
    public void hunt()
    {
        Point startingPosition = new Point(PLAYER.getX(), PLAYER.getY(), null);

        for (int i = 0; i < PLANET.getMarks().size(); i++)
        {
            Mark mark = PLANET.getMarks().get(i);

            PLAYER.getAutomatic().start(new Point(mark.getX(), mark.getY(), null));
            PLANET.removeObject(PLANET.getObjectsAt(PLAYER.getX(), PLAYER.getY(), Mark.class).get(0));
        }


        //PLAYER.getAutomatic().start(startingPosition);


        int x = PLAYER.getX();
        int y = PLAYER.getY();
        PLAYER.getLocation().getPlanet().addMarker(x, y);
    }

}
