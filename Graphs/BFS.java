package Graphs;

import list.MyQuene;

public class BFS {

	private Graph g;
	private int s;
	private int count;
	private boolean[] marked;
	
	public BFS(Graph g, int s) {
		this.g = g;
		this.s = s;
		marked = new boolean[g.V()];
		count = 0;
	}
	
	public void Search(){
		Search(g, s);
	}
	
	public void Search(Graph g, int s){
		MyQuene<Integer> quene = new MyQuene<>();
		quene.enQuene(s);
		while(!quene.isEmpty()){
			int v = quene.deQuene();
			marked[v] = true;
			for(int w:g.adj(v)){
				if(!marked(w))
					quene.enQuene(w);
			}
		}
	}
	
	public boolean marked(int w){
		return marked[w];
	}
	
	public int count(){
		return this.count;
	}

}
