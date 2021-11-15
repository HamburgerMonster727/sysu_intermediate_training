import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains circle bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class CircleBugRunner
{   
    private CircleBugRunner(){}
    
    public static void main(String[] args)
    {
        int x, y, radius;
        ActorWorld world = new ActorWorld();
        CircleBug newbug;
        
        x = 5;
        y = 0;
        radius = 2;
        newbug = new CircleBug(radius);
        world.add(new Location(x, y), newbug);
        
        x = 5;
        y = 2;
        radius = 1;
        newbug = new CircleBug(radius);
        world.add(new Location(x, y), newbug);
        
        world.show();
    }
}
