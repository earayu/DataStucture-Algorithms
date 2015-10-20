package ShortestPath;

import java.util.Stack;

import list.IndexMinPQ;

public class DijktraSP {

	private EdgeWeightDiGraph g;
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public DijktraSP(EdgeWeightDiGraph g) {
		this.g = g;
		edgeTo = new DirectedEdge[g.V()];
		distTo = new double[g.V()];
		for(int v=0; v<g.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		pq = new IndexMinPQ<Double>(g.V());
	}
	
	
	public void sp(int s){
		distTo[s] = 0.0;
		pq.insert(s, 0.0);
		while(!pq.isEmpty())
			relax(pq.delMin());
	}
	
	/**
	 * 放松节点v指出的所有边
	 * @param v
	 */
	private void relax(int v){
		for(DirectedEdge e:g.adj(v)){
			int w = e.to();
			if( distTo[w] > distTo[v] + e.weight()  ){//如果可以松弛
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(pq.contains(w))
					pq.changeKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v]; e!=null; e = edgeTo[e.from()])
			path.push(e);
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
		
		DijktraSP d = new DijktraSP(g);	
		d.sp(0);
		for(DirectedEdge e:d.pathTo(1)){
			System.out.println(e.from());
		}
	}

}
