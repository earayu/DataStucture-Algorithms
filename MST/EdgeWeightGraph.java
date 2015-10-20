package MST;

import list.MyArrayList;

public class EdgeWeightGraph {

	private final int V;
	private int E;
	private MyArrayList<Edge>[] adj;
	
	
	public EdgeWeightGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (MyArrayList<Edge>[]) new MyArrayList[this.V];
		for(int i=0;i<this.V;i++)
			adj[i] = new MyArrayList<Edge>();
	}
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	public void addEdge(Edge e){
		adj[e.either()].add(e);
		adj[e.other(e.either())].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public Iterable<Edge> edges(){
		MyArrayList<Edge> list = new MyArrayList<>();
		for(int v=0; v<this.V; v++)
			for(Edge e:adj[v]){
				if(e.other(v) > v)
					list.add(e);
			}
		return list;
	}
	
	

}
