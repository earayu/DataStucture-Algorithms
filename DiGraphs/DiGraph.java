package DiGraphs;

import Graphs.Graph;
import list.MyArrayList;

public class DiGraph {

	/**
	 * 节点数
	 * 边数
	 * 邻接表
	 */
	private final int V;
	private int E;
	private MyArrayList<Integer>[] adj;
	
	/**
	 * 给定定点数，初始化有向图
	 * @param V
	 */
	public DiGraph(int V) {
		this.V = V;
		this.E = 0;
		adj =(MyArrayList<Integer>[]) new MyArrayList[this.V];
		for(int v=0;v<V;v++)
			adj[v] = new MyArrayList<Integer>();
	}
	
	/**
	 * 添加一条从v到w的有向边
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w){
		adj[v].add(w);
		E++;
	}
	
	/**
	 * 返回图g的v顶点的邻接表
	 * @param v
	 * @return
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	/**
	 * 返回图g的反转图(所有边反方向的图)
	 * @return
	 */
	public DiGraph reverse(){
		DiGraph r = new DiGraph(V);
		for(int v=0;v<V;v++)
			for(int x:adj(v)){
				r.addEdge(x, v);
			}
		return r;
	}
	
	/**
	 * 返回定顶点数
	 * @return
	 */
	public int V(){
		return this.V;
	}
	
	/**
	 * 返回边数
	 * @return
	 */
	public int E(){
		return this.E;
	}
	

}
