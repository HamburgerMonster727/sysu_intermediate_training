import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 * A <code>OccupantInCol</code> is a helper class of SparseBoundedGrid <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class OccupantInCol 
{
    private Object occupant;
    private int col;

    /**
     * Constructs an Occupant with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param obj the occupant
     * @param colSize the col
     */
    OccupantInCol(Object obj, int colSize) 
    {
        this.occupant = obj;
        col = colSize;
    }

    public void setOccupant(Object obj) 
    {
        occupant = obj;
    }

    public Object getOccupant() 
    {
        return occupant;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public int getCol() 
    {
        return col;
    }
}
