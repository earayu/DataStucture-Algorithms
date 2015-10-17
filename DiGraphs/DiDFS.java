package DiGraphs;

public class DiDFS {

	private DiGraph g;
	private int s;
	private boolean[] marked;
	private int count;

	/**
	 * 构造器，初始化有向图，开始节点，计数器，和访问标记位。
	 * @param g
	 * @param s
	 */
	public DiDFS(DiGraph g, int s) {
		this.g = g;
		this.s = s;
		count = 0;
		marked = new boolean[g.V()];
	}

	/**
	 * 深度优先查找DepthFirstSearch
	 */
	public void dfs() {
		dfs(g,s);
	}
	
	/**
	 * 设定有向图开始深度优先查找（dfs）的开始节点
	 * @param s
	 */
	public void startPoint(int s){
		this.s = s;
	}

	public void dfs(DiGraph g, int s) {
		marked[s] = true;
		count++;
		for(int w:g.adj(s)){
			if(!marked(w)){
				dfs(g,w);
			}
		}
	}
	
	/**
	 * 返回该位置的节点是否被访问过
	 * @param w
	 * @return
	 */
	public boolean marked(int w){
		return marked[w];
	}
	
	/**
	 * 清除访问信息
	 */
	public void unMarked(){
		marked = new boolean[g.V()];
	}
	
	/**
	 * 返回访问过的节点数
	 * @return
	 */
	public int count(){
		return count;
	}

	public static void main(String[] args) {
		
	}
}
