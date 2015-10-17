package DiGraphs;

import java.util.Stack;

public class CycleDetect {

	private DiGraph g;
	private int s;
	private boolean[] marked;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	private int edgeTo[];

	public CycleDetect(DiGraph g) {
		marked = new boolean[g.V()];
		onStack = new boolean[g.V()];
		edgeTo = new int[g.V()];
		for (int v = 0; v < g.V(); v++)
			if (!marked(v))
				dfs(g, v);
	}

	public void dfs(DiGraph g, int s) {
		marked[s] = true;
		onStack[s] = true;
		for (int w : g.adj(s))
			if (this.hasCycle())
				return;
			else if (!marked(w)) {
				edgeTo[w] = s;
				dfs(g, w);
			} else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for (int x = s; x != w; x = edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(s);
			}
		onStack[s] = false;
	}

	private boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}

	private boolean marked(int w) {
		return marked[w];
	}
	
	public static void main(String[] args) {
		DiGraph g = new DiGraph(13);
		g.addEdge(0, 5);
		g.addEdge(4, 3);
		g.addEdge(5, 4);
		g.addEdge(3, 5);
		CycleDetect detectCycle = new CycleDetect(g);
		
		Stack<Integer> st = (Stack<Integer>) detectCycle.cycle();
		while(!st.isEmpty()){
			System.out.print(st.pop() + " ");
		}
	}
	//result: 3 5 4 3 

}
