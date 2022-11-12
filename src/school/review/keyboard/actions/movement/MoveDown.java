package school.review.keyboard.actions.movement;

import school.review.entity.Player;
import school.review.keyboard.GreenKey;
import school.review.keyboard.KeyAction;
import school.review.movement.Direction;

import java.util.List;

public class MoveDown implements KeyAction
{
    @Override
    public void execute(Player player)
    {
        player.getActorMovement().setDirection(Direction.DOWN_FRONT_SIDE);
        player.getActorMovement().moveForward(1);
    }

    @Override
    public List<GreenKey> getKeys()
    {
        return List.of(GreenKey.ARROW_DOWN, GreenKey.S);
    }
}
