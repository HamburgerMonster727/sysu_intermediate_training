[TOC]

# Set 7

**The source code for the Critter class is in the critters directory**

- What methods are implemented in Critter?

回答：act(),  getActors(),  processActors(ArrayList<Actor> actors),  getMoveLocations(),  selectMoveLocation(ArrayList<Location> locs),  makeMove(Location loc)

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:38
public void act()
//@line:56
public ArrayList<Actor> getActors()
//@line:71
public void processActors(ArrayList<Actor> actors)
//@line:88
public ArrayList<Location> getMoveLocations()
//@line:104
public Location selectMoveLocation(ArrayList<Location> locs)
//@line:125
public void makeMove(Location loc)
```

- What are the five basic actions common to all critters when they act?

回答：getActors(),  processActors(ArrayList<Actor> actors),  getMoveLocations(),  selectMoveLocation(ArrayList<Location> locs),  makeMove(Location loc)

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:42~46
ArrayList<Actor> actors = getActors();
processActors(actors);
ArrayList<Location> moveLocs = getMoveLocations();
Location loc = selectMoveLocation(moveLocs);
makeMove(loc);
```

- Should subclasses of Critter override the getActors method? Explain.

回答：应该，Critter的子类需要重写getActors()去满足不同的需求，Critter中的getActors()只实现了默认的方法

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:56~59
public ArrayList<Actor> getActors()
{
    return getGrid().getNeighbors(getLocation());
}
```

- Describe the way that a critter could process actors.

回答：critter可以改变周围actors的颜色，可以消除其他actors，可以迫使其他actors移动

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

- What three methods must be invoked to make a critter move? Explain each of these methods.

回答：getMoveLocations()，获取critter周围可以移动的没有被占领的区域；selectMoveLocation(ArrayList<Location> locs)，得到一个空白区域的位置；makeMove(Location loc)，根据参数位置，移动到该空白位置；

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:88~91
public ArrayList<Location> getMoveLocations()
{
    return getGrid().getEmptyAdjacentLocations(getLocation());
}

//@line:104~111
public Location selectMoveLocation(ArrayList<Location> locs)
{
    int n = locs.size();
    if (n == 0)
        return getLocation();
    int r = (int) (Math.random() * n);
    return locs.get(r);
}

//@line:125~131
public void makeMove(Location loc)
{
    if (loc == null)
        removeSelfFromGrid();
    else
        moveTo(loc);
}
```

- Why is there no Critter constructor?

回答：因为Critter类继承于Actor类，编译器可以自动调用Actor类的默认构造函数Actor()来创建一个Critter实例

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:31
public class Critter extends Actor

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

# Set 8

**The source code for the ChameleonCritter class is in the critters directory**

- Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

回答：因为ChameleonCritter类重写了act()中调用的processActors和makeMove方法，所以会出现不同的结果

```java
//@file:/GridWorldCode/projects/critters/ChameleonCritter.java
//@line:36
public void processActors(ArrayList<Actor> actors)
//@line:50
public void makeMove(Location loc)
```

- Why does the makeMove method of ChameleonCritter call super.makeMove?

回答：因为ChameleonCritter类中的makeMove方法只需要在原有的基础上，先修改方向，后面的实现与Critter类中的makeMove方法是一样的，所以可以直接调用父类的makeMove方法，减少代码冗余。

```java
//@file:/GridWorldCode/projects/critters/ChameleonCritter.java
//@line:50~54
public void makeMove(Location loc)
{
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
}
```

- How would you make the ChameleonCritter drop flowers in its old location when it moves?

回答：先保存对象的旧坐标，当对象移动到新位置后，使用旧坐标的位置，生成一朵花朵

实现代码：

```java
public void makeMove(Location loc)
{
    Location oldLoc = getLocation();
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
    if (!oldLoc.equals(loc)) {
        Flower flower = new Flower(getColor());
        flower.pushSelfInGrid(getGrid(), oldLoc);
    } 
}
```

- Why doesn’t ChameleonCritter override the getActors method?

回答：因为ChameleonCritter类对getActors方法没有新的需求

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:56~59
public ArrayList<Actor> getActors()
{
    return getGrid().getNeighbors(getLocation());
}
```

- Which class contains the getLocation method?

回答：Actor类中有getLocation方法，Actor的子类都继承了这个方法

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:102~105
public Location getLocation()
{
    return location;
}
```

- How can a Critter access its own grid

回答：使用getGrid()方法

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Actor.java
//@line:92~95
public Grid<Actor> getGrid()
{
    return grid;
}
```

# Set 9

**The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.**

- Why doesn’t CrabCritter override the processActors method?

回答：因为CrabCritter对processActors方法没有新的需求

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

- Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

回答：CrabCritter寻找前方，左前方，右前方这三个方向上相邻的actor，并且只会吃掉这三个方向上相邻的actor，不会吃掉石头和Critter

```java
//@file:/GridWorldCode/projects/critters/CrabCritter.java
//@line:44~57
public ArrayList<Actor> getActors()
{
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    for (Location loc : getLocationsInDirections(dirs))
    {
        Actor a = getGrid().get(loc);
        if (a != null)
            actors.add(a);
    }

    return actors;
}
```

- Why is the getLocationsInDirections method used in CrabCritter?

回答：帮助CrabCritter寻找前方，左前方，右前方这三个方向上相邻的位置

```java
//@file:/GridWorldCode/projects/critters/CrabCritter.java
//@line:49~54
for (Location loc : getLocationsInDirections(dirs))
{
    Actor a = getGrid().get(loc);
    if (a != null)
        actors.add(a);
}
```

- If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

回答：位置为(4, 3), (4, 4), (4, 5)

```java
//@file:/GridWorldCode/projects/critters/CrabCritter.java
//@line:47~49
int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
for (Location loc : getLocationsInDirections(dirs))
```

- What are the similarities and differences between the movements of a CrabCritter and a Critter?

回答：相似点：移动中不可以改变方向，移动前选择的方向都是随机的

不同点：Critter可以移动方向为八个方向，CrabCritter只可以向左右方向移动，如果左右都不能移动，只能转向。

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:125~131
public void makeMove(Location loc)
{
    if (loc == null)
        removeSelfFromGrid();
    else
        moveTo(loc);
}

//@file:/GridWorldCode/projects/critters/CrabCritter.java
//@line:77~91
public void makeMove(Location loc)
{
    if (loc.equals(getLocation()))
    {
        double r = Math.random();
        int angle;
        if (r < 0.5)
            angle = Location.LEFT;
        else
            angle = Location.RIGHT;
        setDirection(getDirection() + angle);
    }
    else
        super.makeMove(loc);
}
```

- How does a CrabCritter determine when it turns instead of moving?

回答：当makeMove方法中的loc等于CrabCritter当前的位置时

```java
//@file:/GridWorldCode/projects/critters/CrabCritter.java
//@line:79~88
if (loc.equals(getLocation()))
{
    double r = Math.random();
    int angle;
    if (r < 0.5)
        angle = Location.LEFT;
    else
        angle = Location.RIGHT;
    setDirection(getDirection() + angle);
}
```

- Why don’t the CrabCritter objects eat each other?

回答：因为CrabCritter继承于Critter类，CrabCritter继承了Critter的processActors方法，方法中不允许吃掉岩石和Critter，CrabCritter继承了该方法，也不允许吃掉其他CrabCritter

```java
//@file:/GridWorldCode/framework/info/gridworld/actor/Gritter.java
//@line:71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

