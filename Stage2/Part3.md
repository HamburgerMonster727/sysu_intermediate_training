[TOC]

# Set 3

Assume the following statements when answering the following questions.

```
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```

- How would you access the row value for loc1? 

回答：使用Location中的getRow()函数

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Location.java
//@line:110~113
public int getRow()
{
    return row;
}
```

- What is the value of b after the following statement is executed?

```
boolean b = loc1.equals(loc2);
```

回答：b = false，因为loc1与loc2不相等

```java
//@file:/GridWorld/framework/info/gridworld/grid/Location.java
//@line:205~212
public boolean equals(Object other)
{
    if (!(other instanceof Location))
        return false;

    Location otherLoc = (Location) other;
    return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
}
```

- What is the value of loc3 after the following statement is executed?

```
Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
```

回答：lo3变为(4,4)

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Location.java
//@line:130~169
public Location getAdjacentLocation(int direction)
{
    // reduce mod 360 and round to closest multiple of 45
    int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
    if (adjustedDirection < 0)
        adjustedDirection += FULL_CIRCLE;

    adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
    int dc = 0;
    int dr = 0;
    if (adjustedDirection == EAST)
        dc = 1;
    else if (adjustedDirection == SOUTHEAST)
    {
        dc = 1;
        dr = 1;
    }
    else if (adjustedDirection == SOUTH)
        dr = 1;
    else if (adjustedDirection == SOUTHWEST)
    {
        dc = -1;
        dr = 1;
    }
    else if (adjustedDirection == WEST)
        dc = -1;
    else if (adjustedDirection == NORTHWEST)
    {
        dc = -1;
        dr = -1;
    }
    else if (adjustedDirection == NORTH)
        dr = -1;
    else if (adjustedDirection == NORTHEAST)
    {
        dc = 1;
        dr = -1;
    }
    return new Location(getRow() + dr, getCol() + dc);
}
```

- What is the value of dir after the following statement is executed?

```
int dir = loc1.getDirectionToward(new Location(6, 5);
```

回答：dir的值为135，SOUTHEAST方向

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Location.java
//@line:178~195
public int getDirectionToward(Location target)
{
    int dx = target.getCol() - getCol();
    int dy = target.getRow() - getRow();
    // y axis points opposite to mathematical orientation
    int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

    // mathematical angle is counterclockwise from x-axis,
    // compass angle is clockwise from y-axis
    int compassAngle = RIGHT - angle;
    // prepare for truncating division by 45 degrees
    compassAngle += HALF_RIGHT / 2;
    // wrap negative angles
    if (compassAngle < 0)
        compassAngle += FULL_CIRCLE;
    // round to nearest multiple of 45
    return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
}
```

- How does the getAdjacentLocation method know which adjacent location to return?

回答：根据getAdjacentLocation函数中的参数direction，计算获得给定方向的相邻位置

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Location.java
//@line:130~169
public Location getAdjacentLocation(int direction)
{
    // reduce mod 360 and round to closest multiple of 45
    int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
    if (adjustedDirection < 0)
        adjustedDirection += FULL_CIRCLE;

    adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
    int dc = 0;
    int dr = 0;
    if (adjustedDirection == EAST)
        dc = 1;
    else if (adjustedDirection == SOUTHEAST)
    {
        dc = 1;
        dr = 1;
    }
    else if (adjustedDirection == SOUTH)
        dr = 1;
    else if (adjustedDirection == SOUTHWEST)
    {
        dc = -1;
        dr = 1;
    }
    else if (adjustedDirection == WEST)
        dc = -1;
    else if (adjustedDirection == NORTHWEST)
    {
        dc = -1;
        dr = -1;
    }
    else if (adjustedDirection == NORTH)
        dr = -1;
    else if (adjustedDirection == NORTHEAST)
    {
        dc = 1;
        dr = -1;
    }
    return new Location(getRow() + dr, getCol() + dc);
}
```

# Set 4

- How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

回答：通过getOccupiedLocations()函数可以获取有多少对象在网格中

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Grid.java
//@line: 85
ArrayList<Location> getOccupiedLocations();
```

通过num = grid.getNumRows() * grid.getNumCols() - grid.getOccupiedLocations().size() 计算可以获取有多少空格子在限定的网格中

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:48~51
public int getNumRows()
{
    return occupantArray.length;
}
//@line:53~58
public int getNumCols()
{
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
}
//@line:66~83
public ArrayList<Location> getOccupiedLocations()
{
    ArrayList<Location> theLocations = new ArrayList<Location>();

    // Look at all grid locations.
    for (int r = 0; r < getNumRows(); r++)
    {
        for (int c = 0; c < getNumCols(); c++)
        {
            // If there's an object at this location, put it in the array.
            Location loc = new Location(r, c);
            if (get(loc) != null)
                theLocations.add(loc);
        }
    }

    return theLocations;
}

```

- How can you check if location (10,10) is in a grid?

回答：通过isValid()函数，isValid(new Location(10,10))

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:60~64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}

//@file:/GridWorldCode/framework/info/gridworld/grid/UnBoundedGrid.java
//@line:53~56
public boolean isValid(Location loc)
{
    return true;
}
```

- Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

回答：因为Grid类只是一个接口，函数需要由其他类来实现，例如AbstractGrid类。BounderGrid类，UnboundedGrid类继承AbstractGrid类，也可以实现函数。

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/AbstractGrid.java
//@line:26
public abstract class AbstractGrid<E> implements Grid<E>
//@file:/GridWorldCode/framework/info/gridworld/grid/BoundedGrid.java
//@line:29
public class BoundedGrid<E> extends AbstractGrid<E>
//@file:/GridWorldCode/framework/info/gridworld/grid/UnBoundedGrid.java
//@line:31
public class UnboundedGrid<E> extends AbstractGrid<E>
```

- All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

回答：我认为ArrayList类型比array类型好。因为每次函数返回的数组，你不知道数组里面的个数，使用ArrayList可以动态添加或删除对象，数组容量可以动态变化，可以减少浪费内存空间或者数组溢出等问题。

```java
//@file:/GridWorldCode/framework/info/gridworld/grid/Grid.java
//@line:85
ArrayList<Location> getOccupiedLocations();
//@line:96
ArrayList<Location> getValidAdjacentLocations(Location loc);
//@line:107
ArrayList<Location> getEmptyAdjacentLocations(Location loc);
//@line:118
ArrayList<Location> getOccupiedAdjacentLocations(Location loc);
//@line:129
ArrayList<E> getNeighbors(Location loc);
```

# Set 5

- Name three properties of every actor.

回答：location,direction,color

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:32~34
private Location location;
private int direction;
private Color color;
```

- When an actor is constructed, what is its direction and color?

回答：方向为NORTH，颜色为BLUE

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:39~45
public Actor()
{
    color = Color.BLUE;
    direction = Location.NORTH;
    grid = null;
    location = null;
}
```

- Why do you think that the Actor class was created as a class instead of an interface?

回答：Actor拥有自己的属性和方法，而接口不能声明属性和实现方法

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:29
public class Actor
```

- Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

回答：不能，会丢出错误 IllegalStateException()

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:117~119
if (grid != null)
        throw new IllegalStateException(
                "This actor is already contained in a grid.");
```

不能，会丢出错误 IllegalStateException()

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:135~137
if (grid == null)
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
```

可以，不会报错

- How can an actor turn 90 degrees to the right?

回答：使用setDirection(int newDirection)方法和getLocation()方法，setDirection(getDirection() + Location.RIGHT)

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:80~85
public void setDirection(int newDirection)
{
    direction = newDirection % Location.FULL_CIRCLE;
    if (direction < 0)
        direction += Location.FULL_CIRCLE;
}
//@line:102~105
public Location getLocation()
{
    return location;
}
```

# Set 6

- Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

代码：

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:96~99
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
if (!gr.isValid(next))
    return false;
```

- Which statement(s) in the canMove method determines that a bug will not walk into a rock?

代码：

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:100~101
Actor neighbor = gr.get(next);
return (neighbor == null) || (neighbor instanceof Flower);
```

- Which methods of the Grid interface are invoked by the canMove method and why?

回答：Grid的isValid()和get()方法，确保bug移动的下一个位置是存在的且合法的

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:96~100
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
if (!gr.isValid(next))
    return false;
Actor neighbor = gr.get(next);

//@file:/GridWorldCode/framework/info/gridworld/grid/Grid.java
//@line:50
boolean isValid(Location loc);
//@line:79
E get(Location loc);
```

- Which method of the Location class is invoked by the canMove method and why?

回答：Location的getAdjacentLocation()方法，获取某个方向的相邻位置

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:97
Location next = loc.getAdjacentLocation(getDirection());

//@file:/GridWorldCode/framework/info/gridworld/grid/Location.java
//@line:130
public Location getAdjacentLocation(int direction)
```

- Which methods inherited from the Actor class are invoked in the canMove method?

回答：Actor的getLocation(), getDirection(), getGrid()方法，获取actor的属性

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:96
Location loc = getLocation();

//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:102~105
public Location getLocation()
{
    return location;
}
```

- What happens in the move method when the location immediately in front of the bug is out of the grid?

回答：会调用removeSelfFromGrid()方法，把自己从grid中除去

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:78~81
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
```

- Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

回答：需要，插入新的花朵的时候，需要旧的location提供位置

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:76~83
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
if (gr.isValid(next))
	moveTo(next);
else
	removeSelfFromGrid();
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

- Why do you think the flowers that are dropped by a bug have the same color as the bug?

回答：因为生成的新的花朵调用了当前bug的getColor()方法，获得了当前bug的颜色，所以花朵颜色和bug颜色一样

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:82
Flower flower = new Flower(getColor());
```

- When a bug removes itself from the grid, will it place a flower into its previous location?

回答：如果bug调用了actor中的removeSelfFromGrid方法，不会留下花朵

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:133~146
public void removeSelfFromGrid()
{
    if (grid == null)
        throw new IllegalStateException(
                "This actor is not contained in a grid.");
    if (grid.get(location) != this)
        throw new IllegalStateException(
                "The grid contains a different actor at location "
                        + location + ".");

    grid.remove(location);
    grid = null;
    location = null;
}
```

回答：如果bug调用了move方法，会留下花朵

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:71~84
public void move()
{
    Grid<Actor> gr = getGrid();
    if (gr == null)
        return;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
}
```

- Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

代码：

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:76~83
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

- If a bug needs to turn 180 degrees, how many times should it call the turn method?

回答：turn调用一次，只旋转45度，要旋转180度，需要调用4次turn方法

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Bug.java
//@line:62~65
public void turn()
{
    setDirection(getDirection() + Location.HALF_RIGHT);
}
```

