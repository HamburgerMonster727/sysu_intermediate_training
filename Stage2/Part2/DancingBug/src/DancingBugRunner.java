import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains Dancing bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner
{
    private DancingBugRunner(){}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] turnTimes = new int[4] ;
        for (int i = 0; i < 4; i++) 
        {
            turnTimes[i] = i + 1;
        }
        DancingBug newDancingbug = new DancingBug(turnTimes);
        world.add(new Location(6, 6), newDancingbug);
        world.show();
    }
}

