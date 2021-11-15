package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.*;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw 
{
    /**
     * 拼图构造函数
     */
    public Solution(){}

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) 
    {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) 
    {
        Vector<JigsawNode> noVisitedNode; //未访问的节点
        Vector<JigsawNode> VisitedNode;   //已访问过的节点
        int number;                       //访问过节点的数量

        beginJNode = new JigsawNode(bNode);
        endJNode = new JigsawNode(eNode);
        currentJNode = new JigsawNode(bNode);
        noVisitedNode = new Vector<JigsawNode>();
        VisitedNode = new Vector<JigsawNode>();
        number = 0;
        noVisitedNode.add(bNode);

        //开始BFS广度优先搜索
        while (!noVisitedNode.isEmpty()) 
        {
            //未访问节点出队
            currentJNode = noVisitedNode.elementAt(0);
            noVisitedNode.remove(0);
            //访问节点入队
            VisitedNode.add(currentJNode);
            //访问节点数量增加
            number++;

            //BFS搜索结束
            if (currentJNode.equals(endJNode)) 
            {
                this.getPath();
                break;
            } 

            //使用jigsawNode类的move函数，获取下一个未访问的节点
            JigsawNode[] nextNodes = new JigsawNode[]{new JigsawNode(currentJNode), new JigsawNode(currentJNode),
                                                    new JigsawNode(currentJNode), new JigsawNode(currentJNode)};
            for (int i = 0; i < 4; i++) 
            {
                if (nextNodes[i].move(i) && !VisitedNode.contains(nextNodes[i]) && !noVisitedNode.contains(nextNodes[i])) 
                {
                    noVisitedNode.add(nextNodes[i]);
                }
            }
        }

        System.out.println("Jigsaw BFSearch Result:");
        System.out.println("Solution Path: " + getSolutionPath());
        //System.out.println("Begin state:" + bNode.toString());
        //System.out.println("End state:" + eNode.toString());
        System.out.println("Total number of searched nodes:" + number);
        System.out.println("Depth of the current node is:" + getCurrentJNode().getNodeDepth());
        return this.isCompleted();
    }

    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) 
    { 
        /**
         * 估价函数f(n)用来估计节点n的重要性，可以同时使用多个估价方法，
         * f(n) = a*f1(n) + b*f2(n) + ... 通过适当调整权重(a,b,...)，能够加快搜索速度
         * 通过使用后续节点不正确的数码个数，曼哈顿距离，欧几里得距离来进行计算估价函数
         */

        int incorrectNumber = 0;                    //后续节点不正确的数码个数
        int manhattanDistance = 0;                  //曼哈顿距离
        int euclideanDistance = 0;                  //欧几里得距离
        int dimension = JigsawNode.getDimension();  //获取拼图的维数

        //遍历拼图，获取后续节点不正确的数码个数
        for (int i = 1; i < dimension * dimension; i++) 
        {
            if (jNode.getNodesState()[i] + 1 != jNode.getNodesState()[i + 1]) 
                incorrectNumber++;
        }

        //遍历拼图，计算曼哈顿距离和欧几里得距离
        for (int i = 1; i <=  dimension * dimension; i++) 
        {
            for (int j = 1; j <= dimension * dimension; j++) 
            {
                if (jNode.getNodesState()[i] != 0 && jNode.getNodesState()[i] == endJNode.getNodesState()[j]) 
                {
                    int length = (int)(Math.sqrt(jNode.getNodesState().length - 1));
                    int x1 = (i - 1) / length;
                    int y1 = (i + 4) % length;
                    int x2 = (j - 1) / length; 
                    int y2 = (j + 4) % length;
                    int disX = Math.abs(x1 - x2);
                    int disY = Math.abs(y1 - y2);  
                    manhattanDistance += (int)(disX + disY);
                    euclideanDistance += (int)(Math.sqrt((double)(disX * disX + disY * disY)));
                    break;
                }
            }
        }

        jNode.setEstimatedValue(incorrectNumber + manhattanDistance * 2  + euclideanDistance);
    }
}

