# Test Report

## 测试原理

使用junit，编写JumperTest对Jumper进行测试

## 测试1

对act方法进行测试：生成一个jumper，运行act，检查运行后jumper是否跳跃两格的坐标。然后在jumper前方两格生成一个rock，再运行一次act，检查运行后jumper的坐标是否不变，方向是否改为东北方向。

```java
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
```

## 测试2

对turn方法进行测试：生成一个jumper，运行turn方法，检查运行后的jumper方向是否为东北方向。

```java
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
```

## 测试3

对move方法进行测试：生成一个jumper，运行move方法，检查运行后的jumper的row坐标是否减2，col坐标是否不变。

```java
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
```

## 测试4

对canMove方法进行测试：生成一个jumper，检查jumper的canMove的值是否为true。然后在jumper前方两格生成一个rock，检查jumper的canMove的值是否为false。最后在jumper后方两格生成一个jumper1，检查jumper1的canMove的值是否为false。

```java
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
```

## 测试结果

使用ant进行编译

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project name="Jumper" default="run"> 
    <property name="jar.path" value="./gridworld.jar" />
    <target name="compileJumper">
        <javac classpath="${jar.path}" srcdir="./src" destdir="." includes="Jumper.java" includeantruntime="false"/> 
    </target> 

    <target name="compile" depends="compileJumper">
        <javac classpath="${jar.path}" srcdir="./src" destdir="." includes="JumperRunner.java" includeantruntime="false" />
    </target>
    
    <target name="run" depends="compile">
        <java classname="JumperRunner" fork="true">
            <classpath>
                <pathelement location="${jar.path}" />
                <pathelement location="." />
            </classpath>
        </java>
    </target>

    <target name="junit-test">
        <javac srcdir="./src" destdir="." includes="JumperTest.java" includeantruntime="false">
            <classpath>
                <pathelement location="${jar.path}" />
                <pathelement location="." />
                <pathelement location="./junit-4.9.jar" />
            </classpath>
        </javac>
    </target>
    
    <target name="junit" depends="junit-test, compileJumper">
        <junit printsummary="true" fork="true" haltonfailure="false" showoutput="true">
            <classpath>
                <pathelement location="./src/Jumper.class" />
                <pathelement location="${jar.path}" />
                <pathelement location=".." />
                <pathelement location="." />
                <pathelement location="./junit-4.9.jar" />
            </classpath>
            <test name="JumperTest" />
            <formatter type="plain" usefile="false" />
        </junit>
    </target>
    
        <target name="clean">
        <delete file="Jumper.class" quiet="true" />
        <delete file="JumperRunner.class" quiet="true" />
        <delete file="JumperTest.class" quiet="true" />
    </target>
</project>
```

可以看到，四个测试都通过了

![](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage2/part3/1.png)