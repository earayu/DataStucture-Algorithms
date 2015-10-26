package ShortestPath;

import java.util.Stack;
import list.IndexMinPQ;

public class Dijkstra {

	private EdgeWeightDiGraph g;//加权有向图
	private int s;//初始节点
	private int[] from;
	private double[] dist;//索引位置的顶点距离初始节点的距离
	private IndexMinPQ<Double> pq;
	

	public Dijkstra(EdgeWeightDiGraph g, int s) {
		this.g = g;
		this.s = s;
		from = new int[g.V()];
		dist = new double[g.V()];
		for(int v=0;v<g.V();v++)
			dist[v] = Double.POSITIVE_INFINITY;
		pq = new IndexMinPQ<>(g.V());
		
		sp(s);//使用Dijkstra生成最短路径树
	}
	
	/**
	 * 从顶点startVertex开始生成最短路径树
	 * @param startVertex
	 */
	public void sp(int startVertex){
		clear();//清除之前的最短路径树的信息
		this.s = startVertex;//更新初始节点
		dist[startVertex] = 0.0;
		pq.insert(startVertex, 0.0);
		while(!pq.isEmpty())
			relax(pq.delMin());
	}
	
	/**
	 * 放松顶点v，并将放松后的结果插入索引优先队列pq
	 * @param v
	 */
	public void relax(int v){
		for(DirectedEdge e:g.adj(v)){//遍历从v指出的边
			int w = e.to();
			if(dist[w] > dist[v] + e.weight()){//如果有更短路径，则更新from和dist
				dist[w] = dist[v] + e.weight();
				from[w] = v;
				if(pq.contains(w))//然后更新pq
					pq.changeKey(w, dist[w]);
				else
					pq.insert(w, dist[w]);
			}
		}
	}
	
	/**
	 * 清除之前的最短路径树的信息
	 */
	private void clear(){
		from = new int[g.V()];
		dist = new double[g.V()];
		for(int v=0;v<g.V();v++)
			dist[v] = Double.POSITIVE_INFINITY;
		pq = new IndexMinPQ<>(g.V());
	}
	
	
	
	
	public double distTo(int w){
		return dist[w];
	}
	
	public boolean hasPathTo(int w){
		return dist[w] != Double.POSITIVE_INFINITY;
	}
	
	public Iterable<Integer> pathTo(int w){
		if(!hasPathTo(w))
			return null;
		Stack<Integer> path = new Stack<>();
		for(int x=w; x!=s; x=from[x])
			path.push(x);
		path.push(s);
		return path;
	}
	
	
	
	
	public static void main(String[] args) {
		EdgeWeightDiGraph g =new EdgeWeightDiGraph(8);
		
		DirectedEdge e1 = new DirectedEdge(4, 5, 0.35);
		DirectedEdge e2 = new DirectedEdge(5, 4, 0.35);
		DirectedEdge e3 = new DirectedEdge(4, 7, 0.37);
		DirectedEdge e4 = new DirectedEdge(5, 7, 0.28);
		DirectedEdge e5 = new DirectedEdge(7, 5, 0.28);
		DirectedEdge e6 = new DirectedEdge(5, 1, 0.32);
		DirectedEdge e7 = new DirectedEdge(0, 4, 0.38);
		DirectedEdge e8 = new DirectedEdge(0, 2, 0.26);
		DirectedEdge e9 = new DirectedEdge(7, 3, 0.39);
		DirectedEdge e10 = new DirectedEdge(1, 3, 0.29);
		DirectedEdge e11 = new DirectedEdge(2, 7, 0.34);
		DirectedEdge e12 = new DirectedEdge(6, 2, 0.40);
		DirectedEdge e13 = new DirectedEdge(3, 6, 0.52);
		DirectedEdge e14 = new DirectedEdge(6, 0, 0.58);
		DirectedEdge e15 = new DirectedEdge(6, 4, 0.93);
		g.addEdge(e1 );
		g.addEdge(e2 );
		g.addEdge(e3 );
		g.addEdge(e4 );
		g.addEdge(e5 );
		g.addEdge(e6 );
		g.addEdge(e7 );
		g.addEdge(e8 );	
		g.addEdge(e9 );
		g.addEdge(e10);
		g.addEdge(e11);
		g.addEdge(e12);
		g.addEdge(e13);
		g.addEdge(e14);
		g.addEdge(e15);
		
		Dijkstra d = new Dijkstra(g,0);	
		
		Stack<Integer> stack = new Stack<>();
		
		stack = (Stack<Integer>) d.pathTo(6);
		
		System.out.println("顶点" + "\t" + "距离");
		while(!stack.isEmpty()){
			int a = stack.pop();
			System.out.print(a + "\t");
			System.out.println(d.distTo(a));
		}
	}

}
