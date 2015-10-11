package test;

public class AVLTree<T extends Comparable<? super T>> {

	/**
	 * AVL树的节点类
	 * @author earayu
	 *
	 * @param <T>
	 */
	private static class AVLNode<T> {
		T data;
		int height;
		AVLNode<T> leftChild;
		AVLNode<T> rightChild;

		public AVLNode(T data, AVLNode<T> leftChild, AVLNode<T> rightNode) {
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightNode;
			this.height = 0;
		}
	}

	private AVLNode<T> root;

	/**
	 * 初始化AVL树
	 */
	public AVLTree() {
		root = null;
	}

	/**
	 * 返回此AVL节点的高度，空节点返回-1
	 * @param t
	 * @return
	 */
	private int height(AVLNode<T> t) {
		return (t == null) ? -1 : t.height;
	}

	/*********************************** 公共方法 ******************************************/

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmpty() {
		root = null;
	}

	/**
	 * 将data插入AVL树，重复元素不插入。
	 * @param data
	 */
	public void put(T data) {
		root = put(data, root);
	}

	/**
	 * 获取值为data的节点
	 * @param data
	 * @return
	 */
	public AVLNode<T> get(T data) {
		return get(data, root);
	}

	public AVLNode<T> getMin(AVLNode<T> t) {
		if (t == null) {
			return null;
		}
		while (t.leftChild != null) {
			t = t.leftChild;
		}
		return t;
	}

	public AVLNode<T> getMax(AVLNode<T> t) {
		if (t == null) {
			return null;
		}
		while (t.rightChild != null) {
			t = t.rightChild;
		}
		return t;
	}

	/**
	 * 返回是否含有值为data的节点
	 * @param data
	 * @return
	 */
	public boolean contains(T data) {
		return get(data) != null;
	}

	/**
	 * 删除值为data的节点
	 * @param data
	 */
	public void delete(T data) {
		root = delete(data, root);
	}
	
	/**
	 * 打印此AVL树，先序遍历
	 */
	public void printTree(){
		if(isEmpty()){
			System.out.println("Empty Tree!");
		}else{
			printPre(root);
		}
	}

	

	/********************************* 私有方法 ********************************************/

	/**
	 * 在t中删除值为data的节点
	 * @param data
	 * @param t
	 * @return
	 */
	private AVLNode<T> delete(T data, AVLNode<T> t) {
		if (t == null)
			return null;

		int cmp = data.compareTo(t.data);
		if (cmp < 0) {
			t.leftChild = delete(data, t.leftChild);
			//
			if (heightDif(t.leftChild, t.rightChild) == 2) {
				if (t.rightChild != null) // 右子树可能不存在，防止空指针异常。重要
					if (t.rightChild.leftChild != null) { // 确定形状，RL
						t = doubleWithRightChild(t);
					} else { // RR
						t = rotateWithRightChild(t);
					}
			}
		} else if (cmp > 0) {
			t.rightChild = delete(data, t.rightChild);
			if (heightDif(t.leftChild, t.rightChild) == 2) {
				if (t.leftChild != null)
					if (t.leftChild.leftChild != null) { // LL
						t = rotateWithLeftChild(t);
					} else {
						t = doubleWithLeftChild(t); // LR
					}
			}
		} else {
			if (t.leftChild == null || t.rightChild == null) { // 0个或1个儿子
				return (t.leftChild == null) ? t.rightChild : t.leftChild;
			} else { 	// 2个儿子
				t.data = getMin(t.rightChild).data;
				t.rightChild = delete(t.data, t.rightChild);
			}
		}
		return t;
	}

	/**
	 * 根据data值， 返回存放该值得节点。 若不存在，则返回 null
	 * @param data
	 * @param t
	 * @return
	 */
	private AVLNode<T> get(T data, AVLNode<T> t) {
		if(t == null)
			return null;
		
		int cmp = data.compareTo(t.data);
		if(cmp<0)
			return get(data, t.leftChild);
		else if(cmp>0)
			return get(data, t.rightChild);
		else
			return t;
	}
	

	/**
	 * 返回t1和t2的高度差，如果大于等于2，则此树不平衡。空节点的高度为-1，叶子节点高度为0。
	 * @param t1
	 * @param t2
	 * @return
	 */
	private int heightDif(AVLNode<T> t1, AVLNode<T> t2) {
		return Math.abs(height(t1) - height(t2));
	}

	/**
	 * 实现将值为data的节点插入t中。重复元素不插入
	 * @param data
	 * @param t
	 * @return
	 */
	private AVLNode<T> put(T data, AVLNode<T> t) {
		if (t == null)
			return new AVLNode<T>(data, null, null);

		int cmp = data.compareTo(t.data);
		if (cmp < 0) {
			t.leftChild = put(data, t.leftChild);
			if (heightDif(t.leftChild, t.rightChild) == 2)
				if (data.compareTo(t.leftChild.data) < 0)
					t = rotateWithLeftChild(t); // 左左转
				else
					t = doubleWithLeftChild(t); // 左右转

		} else if (cmp > 0) {
			t.rightChild = put(data, t.rightChild);
			if (heightDif(t.rightChild, t.leftChild) == 2)
				if (data.compareTo(t.rightChild.data) > 0)
					t = rotateWithRightChild(t); // 右左转
				else
					t = doubleWithRightChild(t); // 右右转

		} else
			; 		// 已有该节点
		
		t.height = Math.max(height(t.leftChild), height(t.rightChild)) + 1;//更新高度
		return t;
	}

	/**
	 * 旋转LL的情况
	 * @param k1
	 * @return
	 */
	private AVLNode<T> rotateWithLeftChild(AVLNode<T> k1) {
		AVLNode<T> k2 = k1.leftChild;
		k1.leftChild = k2.rightChild;
		k2.rightChild = k1;
		k1.height = Math.max(height(k1.leftChild), height(k1.rightChild)) + 1;
		k2.height = Math.max(height(k2.leftChild), k1.height) + 1;
		return k2; // 注意这个返回值小心不要写错
	}

	private AVLNode<T> rotateWithRightChild(AVLNode<T> k1) {
		AVLNode<T> k2 = k1.rightChild;
		k1.rightChild = k2.leftChild;
		k2.leftChild = k1;
		k1.height = Math.max(height(k1.leftChild), height(k1.rightChild)) + 1;
		k2.height = Math.max(k1.height, height(k2.rightChild)) + 1;
		return k2;
	}

	/**
	 * 旋转LR的情况
	 * @param k1
	 * @return
	 */
	private AVLNode<T> doubleWithLeftChild(AVLNode<T> k1) {
		k1.leftChild = rotateWithRightChild(k1.leftChild);
		return rotateWithLeftChild(k1);
	}

	private AVLNode<T> doubleWithRightChild(AVLNode<T> k1) {
		k1.rightChild = rotateWithLeftChild(k1.rightChild);
		return rotateWithRightChild(k1);
	}
	

	/**
	 * 先序遍历t
	 * @param t
	 */
	private void printPre(AVLNode<T> t) {
		if (t != null) {
			System.out.println(t.data);
			printPre(t.leftChild);
			printPre(t.rightChild);
		}
	}
	
	/**
	 * 中序遍历t,可以在printTree中用switch语句添加。
	 * @param t
	 */
	private void printMid(AVLNode<T> t) {
		if (t != null) {
			printMid(t.leftChild);
			System.out.println(t.data);
			printMid(t.rightChild);
		}
	}

	/**
	 * 后序遍历t,可以在printTree中用switch语句添加。
	 * @param t
	 */
	private void printPos(AVLNode<T> t) {
		if (t != null) {
			printPos(t.leftChild);
			printPos(t.rightChild);
			System.out.println(t.data);
		}
	}
	
	public void h(){
		System.out.println(root.height);
	}
}
