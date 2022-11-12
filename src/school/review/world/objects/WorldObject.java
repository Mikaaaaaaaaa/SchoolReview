package school.review.world.objects;

import greenfoot.Actor;
import school.review.Planet;
import school.review.items.Hill;
import school.review.items.Mark;
import school.review.items.Rock;
import school.review.text.Logger;
import school.review.world.Location;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public interface WorldObject
{


    //LINK: https://as2.ftcdn.net/v2/jpg/02/02/59/51/1000_F_202595140_0h7HJmJ1EY4spPzhH13A4v9VXzy8GUO6.jpg


    List<Location> getContent();

    WorldObject generateBorder();

    default WorldObject addGap()
    {
        try
        {


            List<Location> rocks = getContent().stream().filter(location -> getContent().stream().filter(allLocations ->
                            allLocations.getX() == location.getX() && allLocations.getY() == location.getY()).findAny().orElse(null).getClassName()
                    .equals("circle")).collect(Collectors.toList());

            Location location = rocks.get(getRandom().nextInt(rocks.size()));

            Actor actor = getPlanet().getActor(location.getX(), location.getY());


            if (actor != null && actor instanceof Hill)
            {
                getPlanet().getLocation(actor.getX(), actor.getY()).setWalkable(true);
                getPlanet().removeObject(actor);
            } else
            {
                Logger.error("ERROR: There could be no gap");
                getPlanet().removeObject(actor);
            }
        } catch (IllegalArgumentException exception)
        {
            Logger.error("ERROR: You must first generate the object before adding a gap.");
        } catch (NullPointerException exception)
        {
            exception.printStackTrace();
            Logger.error("ERROR: You must first set the planet before adding a gap.");
        }

        return this;
    }

    default WorldObject addMark()
    {
        try
        {
            Location location = getContent().get(getRandom().nextInt(getContent().size()));
            getPlanet().addObject(new Mark(), location.getX(), location.getY());
        } catch (IllegalArgumentException exception)
        {
            Logger.error("ERROR: You must first generate the object before adding a mark.");
        } catch (NullPointerException exception)
        {
            Logger.error("ERROR: You must first set the planet before adding a mark.");
        }
        return this;
    }

    Planet getPlanet();

    Random getRandom();

}
