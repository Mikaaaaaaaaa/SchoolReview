package school.review.keyboard.actions.movement;

import school.review.entity.Player;
import school.review.keyboard.GreenKey;
import school.review.keyboard.KeyAction;
import school.review.movement.Direction;

import java.util.List;

public class MoveRight implements KeyAction
{
    @Override
    public void execute(Player player)
    {
        player.getActorMovement().setDirection(Direction.RIGHT_SIDE);
        player.getActorMovement().moveForward(1);
    }

    @Override
    public List<GreenKey> getKeys()
    {
        return List.of(GreenKey.ARROW_RIGHT, GreenKey.D);
    }
}
