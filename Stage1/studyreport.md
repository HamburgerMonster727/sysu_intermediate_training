# Vi,Java,Ant和Junit的自学报告

[TOC]

## Vi/Vim

### 介绍

Vi/Vim是一个著名的功能强大、高度可定制的文本编辑器，是在Linux下第二大强大的编辑器，十分方便程序的设计。

### Vi/Vim的安装

linux默认自带Vi/Vim，需要系统命令进行安装

```
sudo apt install vim
```

### Vi/Vim打开/创建文件

输入vim 文件名，若文件存在，打开文件，若文件不存咋，会自动创建一个新的文件并且打开该文件

### Vi/Vim的三种模式

Vi/Vim分为三种模式：普通模式，输入模式和命令模式

- 普通模式：

  当vim打开文件后就进入了普通模式，在普通模式下可以进入命令或者插入模式：

  - i ，切换到输入模式，光标不动，在光标前面开始编辑文本。
  - a，切换到输入模式，光标向后移动一位，在光标后面开始编辑文本。
  - o，切换到输入模式，创建一个新行，光标移动到新行的行首，在行首开始编辑文本。
  - **dd，删除该行。
  - **yy，复制该行。
  - **p，粘贴。
  - x ，删除当前光标所在处的字符。
  - : 进入命令模式，在最下面一行进行输入命令。

- 输入模式：

  普通模式输入i，a，o，即可进入输入模式：

  - Enter，换行
  - Backspace，删除光标前一个字符
  - Del，删除光标后一个字符
  - Home，移动光标到行首
  - End，移动光标到行尾
  - PageUP，向上翻页
  - PageDown，向下翻页
  - Esc，退出输入模式，进入普通模式

- 命令模式：

  普通模式中，按下：(冒号键) 即可进入命令模式：

  - w，保存文件
  - q，退出vim
  - q!，强制退出vim
  - wq，保存文件并且退出vim

### 案例示范

我们尝试在桌面新建一个test.c文件，并对其进行编辑

可以看到，桌面一开始是没有test.c文件的，输入vim test.c后，桌面生成了一个文件，并且进入了vim的普通模式

![1](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/1%20(1).png)

![2](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/1%20(2).png)

输入i后，进入了vim的输入模式，对文本进行编辑

![3](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/1%20(3).png)

按Esc，退出输入模式，输入:进入了命令模式，输入wq，保存文件并且退出vim

![4](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/1%20(4).png)

![5](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/1%20(5).png)

打开test.c，可以看到刚才编辑的文本都保存在了test.c当中

![6](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/1%20(6).png)

## Java

### 介绍

- Java是一门面向对象编程语言，吸收了C++的许多优点，丢弃了C++里难以理解的多继承、指针等复杂概念，因此Java语言具有功能强大和简单易用两个特征。
- Java与平台无关，Java程序可以运行在任何安装了Java虚拟机的计算机上。
- Java是解释型的，不产生整个的机器代码程序，翻译一句执行一句，运行速度会比能够编译为可执行的机器代码的运行速度慢一些。
- Java语法和C++十分相近，学过C++的程序员可以很块地上手学习Java。Java里面没有指针，由系统自动分配和回收内存，程序员不需要为了内存的管理而烦恼。
- Java和C++一样，是面向对象的，具有类，接口，继承等面向对象语言的特性，还支持类与接口之间的实现。Java支持全面的动态绑定，C++只支持虚函数的动态绑定
- Java是分布式的，支持网络应用开发，提供了网络编程的库，URL，Socket等。
- Java是多线程的，线程必须由Thread类或者其子类来创建，每个线程都有一个run方法，Java支持多线程之间的同步
- Java是安全的，Java的应用程序会限制在Java的运行环境中，不允许访问计算机的其他部分

### Java的安装

安装Java，首先要安装jdk，登录Java官网，下载最新版的jdk，解压到/usr/lib/jvm目录当中，然后需要配置环境变量：

```
sudo vim ~/.bashrc
```

在文件末尾添加以下环境变量

![7](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/2%20(1).png)

使环境变量生效：

```
source ~/.bashrc
```

系统注册jdk：

```
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-17/bin/java 300
```

查看Java版本，即可知道是否安装成功：

![8](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/2%20(2).png)

### 案例示范

尝试编译HelloWorld

```
public class HelloWorld {
	public static void main(String[] args){
        System.out.println("Hello World!");
    }
}
```

先用javac命令对HelloWorld.java进行编译，会生成一个HelloWorld.class文件：

![9](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/2%20(3).png)

然后用java命令对HelloWorld进行运行：

![10](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/2%20(4).png)

## Ant

### 介绍

Ant是一种基于Java的打包工具，Ant脚本采用XML格式编写，默认的文件名为build.xml。Ant类似于Linux中的Makefile工具，都是用来编译生成文件的，但是Ant是可以跨平台的，而Makefile不可以。Ant可以方便程序的编译，不需要一步步地执行，里面内置javac，java等功能。

### Ant的安装

Ant安装之前需要安装好JDK，并且配置好JAVA_HOME。

一般linux中都有ant的安装包，使用命令来安装Ant：

```
sudo apt-get update
sudo apt-get install ant
```

配置环境变量：

```
export ANT_HOME=/usr/share/ant
export PATH=$PATH:$ANT_HOME/bin
```

配置好环境变量后，查看Ant版本，确定是否安装成功：

![11](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/3%20(1).png)

### Ant的元素

- project是生成文件的根元素，表示一个工程，一个Ant文件中至少包含一个project元素：

  - name：指定project元素的名称。
  - default：指定project默认执行时的target的名称。
  - basedir：指定基路径的位置。
- target为project的子元素，表示一个任务，一个project可以有多个target，表示多个任务，为Ant的基本执行单元：
  - name：指定target元素的名称，在一个project中name是唯一的。
  - depends：描述target之间的依赖关系，Ant会依照depends中的target顺序依次执行每个target。
  - if：检验指定的属性是否存在，若不存在，target不会被执行。
  - unless：与if相反。
  - description：对target的功能描述说明
- property是参数的定义，project中的属性可以通过property来设定。可用作task的属性值。在task中是通过将属性名放在“${”和“}”之间，并放在task属性值的位置来实现的。

### 案例示范

利用Ant实现HelloWorld.java的自动编译

```
<?xml version="1.0"?>
<project name="AntHelloWorld" default="run" basedir=".">
	<target name="clean">
		<delete dir="${basedir}/build"/>
	</target>
	<target name="compile" depends="clean">
		<mkdir dir="${basedir}/build/classes"/>
		<javac srcdir="${basedir}/src" destdir="${basedir}/build/classes"/>
	</target>
	<target name="run" depends="compile">
		<java classname="AntHelloWorld">
			<classpath>
				<pathelement path="${basedir}/build/classes"/>
			</classpath>
		</java>
	</target>
</project>
```

在命令行输入ant，会默认运行build.xml文件

![12](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/3%20(2).png)

可以看到，ant成功执行，在build/classes中，生产了AntHelloWorld.class文件，并且成功运行，输出了Hello,Amigo

![13](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/3%20(3).png)

## Junit

### 介绍

JUnit是一个Java语言的单元测试框架， JUnit有它自己的JUnit扩展生态圈，多数Java的开发环境都已经集成了JUnit作为单元测试的工具。JUnit是由 Erich Gamma 和 Kent Beck 编写的一个回归测试框架。Junit测试是程序员测试，即所谓白盒测试，因为程序员知道被测试的软件如何完成功能和完成什么样的功能。Junit是一套框架，继承TestCase类，就可以用Junit进行自动测试了。

### Junit的安装

从官网上下载Junit-4.9，配置环境变量

```
export JUNIT_HOME=~/Desktop/junit
export CLASSPATH=$CLASSPATH:$JUNIT_HOME/junit-4.9.jar:$JUNIT_HOME:.
```

### Junit的元数据

- @Test(expected=*.class)：如果程序的异常与 *.class相同，则测试通过。
- @Test(timeout=xxx)：如果程序的执行能够在xxx毫秒内完成，则测试通过。
- @Before：每一个测试方法执行之前都要执行一次。
- @After：每一个测试方法执行之后都要执行一次。
- @Ignore：标记的测试方法会在测试中被忽略，不运行此段代码。
- @BeforeClass：必须是静态方法，在所有测试执行之前执行一次。
- @AfterClass：必须是静态方法，在所有测试执行之后执行一次。

### Junit的测试规则

- 测试方法必须使用@Test来修饰。
- 测试方法必须是public void，并且不能带参数。
- 单元测试类必须继承自TestCase。
- 测试方法使用test作为前缀，但不是必须的。
- 测试类使用Test作为后缀，但不是必须的。
- 测试单元中的每个方法必须可以独立测试。
- 测试方法之间不能有任何依赖。

### 案例示范

需要把junit-4.9保存在HelloWorld项目的目录下。

HelloWorld.java:

```
public class HelloWorld {
	public String get(){
    		return "Hello World!";
 	}
	
	public static void main(String[] args){
			HelloWorld hello = new HelloWorld();
        	System.out.println("Hello World!");
    	}
}
```

HelloWorldTest.java:

```
import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldTest {
	@Test
	public void Test() {
		HelloWorld helloworld = new HelloWorld();
		assertEquals("Hello World!", helloworld.get());
	}
}
```

build.xml：

```
<?xml version="1.0" encoding="UTF-8"?>
<project name="JunitHelloWorld" default="run">
    <target name="clean">
        <delete dir="build"/>
    </target> 

    <target name="compile" depends="clean">
        <mkdir dir="build/classes"/>
        <javac srcdir="src" destdir="build/classes" includeantruntime="on">
            <classpath>
                <fileset dir="./" includes="**/*.jar"/>
            </classpath>
        </javac>
    </target>

    <target name="run" depends="compile">
        <java classname="HelloWorld">
            <classpath>
                <pathelement path="build/classes"/>
            </classpath>
        </java>
    </target> 

    <target name="junit" depends="compile">
        <junit printsummary="true" fork="true" haltonfailure="false" showoutput="true">
            <classpath>
                <fileset dir="./" includes="**/*.jar"/>
                <pathelement path="build/classes"/>
            </classpath>
            <test name="HelloWorldTest"/>
            <formatter type="xml"/>
            <batchtest>
                <fileset dir="build/classes" includes="**/*Test.*"/>
            </batchtest>
        </junit>
    </target> 
</project>
```

使用ant junit命令对代码进行测试

![14](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/4%20(1).png)

可以看到测试输出的结果，运行时间，错误数量等信息，并且生成了一个TEST-HelloWorldTest.xml文件

![15](https://gitee.com/liangguanxuan/md_pictures/raw/master/stage1/4%20(2).png)