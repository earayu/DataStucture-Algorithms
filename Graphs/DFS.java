package Graphs;

import java.util.Stack;

public class DFS {

	private Graph g;// unDirectedGraph
	private int s;// startVertex
	private boolean[] marked;// 索引位置的顶点是否被访问过
	private int[] from;// 在dfs访问顺序中，该顶点的前一个顶点

	public DFS(Graph g, int s) {
		this.g = g;
		this.s = s;
		marked = new boolean[g.V()];
		from = new int[g.V()];
	}

	/**
	 * 深度优先搜索无向图g
	 * 
	 * @param g
	 * @param s
	 */
	public void dfs() {
		dfs(g, s);
	}

	
	// dfs的实现代码,从启示节点遍历图g，访问信息记录在marked中，路径信息记录在from中。
	private void dfs(Graph g, int s) {
		marked[s] = true;
		for (int w : g.adj(s))
			if (!marked[w]) {
				from[w] = s;
				dfs(g, w);
			}
	}
	

	/**
	 * 返回定点w是否被访问过，可以用来判断是否有一条从初始节点s到w的路径。
	 * 
	 * @param w
	 * @return
	 */
	public boolean hasPathTo(int w) {
		return marked[w];
	}

	/**
	 * 用深度优先搜索实现。如果存在从s到w的路径，则返回路径上的节点。如果不存在，返回null
	 * 
	 * @param w
	 * @return
	 */
	public Iterable<Integer> pathTo(int w) {
		dfs(g, s);// 先用dfs遍历一下无向图
		if (!marked[w]) // 如果没有被访问过，则路径不存在
			return null;
		Stack<Integer> path = new Stack<>();
		for (int x = w; x != s; x = from[x])// 从from数组倒推前一个顶点，直到回到s
			path.push(x);
		path.push(s);
		return path;
	}

	public static void main(String[] args) {
		Graph g = new Graph(13);
		g.addEdge(0, 5);
		g.addEdge(4, 3);
		g.addEdge(0, 1);
		g.addEdge(9, 12);
		g.addEdge(6, 4);
		g.addEdge(5, 5);
		g.addEdge(0, 2);
		g.addEdge(11, 12);
		g.addEdge(9, 10);
		g.addEdge(0, 6);
		g.addEdge(7, 8);
		g.addEdge(9, 11);
		g.addEdge(5, 3);

		DFS d = new DFS(g, 0);

		Stack<Integer> stack = (Stack<Integer>) d.pathTo(4);
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		// result: 0 5 3 4
	}

}
