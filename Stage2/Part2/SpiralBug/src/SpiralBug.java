import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces out a spiral of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class SpiralBug extends Bug 
{
    private int steps;
    //Length of Sprial
    private int sideLength;

    /**
     * Constructs a spiral bug that traces a spiral of a given length
     * * @param length the sizeLength
     */
    public SpiralBug(int length) 
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the spiral
     */
    public void act() 
    {
        if (steps < sideLength && canMove()) 
        {
            move();
            ++steps;
        } 
        else 
        {
            turn();
            turn();
            steps = 0;
            ++sideLength;
        }
    }
}

