package Graphs;

import java.util.Stack;
import list.MyQuene;

public class BFS {

	private Graph g;
	private int s;
	private boolean[] marked;
	private int[] from;

	public BFS(Graph g, int s) {
		this.g = g;
		this.s = s;
		marked = new boolean[g.V()];
		from = new int[g.V()];
	}

	/**
	 * 广度优先遍历图g
	 */
	public void bfs() {
		bfs(g, s);
	}

	public void bfs(Graph g, int s) {
		MyQuene<Integer> quene = new MyQuene<>();
		// 要在放入队列之前mark，否则得到的不是最短路径。
		//因为这样的话，一个节点虽然进入了队列，但有可能被再次放入队列。也就是说被访问了2次
		marked[s] = true;
		quene.enQuene(s);// 初始顶点加入队列
		while (!quene.isEmpty()) {
			int v = quene.deQuene();
			// marked[v] = true;//将弹出队列的顶点设置为已访问过
			for (int w : g.adj(v)) {
				if (!marked[w]) {
					from[w] = v;
					marked[w] = true;
					quene.enQuene(w);// 将v的未被访问的邻接顶点加入队列

				}
			}
		}
	}

	public boolean hasPathTo(int w) {
		return marked[w];
	}

	/**
	 * 用广度优先搜索实现。如果存在从s到w的路径，则返回路径上的节点。如果不存在，返回null
	 * 
	 * @param w
	 * @return
	 */
	public Iterable<Integer> pathTo(int w) {
		bfs(g, s);// 先用bfs遍历一下无向图
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

		BFS b = new BFS(g, 0);

		Stack<Integer> stack = (Stack<Integer>) b.pathTo(4);
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		// result: 0 6 4 
	}
}
