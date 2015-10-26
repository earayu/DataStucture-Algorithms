package ShortestPath;

import list.MyArrayList;

public class EdgeWeightDiGraph {

	private final int V;
	private int E;
	private MyArrayList<DirectedEdge>[] adj;
	
	public EdgeWeightDiGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (MyArrayList<DirectedEdge>[]) new MyArrayList[this.V];
		for(int i=0;i<this.V;i++)
			adj[i] = new MyArrayList<DirectedEdge>();
	}
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	public void addEdge(DirectedEdge e){
		adj[e.from()].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	


}
