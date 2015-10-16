package Graphs;

public class Search {

	private Graph g;
	private int s;
	
	public Search(Graph g, int s) {
		this.g = g;
		this.s = s;
	}

	public boolean marked(int v){
		for(int x:g.adj(s)){
			if(v==x)
				return true;
		}
		return false;
	}
	
	public int count(){
		int count=0;
		for(int x:g.adj(s)){
			count++;
		}
		return count;
	}
	
}
