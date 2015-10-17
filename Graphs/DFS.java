package Graphs;

public class DFS {

	private Graph g;
	private int s;
	private int count;
	private boolean[] marked;
	
	public DFS(Graph g, int s) {
		this.g = g;
		this.s = s;
		marked = new boolean[g.V()];
		count = 0;
	}
	
	public void Search(){
		Search(g, s);
	}
	
	private void Search(Graph g, int s){
		marked[s] = true;
		count++;
		for(int w:g.adj(s))
			if(!marked[w]){
				Search(g, w);
			}
	}
	
	public boolean marked(int w){
		return marked[w];
	}
	
	public int count(){
		return this.count;
	}

}
