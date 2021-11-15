import info.gridworld.actor.Bug;

/**
 * A <code> DancingBug</code> traces out a Dancing of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug {
    private int steps;
    //turn times array of Dancing
    private int[] turnTimes;
    //count how many times turn in this round
    private int count = 0;

    /**
     * Constructs a Dancing bug that traces a Dancing of a given turn times array
     * @param turnTemp the turnTimes
     */
    public DancingBug(int[] turnTemp) 
    {
        turnTimes = new int[turnTemp.length];
        System.arraycopy(turnTemp, 0, turnTimes, 0, turnTemp.length);
        steps = 0;
    }

    /**
     * Moves to the next location of the Dancing
     */
    public void act() 
    {
        int index = steps % turnTimes.length;
        if (count < turnTimes[index]) 
        {
            turn();
            ++count;
        } 
        else 
        {
            if (canMove()) 
            {
                move();
                count = 0;
                ++steps;
            } 
            else 
            {
                turn();
            }
        }
    }
}

