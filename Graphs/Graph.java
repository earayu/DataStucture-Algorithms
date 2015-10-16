package Graphs;

import java.util.Iterator;

import list.MyArrayList;

public class Graph {

	//顶点数
	private final int V;
	//边数
	private int E;
	private MyArrayList<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (MyArrayList<Integer>[]) new MyArrayList[this.V];
		for(int v=0; v<V; v++){
			adj[v] = new MyArrayList<Integer>();
		}
	}
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
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
		g.addEdge(9,10);
		g.addEdge(0, 6);
		g.addEdge(7, 8);
		g.addEdge(9, 11);
		g.addEdge(5, 3);
		
		Iterable<Integer> iterable = g.adj(0);
		Iterator<Integer> it = iterable.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
