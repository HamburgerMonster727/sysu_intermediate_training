import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains jumper. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class JumperRunner
{
    private JumperRunner(){}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper newjumper = new Jumper();
        
        world.add(new Location(5, 5), newjumper);
        world.show();
    }
}

