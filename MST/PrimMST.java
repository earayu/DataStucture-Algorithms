package MST;

import list.IndexMinPQ;

public class PrimMST {

	private EdgeWeightGraph g;// 加权无向图
	private boolean[] marked;// 最小生成树的顶点
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;// 用有限队列保存横切边

	public PrimMST(EdgeWeightGraph g) {
		this.g = g;
		marked = new boolean[g.V()];
		edgeTo = new Edge[g.V()];
		distTo = new double[g.V()];
		for (int v = 0; v < g.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;// 正无穷
		pq = new IndexMinPQ<Double>(g.V());
	}

	public void pmst() {
		distTo[0] = 0.0;
		pq.insert(0, 0.0);
		while (!pq.isEmpty()) {
			visit(pq.delMin());// delMin返回的是最小值的索引（删除的是最小值）
		}
	}

	private void visit(int v) {
		marked[v] = true;
		for (Edge e : g.adj(v)) {
			int to = e.other(v);
			if (marked[to])
				continue;
			if (e.weight() < distTo[to]) {
				distTo[to] = e.weight();
				edgeTo[to] = e;
				if (pq.contains(to))
					pq.changeKey(to, distTo[to]);
				else
					pq.insert(to, distTo[to]);
			}
		}
	}

}
