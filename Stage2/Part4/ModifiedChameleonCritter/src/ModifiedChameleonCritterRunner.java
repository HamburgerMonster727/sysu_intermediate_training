import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains ModifiedChameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritterRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 4), new ModifiedChameleonCritter());
        world.add(new Location(5, 8), new ModifiedChameleonCritter());
        world.show();
    }
}

