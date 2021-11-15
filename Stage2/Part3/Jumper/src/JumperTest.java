import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

public class JumperTest {
    //test act function
    @Test
    public void actTest() 
    {
        final int x = 6;
        final int y = 6;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        jumper.act();

        assertEquals(x-2, jumper.getLocation().getRow());
        assertEquals(y, jumper.getLocation().getCol());

        Rock rock = new Rock();
        world.add(new Location(x-2-2, y), rock);
        jumper.act();

        assertEquals(x-2, jumper.getLocation().getRow());
        assertEquals(y, jumper.getLocation().getCol());
        assertEquals(Location.NORTHEAST, jumper.getDirection());
    }

    //test turn function 
    @Test
    public void turnTest() 
    {
        final int x = 6;
        final int y = 6;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        jumper.turn();

        assertEquals(Location.NORTHEAST, jumper.getDirection());
    }

    //test moveTest function  
    @Test
    public void moveTest() 
    {
        final int x = 6;
        final int y = 6;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        jumper.move();

        assertEquals(x-2, jumper.getLocation().getRow());
        assertEquals(y, jumper.getLocation().getCol());
    }
    
    //test canMove function 
    @Test
    public void canMoveTest() 
    {
        final int x = 6;
        final int y = 6;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);

        assertTrue(jumper.canMove());

        Rock rock = new Rock();
        world.add(new Location(x-2, y), rock);

        assertFalse(jumper.canMove());

        Jumper jumper1 = new Jumper();
        world.add(new Location(x+2, y), jumper1);

        assertFalse(jumper1.canMove());
    }
}

