import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */ 
public class MazeBug extends Bug 
{
	public Location next;    //下一个位置
	public Location last;    //上一个位置
	public boolean isEnd = false;	     //游戏是否结束
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;       //记录走了多少步
	public boolean hasShown = false;    //final message has been shown
	public Stack<Location> path = new Stack<Location>();    //当前路径
	public int directions[] = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};  //四个方向
	public int[] probility = {1, 1, 1, 1};    //四个方向的概率，初始化都为1

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * @param length the side length
	 */
	public MazeBug() 
	{
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() 
	{
		if (stepCount == 0) 
		{
			Location loc = this.getLocation();
			path.push(loc);
		}
		if (isEnd == true) 
		{	
			//to show step count when reach the goal
			if (hasShown == false) 
			{
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} 
		else 
		{
			if (canMove()) 
			{
				move();
			} 
			else 
			{
				//不能移动，调用moveBack函数
				moveBack();
				stepCount++;
			}
		}
	}

	/**
	 * Find all positions that can be move to.
	 * @param loc the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) 
	{
		Grid<Actor> gr = getGrid();
		if (gr == null) 
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();

		//查看四个方向
		for (int i = 0; i < 4; i++) 
		{
			Location nextLoc = loc.getAdjacentLocation(directions[i]);
			if (gr.isValid(nextLoc)) 
			{
				Actor actor = gr.get(nextLoc);
				//判断下个位置是否为红石头，既迷宫出口
				if (actor instanceof Rock && actor.getColor().equals(Color.RED)) 
				{
					next = nextLoc;
					ArrayList<Location> valid1 = new ArrayList<Location>();
					valid1.add(next);
					return valid1;
				}
				else if (actor == null) 
				{
					valid.add(nextLoc);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> valid = new ArrayList<Location>();
		valid = getValid(this.getLocation());
		if (valid.size() > 0) 
			return true;
		else
			return false;
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() 
	{
		Grid<Actor> gr = getGrid();
		if (gr == null) 
			return;
		
		Location loc = getLocation();
		ArrayList<Location> valid = new ArrayList<Location>();
		valid = getValid(loc);
		//当只有一个方向可以移动时
		if (valid.size() == 1) 
		{	
			//获取下个可以移动的位置
			next = valid.get(0);
			int direction = loc.getDirectionToward(next);
			//改变方向概率数组中对应的值，概率加一
			probility[direction / 90] += 1;
		} 
		//当有多个方向可以移动时，挑选概率最大的方向移动
		else 
		{
			int max_probility = 0, max_index = 0;
			int index = 0, last_index = 0;
			//找到最大概率的方向
			for (Location i : valid) 
			{
				int direction = loc.getDirectionToward(i);
				if (probility[direction / 90] > max_probility) 
				{
					max_index = (int)direction / 90;
					max_probility = probility[direction / 90];
					last_index = index;
				}
				index++;
			}
			//获取下个可以移动的位置
			next = valid.get(last_index);
			//改变方向概率数组中对应的值，概率加一
			probility[max_index] += 1;
		}

		if (gr.isValid(next)) 
		{
			Actor actor = (Actor)gr.get(next);
			//判断下个位置是否为红石头，既迷宫出口
			if (actor instanceof Rock && actor.getColor().equals(Color.RED)) 
			{
				isEnd = true;
			}
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
			stepCount++;
			path.push(next);
		}
		else 
			removeSelfFromGrid();

		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	/**
	 * Moves the bug back, putting a flower into the location it previously
	 * occupied.
	 */
	public void moveBack() 
	{
		Grid<Actor> gr = getGrid();
		if (gr == null) 
			return;
		
		Location loc = getLocation();
		//从路劲中，获取上一个移动的位置
		path.pop();
		last = path.peek();
		setDirection(getLocation().getDirectionToward(last));
		moveTo(last);
		
		//修改方向概率数组中对应的值，概率减一
		int direction = last.getDirectionToward(loc);
		probility[direction / 90] -= 1;

		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
