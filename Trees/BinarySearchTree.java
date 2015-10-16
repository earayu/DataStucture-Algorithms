package test;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T>{

	//根节点
	private BiNode<T> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	/**
	 * BST的（内嵌）节点类， data存储数据， size为该节点拥有的子孙节点数+1,。
	 * @author earayu
	 *
	 * @param <T>
	 */
	private static class BiNode<T>{
		T data;
		int size;
		BiNode<T> leftChild;
		BiNode<T> rightChild;
		
		public BiNode(T data, BiNode<T> leftChild, BiNode<T> rightChild){
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.size = 1;
		}
	}
	
	
	/**********************************公共方法***************************************/
	
	/**
	 * 将data存入BST
	 * @param data
	 */
	public void put(T data){
		root = put(data, root);
	}
	
	/**
	 * 返回BST中值为data的节点
	 * @param data
	 * @return
	 */
	public BiNode<T> get(T data){
		return get(data, root);
	}
	
	/**
	 * 删除BST中值为data的节点
	 * @param data
	 */
	public void delete(T data){
		root = delete(data, root);
	}
	
	/**
	 * 返回是否含有值为data的节点
	 * @param data
	 * @return
	 */
	public boolean contains(T data){
		return get(data) != null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	//返回BST中节点的数量
	public int size(){
		if(root == null)	return 0;
		return root.size;
	}

	/**
	 * 先序打印BST
	 */
	public void printTree(){
		if(isEmpty())	System.out.println("Empty Tree!");
		printTree(root);
	}
	
	/**
	 * 返回值为data的节点在BST中的位置（升序）,索引从0开始
	 * @param data
	 * @return
	 */
	public int rank(T data){
		return rank(data, root);
	}
	
	/**
	 * 返回在BST中索引为i的元素（升序），索引从0开始
	 * @param i
	 * @return
	 */
	public T select(int i){
		if(i<0 || i>=size())	return null;
		return select(i, root).data;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new BSTIterator();
	}


	/*******************************私有方法******************************************/
	
	private int rank(T data, BiNode<T> t){
		if(t == null)	return 0;
		int cmp = data.compareTo(t.data);
		if(cmp<0)
			return rank(data, t.leftChild);
		else if(cmp>0)
			return size(t.leftChild) + 1 + rank(data, t.rightChild);
		else
			return size(t.leftChild);
	}
	
	private BiNode<T> select(int i, BiNode<T> t){
		if(t == null)	return null;
		int j = size(t.leftChild);
		if(i<j)
			return select(i, t.leftChild);
		else if(i>j)
			return select(i-j-1, t.rightChild);
		else
			return t;
	}
	
	private int size(BiNode<T> t){
		if(t == null)	return 0;
		return t.size;
	}
	
	/**
	 * 将data存入BSTt中的对应位置，然后返回t。
	 * @param data
	 * @param t
	 * @return
	 */
	private BiNode<T> put(T data, BiNode<T> t){
		if(t == null)	return new BiNode<T>(data, null, null);
		
		int cmp = data.compareTo(t.data);
		if(cmp<0)
			t.leftChild = put(data, t.leftChild);
		else if(cmp>0)
			t.rightChild = put(data, t.rightChild);
		else
			t.data = data;
		t.size = size(t.leftChild) + size(t.rightChild) + 1;
		return t;
	}
	
	/**
	 * 根据data值， 返回存放该值得节点。 若不存在，则返回 null
	 * @param data
	 * @param t
	 * @return
	 */
	private BiNode<T> get(T data, BiNode<T> t){
		if(t == null)	return null;
		
		int cmp = data.compareTo(t.data);
		if(cmp<0)
			return get(data, t.leftChild);
		else if(cmp>0)
			return get(data, t.rightChild);
		else
			return t;
	}
	
	/**
	 * 将data从BSTt中删除，然后返回t。
	 * @param data
	 * @param t
	 * @return
	 */
	private BiNode<T> delete(T data, BiNode<T> t){
		if(t == null) return null;
		
		int cmp = data.compareTo(t.data);
		if(cmp<0)
			t.leftChild = delete(data, t.leftChild);
		else if(cmp>0)
			t.rightChild = delete(data, t.rightChild);
		else{
			if(!(t.leftChild!=null && t.rightChild!=null))
				return (t.leftChild==null)?t.rightChild:t.leftChild;
			else{
				//用t右子树的最小元素替代，然后删除t右子树的最小元素
				BiNode<T> minNode = findMin(t.rightChild);
				t.data = minNode.data;
				t.rightChild = delete(minNode.data, t.rightChild);
			}
		}
		
		t.size = size(t.leftChild) + size(t.rightChild) + 1;
		return t;
	}
	
	/**
	 * 返回BSTt中的最小的节点
	 * @param t
	 * @return
	 */
	private BiNode<T> findMin(BiNode<T> t){
		if(t == null)	return null;
		while(t.leftChild != null)
			t = t.leftChild;
		return t;
	}
	
	private void printTree(BiNode<T> t){
		if(t != null){
			System.out.println(t.data);
			printTree(t.leftChild);
			printTree(t.rightChild);
		}
	}
	
	
	private class BSTIterator implements Iterator<T>{
		
		private int position = 0;
		
		@Override
		public boolean hasNext() {
			return position < BinarySearchTree.this.size();
		}

		@Override
		public T next() {
			return  BinarySearchTree.this.get(select(position++)).data;
		}
	}
}
