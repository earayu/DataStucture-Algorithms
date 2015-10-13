package test;

/**
 * Left-Leaning Red Black Binary Search Tree
 * 左倾红黑二叉查找树
 * @author earayu
 *
 */
public class LLRBBST<K extends Comparable<? super K>, V> {

	public LLRBBST() {
		root = null;
	}
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class RBNode{
		K key;
		V value;
		int N;
		boolean color;
		RBNode leftChild;
		RBNode rightChild;
		
		public RBNode(K key, V value, int n, boolean color) {
			super();
			this.key = key;
			this.value = value;
			this.N = n;
			this.color = color;
		}
	}

	private boolean isRed(RBNode t){
		if(t != null)
			return t.color;
		return false;
	}
	
	private RBNode root;
	
	/*********************************************************************************/
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public int size(){
		return size(root);
	}
	

	
	public void put(K key, V value){
		root = put(key, value, root);
		root.color = BLACK;
	}
	
	public void deleteMin(){
		if(!isEmpty()){
			if( !isRed(root.leftChild) && !isRed(root.rightChild) )
				root.color = RED;
			root = deleteMin(root);
			if(!isEmpty()){
				root.color = BLACK;
			}
		}
	}
	
	public void deleteMax(){
		if(!isEmpty()){
			if( !isRed(root.leftChild) && !isRed(root.rightChild) )
				root.color = RED;
			root = deleteMax(root);
			if(!isEmpty())
				root.color = BLACK;
		}
	}
	
	public void delete(K key){
		if(!isEmpty()){
			if( !isRed(root.leftChild) && !isRed(root.rightChild))
				root.color = RED;
			root = delete(key, root);
			if(!isEmpty())
				root.color = BLACK;
		}
	}
	
	
	/*********************************************************************************/
	
	private int size(RBNode t){
		if(t == null)
			return 0;
		return t.N;
	}
	
	private void flipColor(RBNode t){
		if(t!=null){
			t.color = RED;
			t.leftChild.color = BLACK;
			t.rightChild.color = BLACK;
		}
	}
	
	private void colorFlip(RBNode t){
		if(t!=null){
			t.color = !t.color;
			t.leftChild.color = !t.leftChild.color;
			t.rightChild.color = !t.rightChild.color;
		}
	}
	
	public RBNode rotateLeft(RBNode k1){
		RBNode k2 = k1.rightChild;
		k1.rightChild = k2.leftChild;
		k2.leftChild = k1;
		k2.color = k1.color;
		k1.color = RED;
		k1.N = size(k1.leftChild) + size(k1.rightChild) + 1;
		k2.N = k1.N + size(k2.rightChild) + 1;
		return k2;
	}
	
	public RBNode rotateRight(RBNode k1){
		RBNode k2 = k1.leftChild;
		k1.leftChild = k2.rightChild;
		k2.rightChild = k1;
		k2.color = k1.color;
		k1.color = RED;
		k1.N = size(k1.leftChild) + size(k1.rightChild) + 1;
		k2.N = k1.N + size(k2.rightChild) + 1;
		return k2;
	}
	
	private RBNode moveRedLeft(RBNode t){
		if(t == null)
			return null;
		colorFlip(t);
		if( isRed(t.rightChild.leftChild) ){
			t.rightChild = rotateRight(t.rightChild);
			t = rotateLeft(t);
			colorFlip(t);
		}
		return t;
	}
	
	private RBNode moveRedRight(RBNode t){
		if(t == null)
			return null;
		colorFlip(t);
		if( isRed(t.leftChild.leftChild) ){
			t = rotateLeft(t);
			colorFlip(t);
		}
		return t;
	}
	
	private RBNode balance(RBNode t){
		if(t == null)
			return null;
		if( isRed(t.rightChild) && !isRed(t.leftChild) )
			t = rotateLeft(t);
		if( isRed(t.leftChild) && isRed(t.leftChild.leftChild) )
			t = rotateRight(t);
		if( isRed(t.leftChild) && isRed(t.rightChild) )
			flipColor(t);
		t.N = size(t.leftChild) + size(t.rightChild) + 1;
		return t;
	}
	
	private RBNode getMinNode(RBNode t){
		if(t == null)
			return null;
		while(t.leftChild != null)
			t = t.leftChild;
		return t;
	}
	
	private RBNode put(K key, V value, RBNode t){
		if(t == null)
			return new RBNode(key, value, 1, RED);

		int cmp = key.compareTo(t.key);
		if(cmp<0)
			t.leftChild = put(key, value, t.leftChild);
		else if(cmp>0)
			t.rightChild = put(key, value, t.rightChild);
		else
			t.value = value;
		
		if( isRed(t.rightChild) && !isRed(t.leftChild) )
			t = rotateLeft(t);
		if( isRed(t.leftChild) && isRed(t.leftChild.leftChild) )
			t = rotateRight(t);
		if( isRed(t.leftChild) && isRed(t.rightChild) )
			flipColor(t);
		t.N = size(t.leftChild) + size(t.rightChild) + 1;
		return t;
	}
	
	private RBNode deleteMin(RBNode t){
		if(t == null)
			return null;
		
		if(t.leftChild == null)
			return null;
		if( !isRed(t.leftChild) && !isRed(t.leftChild.leftChild) )
			t = moveRedLeft(t);
		t.leftChild = deleteMin(t.leftChild);
		return balance(t);
	}
	
	private RBNode deleteMax(RBNode t){
		if(t == null)
			return null;
		
		if( isRed(t.leftChild) )
			t = rotateRight(t);
		if(t.rightChild == null)
			return null;
		if( !isRed(t.rightChild) && !isRed(t.rightChild.leftChild) )
			t = moveRedRight(t);
		t.rightChild = deleteMax(t.rightChild);
		return balance(t);
	}
	
	private RBNode delete(K key, RBNode t){
		if(t == null)
			return null;
		
		int cmp = key.compareTo(t.key);
		if(cmp<0){
			if( !isRed(t.leftChild) && !isRed(t.leftChild.leftChild) )//不会有空指针异常
				t = moveRedLeft(t);
			t.leftChild = delete(key, t.leftChild);
		}else{
			if( isRed(t.leftChild) && !isRed(t.rightChild) )
				t = rotateRight(t);	//将左倾红链向右旋转
			if(cmp == 0 && t.rightChild == null)
				return null;	//如果查找到的元素在底部，则直接删除
			if( !isRed(t.rightChild) && !isRed(t.rightChild.leftChild) )
				t = moveRedRight(t);	//制造右倾红链
			if(cmp == 0){	//匹配到非底部元素，用后继元素替代，然后删除后继元素
				RBNode minNode = getMinNode(t.rightChild);
				t.key = minNode.key;
				t.value = minNode.value;
				t.rightChild = delete(minNode.key, t.rightChild);
			}else
				t.rightChild = delete(key, t.rightChild);	//当前元素不匹配key，递归给右子树
		}
		return balance(t);
	}
	
	private void printMid(RBNode t){
		if(t != null){
			System.out.println(t.value);
			printMid(t.leftChild);
			printMid(t.rightChild);
		}
	}
	
	public void printTree(){
		printMid(root);
	}
	
	public static void main(String[] args) {
		LLRBBST<Integer, Integer> rbt = new LLRBBST<>();
		for(int i=1;i<=9;i++){
			rbt.put(i, i);
		}
		rbt.delete(9);
		rbt.printTree();
	}
}
