import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains z bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ZBugRunner
{
    private ZBugRunner(){}

    public static void main(String[] args)
    {
        int x = 4, y = 4;
        ActorWorld world = new ActorWorld();
        ZBug newZbug = new ZBug(4);
        world.add(new Location(x, y), newZbug);
        world.show();
    }
}

