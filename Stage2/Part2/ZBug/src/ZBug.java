import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a Z of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug {
    private int steps;
    //Length of Z
    private int sizeLength;

    /**
     * Constructs a box bug that traces a circle of a given radius length
     * @param lengthTemp the sizeLength
     */
    public ZBug(int lengthTemp) 
    {
        steps = 0;
        sizeLength = lengthTemp;
        //bug turn right first
        turn();
        turn();
    }

    //judge turn three times
    private boolean turnThreeTimes() 
    {
        return (steps == sizeLength);
    }

    //judge turn five times
    private boolean turnFiveTimes() 
    {
        return (steps == 2 * sizeLength + 1);
    }
    
    //judge stop move
    private boolean stopMove() 
    {
        return (steps >= 3 * sizeLength + 2 || !canMove());
    }

    /**
     * Moves to the next location of the Z
     */
    public void act() 
    {
        if (turnThreeTimes()) 
        {
            for (int i = 0; i < 3; ++i) 
            {
                turn();
            }
        } 
        else if (turnFiveTimes()) 
        {
            for (int i = 0; i < 5; ++i) 
            {
                turn();
            }
        } 
        else if (stopMove()) 
        {
            --steps;
        } 
        else if (canMove()) 
        {
            move();
        }
        ++steps;
    }
}

