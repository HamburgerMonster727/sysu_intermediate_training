import info.gridworld.actor.Bug;

/**
 * A <code>CircleBug</code> traces out a cricle of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class CircleBug extends Bug 
{
    private int steps;
    //Radius of circle
    private int sizeRadius;

    /**
     * Constructs a box bug that traces a circle of a given radius length
     * @param radius the sizeRadius
     */
    public CircleBug(int radius) 
    {
        steps = 0;
        sizeRadius = radius;
    }
    
    /**
     * Moves to the next location of the circle
     */
    public void act() 
    {
        if (steps < sizeRadius && canMove()) 
        {
            move();
            ++steps;
        } 
        else 
        {
            turn();
            steps = 0;
        }
    }
}
