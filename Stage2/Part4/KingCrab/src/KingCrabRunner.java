import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains KingCrab critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 4), new Bug());
        world.add(new Location(4, 3), new Rock());
        world.add(new Location(4, 5), new Rock());
        world.add(new Location(8, 4), new Flower());
        world.add(new Location(6, 5), new Flower());
        world.add(new Location(6, 3), new Flower());
        world.add(new Location(4, 4), new KingCrab());
        world.show();
    }
}
