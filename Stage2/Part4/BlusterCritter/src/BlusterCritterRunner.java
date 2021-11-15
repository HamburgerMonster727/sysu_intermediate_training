import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains BlusterCritter. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritterRunner
{	
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        BlusterCritter Bluster1 = new BlusterCritter(100);
        Bluster1.setColor(Color.ORANGE);
        BlusterCritter Bluster2 = new BlusterCritter(2);
        Bluster2.setColor(Color.GREEN);
        world.add(new Location(0, 0), new Bug());
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 4), Bluster1);
        world.add(new Location(5, 8), Bluster2);
        world.show();
    }
}

