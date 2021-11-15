import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains spiral bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class SpiralBugRunner
{
    //provide a default constructor
    private SpiralBugRunner(){}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        SpiralBug new_spiralbug = new SpiralBug(3);
        world.add(new Location(9, 9), new_spiralbug);

        world.show();
    }
}
