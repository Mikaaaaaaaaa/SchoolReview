package school.review.keyboard.actions.information;

import school.review.entity.Player;
import school.review.items.Rock;
import school.review.keyboard.GreenKey;
import school.review.keyboard.KeyAction;

import java.util.List;

public class PlayerInformation implements KeyAction
{
    @Override
    public void execute(Player player)
    {
        player.sendMessage("_______________________");
        player.sendMessage("blocks nearby: " + player.getLocation().getPlanet().isObjectNearby(player.getLocation().getX(),
                player.getLocation().getY() + 1,
                Rock.class));
        player.sendMessage("X: " + player.getX() + " Y:" + player.getY());
        player.sendMessage("_______________________");
    }

    @Override
    public List<GreenKey> getKeys()
    {
        return List.of(GreenKey.I);
    }
}
