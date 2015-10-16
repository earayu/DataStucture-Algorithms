package Graphs;

import list.MyQuene;

public class BreathFirstSearch {

	private int count;
	private boolean[] marked;
	
	public BreathFirstSearch(Graph g, int s) {
		marked = new boolean[g.V()];
		bfs(g, s);
	}
	
	private void bfs(Graph g, int s){
		MyQuene<Integer> quene = new MyQuene<>();
		quene.enQuene(s);
		marked[s] = true;
		while(!quene.isEmpty()){
			int v = quene.deQuene();
			for(int w:g.adj(v))
				if(!marked[w]){
					quene.enQuene(w);
					marked[w] = true;
					//edgeTo[w] = v;
				}
		}
	}
	
	private boolean marked(int w){
		return marked[w];
	}
	
	private int count(){
		return count;
	}
	
	

}
