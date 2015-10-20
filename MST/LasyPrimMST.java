package MST;

import java.util.PriorityQueue;
import list.MyQuene;

public class LasyPrimMST {

	private EdgeWeightGraph g;// 加权无向图
	private boolean[] marked;// 最小生成树的顶点
	private MyQuene<Edge> mst;// 最小生成树的边
	private PriorityQueue<Edge> pq;// 用有限队列保存横切边

	public LasyPrimMST(EdgeWeightGraph g) {
		this.g = g;
		marked = new boolean[g.V()];
		mst = new MyQuene<>();
		pq = new PriorityQueue<Edge>();
	}

	/**
	 * 计算图g中的最小生成树（为了方便，图g默认连通图）
	 * @param g
	 */
	public void mst(EdgeWeightGraph g){
		visit(0);//初始点
		while(!pq.isEmpty()){
			Edge e = pq.poll();//从pq取出权值最小的边
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w])//如果该边两边的顶点都被访问过了，则跳过
				continue;
			mst.enQuene(e);//该边为mst中的一条边
			if(!marked[v])	visit(v);//访问该边的另一端
			if(!marked[w])	visit(w);
		}
	}
	
	/**
	 * 如果连接顶点v另一端的顶点未被访问，则将此边加入优先队列
	 * 
	 * @param v
	 */
	private void visit(int v) {
		marked[v] = true;
		for (Edge e : g.adj(v))
			if (!marked[e.other(v)])
				pq.add(e);
	}

}
