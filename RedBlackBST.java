package test;

public class RedBlackBST<K extends Comparable<? super K>, V> {

	public RedBlackBST() {
	}
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private RBNode root;
	
	private class RBNode{
		K key;
		V value;
		boolean color;
		int N;
		RBNode leftChild;
		RBNode rightChild;
		
		public RBNode(K key, V value,  int N, boolean color) {
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(RBNode t){
		if(t == null)
			return false;
		return t.color == RED;
	}
	
	/*****************************************************************************/
	
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
		root = put(key , value, root);
		root.color = BLACK;	//根节点永远是黑色的
	}
	
	public void delete(K key){
		
	}
	
	
	
	
	/*****************************************************************************/
	
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
			t = rotateWithRightChild(t);
		if( isRed(t.leftChild) && isRed(t.leftChild.leftChild) )
			t = rotateWithLeftChild(t);
		if( isRed(t.rightChild) && isRed(t.leftChild) )
			flipColors(t);
		
		t.N = size(t.leftChild) + size(t.rightChild) + 1;
		return t;
	}
	
	private int size(RBNode t){
		if(t != null)
			return t.N;
		return 0;
	}
	
	private RBNode rotateWithRightChild(RBNode k1){
		RBNode k2 = k1.rightChild;
		k1.rightChild = k2.leftChild;
		k2.leftChild = k1;
		k2.color = k1.color;
		k1.color = RED;
		k2.N = k1.N;
		k1.N = size(k1.leftChild) + size(k1.rightChild) + 1;
		return k2;
	}
	
	private RBNode rotateWithLeftChild(RBNode k1){
		RBNode k2 = k1.leftChild;
		k1.leftChild = k2.rightChild;
		k2.rightChild = k1;
		k2.color = k1.color;
		k1.color = RED;
		k2.N = k1.N;
		k1.N = size(k1.leftChild) + size(k1.rightChild) + 1;
		return k2;
	}
	
	private void flipColors(RBNode t){
		t.color = RED;
		t.leftChild.color = BLACK;
		t.rightChild.color = BLACK;
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
		RedBlackBST<Integer, Integer> r = new RedBlackBST<>();
		for(int i=1;i<=9;i++){
			r.put(i, i);
		}
		r.printTree();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
