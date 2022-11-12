package school.review.movement.automatic;

import school.review.Planet;
import school.review.algorithm.Point;
import school.review.entity.Player;
import school.review.items.Mark;

public class MarkHunter
{

    private final Player PLAYER;
    private final Planet PLANET;

    public MarkHunter(Player player)
    {
        PLAYER = player;
        PLANET = player.getLocation().getPlanet();
    }

    /*
     * This method is responsible for the movement of the player.
     */
    public void hunt()
    {
        Point startingPosition = new Point(PLAYER.getX(), PLAYER.getY(), null);

        for (int i = 0; i < PLANET.getMarks().size(); i++)
        {
            Mark mark = PLANET.getMarks().get(i);

            PLAYER.getAutomatic().start(new Point(mark.getX(), mark.getY(), null));
            removeMark(PLAYER.getX(), PLAYER.getY());
        }


        PLAYER.getAutomatic().start(startingPosition);


        int x = PLAYER.getX();
        int y = PLAYER.getY();
        PLAYER.getLocation().getPlanet().addMarker(x, y);
    }

    private void removeMark(int x, int y)
    {
        PLANET.removeObject(PLANET.getObjectsAt(x, y, Mark.class).get(0));
    }

}
