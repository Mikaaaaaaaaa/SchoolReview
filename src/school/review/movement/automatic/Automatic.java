package school.review.movement.automatic;

import school.review.entity.Player;
import school.review.items.Mark;
import school.review.movement.Direction;
import school.review.movement.PlayerMovement;
import school.review.world.Location;

public class Automatic
{

    private final Player PLAYER;
    private final PlayerMovement PLAYER_MOVEMENT;

    public Automatic(Player player)
    {
        this.PLAYER = player;
        this.PLAYER_MOVEMENT = player.getActorMovement();
    }

    /**
     * The goal is to find the entrance of the generated object and within it to drive
     * over the set marker.
     */
    public void start()
    {
        PLAYER_MOVEMENT.moveToLocation(0, 0);
        /*serpentineDriving();
        Location entrance = PLAYER.getLocation();
        findMark(entrance.getX(), entrance.getY(), PLAYER.getRotation());
        PLAYER.getLocation().getPlanet().addObject(new Mark(), PLAYER.getX(), PLAYER.getY());*/
    }

    private void serpentineDriving()
    {
        boolean last = true;

        while (PLAYER_MOVEMENT.goToEnd(last, false) != -2)
        {
            last = !last;
            PLAYER_MOVEMENT.setDirection(Direction.DOWN_FRONT_SIDE);
            PLAYER_MOVEMENT.moveForward(1);
        }

        bypassObject();
    }


    /**
     * This method circles the object and looks for the entrance
     */
    private void bypassObject()
    {
        while (!recognizeGap())
        {
            driveAround();
        }
    }

    /**
     * This method searches for the entrance of the object
     */
    private boolean recognizeGap()
    {
        return this.PLAYER.hillAvailable(Direction.LEFT_SIDE) && this.PLAYER.hillAvailable(Direction.RIGHT_SIDE);
    }

    /**
     * This method searches the marker
     */
    private void findMark(int x, int y, int rotation)
    {
        System.out.println("FINDING...");
        PLAYER_MOVEMENT.setSearchAround(true);
        PLAYER_MOVEMENT.setPlayerDirection(Direction.RIGHT_SIDE);
        boolean vertical = isVertical();

        while (nextRow(rotation))
        {
            searchRow(vertical, x, y);
        }
        PLAYER_MOVEMENT.setSearchAround(false);
        System.out.println("FOUND!");
        PLAYER_MOVEMENT.setPlayerDirection(Direction.DOWN_FRONT_SIDE);
        PLAYER_MOVEMENT.moveForward((isVertical() ?  Math.abs(PLAYER.getY() - y) : Math.abs(PLAYER.getX() - x)));
        check(x, y);
        PLAYER_MOVEMENT.moveToLocation(0, 0);
    }

    private void check(int x, int y)
    {
        if (PLAYER.getX() == x && PLAYER.getY() == y)
        {
            return;
        }
        if(PLAYER.hillAvailable(Direction.RIGHT_SIDE))
        {
            PLAYER_MOVEMENT.setPlayerDirection(Direction.LEFT_SIDE);
            PLAYER_MOVEMENT.moveForward(1);
            PLAYER_MOVEMENT.setPlayerDirection(Direction.RIGHT_SIDE);
            PLAYER_MOVEMENT.moveForward(1);
        } else if(PLAYER.hillAvailable(Direction.LEFT_SIDE))
        {
            PLAYER_MOVEMENT.setPlayerDirection(Direction.RIGHT_SIDE);
            PLAYER_MOVEMENT.moveForward(1);
            PLAYER_MOVEMENT.setPlayerDirection(Direction.LEFT_SIDE);
            PLAYER_MOVEMENT.moveForward(1);
        }
    }


    private boolean isVertical()
    {
        return Direction.toDirection(PLAYER.getRotation()) == Direction.UP_FRONT_SIDE
                || Direction.toDirection(PLAYER.getRotation()) == Direction.DOWN_FRONT_SIDE;
    }

    public void searchRow(boolean vertical, int x, int y)
    {
        PLAYER_MOVEMENT.goToEnd(true, vertical);
        PLAYER_MOVEMENT.goToEnd(false, vertical);

        centerLine(vertical, x, y);
    }

    private int centerLine(boolean vertical, int x, int y)
    {
        int result = 1;
        PLAYER_MOVEMENT.setPlayerDirection(Direction.DOWN_FRONT_SIDE);

        int steps = vertical ? Math.max(PLAYER.getY(), y) - Math.min(PLAYER.getY(), y) :
                Math.max(PLAYER.getX(), x) - Math.min(PLAYER.getX(), x);
        for (int i = 0; i < steps; i++)
        {
            if(PLAYER_MOVEMENT.moveForward(1) == 3)
            {
                System.out.println("JNM)(§8723438h§)($");
                result = 3;
                break;
            }
        }
        return result;
    }

    public boolean nextRow(int rotation)
    {
        PLAYER.setRotation(rotation);
        return PLAYER_MOVEMENT.moveForward(1) == 1;
    }

    private void driveAround()
    {
        while (this.PLAYER.hillAvailable(Direction.UP_FRONT_SIDE))
        {
            this.PLAYER_MOVEMENT.setPlayerDirection(Direction.LEFT_SIDE);
        }
        this.PLAYER_MOVEMENT.moveForward(1);
        while (this.PLAYER.hillAvailable(Direction.RIGHT_SIDE))
        {
            this.PLAYER_MOVEMENT.moveForward(1);
        }

        this.PLAYER_MOVEMENT.setPlayerDirection(Direction.RIGHT_SIDE);
        this.PLAYER_MOVEMENT.moveForward(1);
    }


    //UML: https://www.draw.io?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Unbenanntes%20Diagramm.drawio#R7Vpdc%2BI2FP01PHbH34ZHIOm200nbGR622ZcdYWttFSMxshwgv75XWMa2bBKHAvYm%2BwLStT6sc6Rzry6M7Pl695mjTfzAQpyMLCPcjey7kWWZnuvBl7Tsc4tvTnJDxEmoGpWGBXnGymgoa0ZCnNYaCsYSQTZ1Y8AoxYGo2RDnbFtv9p0l9Vk3KMINwyJASdP6hYQizq1jyy%2Ftv2ESxcXMpqfWt0ZFY7WSNEYh21ZM9v3InnPGRF5a7%2BY4keAVuOT9fj3x9PhiHFPRpcO3x4gv4ofkOfzjd9MJt6v7L19%2FUaM8oSRTC1YvK%2FYFApxlNMRyEGNkz7YxEXixQYF8ugXOwRaLdQI1E4pqOMwF3p18T%2FO4etg2mK2x4HtoUnQwFGBqx4xVdVvCbxaYxhXoPWVDivHoOHIJChQULm%2FAyGrByEtg1llInqAYyeLXjEObaZZGiEbphhFBGC3awbSVpi29l3iLI3xsv%2BR6W30EjSNAW9SJSAVnKzxnCeNgoYxCy9l3kiSaCSUkolANgB0M9pnkjsD%2Bn6oHaxKGcppW5ut74xLkm6%2BTb9ySfPv1A4JpOJVKU4Ja4QHWzff%2FSIA%2BuUX1sfrsbqfQy2v7GpY4bKiThiTIIeIRFq%2Ft3ybiFUjdFkQLG8cJEuSp%2FhptMKsZ%2FmYEXrAk1KsTahkaUynLeIBVr6p86QO59YFMnfIch8ZAB9aPyz5%2FIzgXVkrOBDpoxOXOjnEC6t6Uc9zA7M%2FR3B5NnSBOhZQ7Iw1iToSkeYk5DmIqre9W3HSCrI4EWdciaHI5dbP6kjenV3nTKLXtC8nb0fPdSN4KvzukSNA5gW1vemY2w%2BU7oDcG00LAC2Q0giLVNG5x1DjMY5SI9yxxOmVu3wpntkXvb5K4HRGV%2BA1qj4WiQbmUN1kp1K0ii%2F6VZDHXlZcWbv8I%2BnnyjL9ZPyeaft44PDQ7XBRufpP2ByegbVF04zb8IAV05kUJCWLMt5ikuPNN%2Bq%2Flv3glDjClKYGY8ueleoj7wB3gadFSD05X33U9lLx%2BfVdvrsvt6LpMo53R2%2FguLWJ3nHNdl36HmNzYdfk%2Ft9nL28zuc5vpkc3Z%2B6wRa916nzXTQXA1ShCNMEXgHw93J3S4TR29eJpBCPCO70sDdDr%2FOyfUqgbmi2pwyVM97niqrV6dhx6Tuf653kMfyLztqS4255BDOdfr%2B1RZzczRdF0VOimDET5onQFgwWd4KN8TKn9R%2FIBSOADSrpM7upkUdv%2BFsN8AR1MwT099nyuFnp5VvLYUDjEHZJ4At79T1cwB3cEk0ItSzHFVFFU0iAk8gO8HxFcE8zzLjpYfTRAHQF2HtM2gBdHpKojekATR1y9q5wqir18dry2IHRJYfQuiP558mvge3LKdw6fb9xlrZmPkf8tkRnwerODBcyYDxwVQJzYZPeikChw%2FihAez8MVSIJq%2BZfM%2FByUf2y17%2F8D
}
