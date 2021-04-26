package graphy;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author SunHang
 * @className: Graph
 * @description:
 * @createTime 2021/4/14 19:36
 */
public class Graph {
    // 存储顶点的集合
    private ArrayList<String> vertexList;
    //  存储图对应的临结矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 定义一个数组boolean[] ，记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        String[] vertexValue = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(5);
        // 添加顶点
        for (String ver : vertexValue) {
            graph.insertVertex(ver);
        }
        // 添加边 A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
        // 测试深度遍历是否OK
        System.out.println("---深度遍历---");
//        graph.dfs();
        System.out.println();
        System.out.println("---广度优先---");
        graph.bfs();
    }

    // 构造器
    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    // 插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边 相当于二位数组 A-->B A: 0 B:1 则 [0][1] = 1
     *
     * @param v1
     * @param v2
     * @param weight: 0 或者 1 表示两个顶点之间的关系
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;  // 边的数量++
    }

    // 图中常用的方法：1. 返回结点的个数 2.得到边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回结点i 的下标对应的数据0-->"A" 1-->"B"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    // 得到第一个邻接节点的下标w
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * @param v1 : 前一个邻接结点
     * @param v2 ：当前的结点
     * @return ： 下一个邻接节点
     */
    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历方法
    private void dfs(boolean[] isVisited, int i) {
        // 访问当前的结点
        System.out.print(getValueByIndex(i) + "-->");
        // 设置当前的结点为已访问结点
        isVisited[i] = true;
        // 查找下一个结点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            // 判断是否被访问过
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    // 对dfs进行重载 遍历所有的结点，并进行dfs
    public void dfs() {
        // 遍历所有的结点，进行dfs(回溯)
        for (int i = 0; i < getNumOfVertex(); i++) {
            // 如果没有被访问过
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 广度优先遍历
    // 对一个结点先进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u;  // 队列中的头结点的下标
        int w;   // 邻接节点的下标
        // 需要一个队列,记录结点的访问顺序，当找不到的时候，从哪里开始重新访问
        LinkedList queue = new LinkedList();
        // 访问当前的结点
        System.out.print(getValueByIndex(i) + "-->");
        isVisited[i] = true;
        // 将该结点加入到队列中,加入到链表的最后，可以优先取出 先进先出
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列的头结点
            u = (Integer) queue.removeFirst();
            // 得到第一个邻结点的下标
            w = getFirstNeighbor(u);
            // 判断是否存在
            while (w != -1) {
                // 是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "-->");
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u为起始点，重新找w后面的下一个节点
                w = getNextNeighbor(u, w);  // 广度优先，先把起始点u 的所有节点遍历完成
            }
        }
    }

    // 遍历所有的节点，对所有的节点都进行广度优先搜索
    public void bfs(){
        for(int i = 0; i<getNumOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
