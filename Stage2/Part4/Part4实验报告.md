# Part4实验报告

[TOC]

## ModifiedChameleonCritter

### 要求

Modify the processActors method in ChameleonCritter so that if the list of actors to process is empty, the color of the ChameleonCritter will darken (like a flower).

### 实现

ModifiedChameleonCritter继承Critter，重写processActors，当critter周围没有actor时，critter的颜色要不断变暗，对critter的RGB进行修改

```java
public void processActors(ArrayList<Actor> actors)
{
    int n = actors.size();
    if (n == 0) 
    {
        Color color = getColor();
        int red = (color.getRed() - 15) > 0 ? (color.getRed() - 15) : 0;
        int green = (color.getGreen() - 15) > 0 ? (color.getGreen() - 15) : 0;
        int blue = (color.getBlue() - 15) > 0 ? (color.getBlue() - 15) : 0;
        setColor(new Color(red, green, blue));
    }
    else
    {
        int r = (int) (Math.random() * n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }
}
```

生成两个critter，两个不同颜色的rock，观察critter的颜色是否会变暗

可以看到，critter的RGB从0，0，255变为0，0，135，颜色变暗了

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(1).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(2).png)

## ChameleonKid

### 要求

Create a class called ChameleonKid that extends ChameleonCritter as modified in exercise 1. A ChameleonKid changes its color to the color of one of the actors immediately in front or behind. If there is no actor in either of these locations, then the ChameleonKid darkens like the modified ChameleonCritter.

### 实现

ChameleonKid继承ModifiedChameleonCritter，重写getActors，只有在critter前后方的actor才被get

```java
public ArrayList<Actor> getActors()
{
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
    for (Location loc : getLocationsInDirections(dirs))
    {
        Actor a = getGrid().get(loc);
        if (a != null) 
            actors.add(a);
    }
    return actors;
}
```

可以看到，当critter前方或者后方有rock，critter的颜色会发生改变，周围没有actor，颜色也会变暗

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(3).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(4).png)

## RockHound

### 要求

Create a class called RockHound that extends Critter. A RockHound gets the actors to be processed in the same way as a Critter. It removes any rocks in that list from the grid. A RockHound moves like a Critter.

### 实现

RockHound继承Critter，重写processActors，当附近的actor为rock时，从grid中去除rock

```java
public void processActors(ArrayList<Actor> actors) 
{
    for (Actor act : actors) 
    {
        if (act instanceof Rock) 
            act.removeSelfFromGrid();
    }
}
```

可以看到，生成的众多rock，运行一段时间后，都被critter消除了

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(5).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(6).png)

## BlusterCritter

### 要求

Create a class BlusterCritter that extends Critter. A BlusterCritter looks at all of the neighbors within two steps of its current location. (For a BlusterCritter not near an edge, this includes 24 locations). It counts the number of critters in those locations. If there are fewer than c critters, the BlusterCritter's color gets brighter (color values increase). If there are c or more critters, the BlusterCritter's color darkens (color values decrease). Here, c is a value that indicates the courage of the critter. It should be set in the constructor.

### 实现

BlusterCritter继承Critter ，添加属性courage，表示critter的勇气值

```java
private int courage;

public BlusterCritter(int courageSize) {
    this.courage = courageSize;
}
```

重写getActors，四周两格内的actor都会被get

```java
public ArrayList<Actor> getActors()
{
    ArrayList<Actor> actors = new ArrayList<Actor>();
    Grid<Actor> grid = getGrid();
    if (grid == null) 
    {
        return actors;
    }

    int row = getLocation().getRow();
    int col = getLocation().getCol();
    for (int i = col - 2;  i <= col + 2; i++) 
    {
        for (int j = row - 2; j <= row + 2; j++) 
        {
            if (!(i == col && j == row)) 
            {
                Location location = new Location(j, i);
                if (grid.isValid(location)) 
                {
                    Actor actor = grid.get(location);
                    if (actor != null) 
                        actors.add(actor);
                }
            }
        }
    }
    return actors;
}
```

重写processActors，当actor的数量大于勇气值时，critter变暗，反之变亮

```java
public void processActors(ArrayList<Actor> actors)
{
    if (actors.size() < courage) 
    {
        Color color = getColor();
        int red = (color.getRed() + 15) < 255 ? (color.getRed() + 15) : 255;
        int green = (color.getGreen() + 15) < 255 ? (color.getGreen() + 15) : 255;
        int blue = (color.getBlue() + 15) < 255 ? (color.getBlue() + 15) : 255;
        setColor(new Color(red, green, blue));
    } 
    else 
    {
        Color color = getColor();
        int red = (color.getRed() - 15) > 0 ? (color.getRed() - 15) : 0;
        int green = (color.getGreen() - 15) > 0 ? (color.getGreen() - 15) : 0;
        int blue = (color.getBlue() - 15) > 0 ? (color.getBlue() - 15) : 0;
        setColor(new Color(red, green, blue));
    }
}
```

生成两个critter，一个勇气值为100，一个勇气值为2，可以看到一个颜色逐渐变暗，RGB都为0，一个颜色逐渐变亮，RGB都为255

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(7).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(8).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(9).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(10).png)

## QuickCrab

### 要求

Create a class QuickCrab that extends CrabCritter. A QuickCrab processes actors the same way a CrabCritter does. A QuickCrab moves to one of the two locations, randomly selected, that are two spaces to its right or left, if that location and the intervening location are both empty. Otherwise, a QuickCrab moves like a CrabCritter.

### 实现

QuickCrab继承CrabCritter，重写getMoveLocations，当周围的loc为0时，调用父类CrabCritter的getMoveLocations

```java
public ArrayList<Location> getMoveLocations() 
{
    ArrayList<Location> locs = new ArrayList<Location>();
    int[] dirs = { Location.LEFT, Location.RIGHT }; 
    for (Location loc : getLocationsInDirections(dirs)) 
        if (getGrid().get(loc) == null) 
            locs.add(loc);

    if (locs.size() == 0) 
        return super.getMoveLocations();

    return locs;
}
```

重写getLocationsInDirections，调用两次getAdjacentLocation，获取左右方向最近两格的位置

```java
public ArrayList<Location> getLocationsInDirections(int[] directions)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    Grid gr = getGrid();
    Location loc = getLocation();

    for (int d : directions)
    {
        Location neighborLoc1 = loc.getAdjacentLocation(getDirection() + d);
        if (gr.isValid(neighborLoc1) && getGrid().get(neighborLoc1) == null) 
        {
            Location neightborLoc2 = neighborLoc1.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neightborLoc2)) 
                locs.add(neightborLoc2);
        }
    }

    return locs;
}
```

生成两格crab，可以看到crab只会两格两格左右方向移动，有rock挡住不会跳过

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(11).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(12).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(13).png)

## KingCrab

### 要求

Create a class KingCrab that extends CrabCritter. A KingCrab gets the actors to be processed in the same way a CrabCritter does. A KingCrab causes each actor that it processes to move one location further away from the KingCrab. If the actor cannot move away, the KingCrab removes it from the grid. When the KingCrab has completed processing the actors, it moves like a CrabCritter.

### 实现

KingCrab继承CrabCritter，重写processActors，当前方的actor为flower或者rock时，消除actor

```java
public void processActors(ArrayList<Actor> actors)
{
    for (Actor actor : actors)
    {
        if(!(actor instanceof KingCrab))
        {
            ArrayList<Location> loc = getGrid().getEmptyAdjacentLocations(actor.getLocation());
            if(loc.size() == 0 || actor instanceof Flower || actor instanceof Rock) 
            {
                actor.removeSelfFromGrid();
            }
            else 
            {
                int n = loc.size();
                int r = (int) (Math.random() * n);
                actor.moveTo(loc.get(r));
            }
        }
    }
}
```

可以看到，一开始生成的rock和flower都被消除了

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(14).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(15).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/1%20(16).png)

## sonar-scanner测试

可以看到，六个项目代码都通过了sonar-scanner测试

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/2%20(1).png)

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part4/2%20(2).png)