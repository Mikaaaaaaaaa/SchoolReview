package school.review.keyboard.actions.movement;

import school.review.entity.Player;
import school.review.keyboard.GreenKey;
import school.review.keyboard.KeyAction;
import school.review.movement.automatic.MarkHunter;

import java.util.List;

public class AutomaticRunning implements KeyAction
{
    @Override
    public void execute(Player player)
    {
        new MarkHunter(player).hunt();
    }

    @Override
    public List<GreenKey> getKeys()
    {
        return List.of(GreenKey.Q);
    }
}
