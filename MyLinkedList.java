package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * MyLinkedList
 * @author earayu
 * @param <T>
 */
public class MyLinkedList<T> implements Iterable<T> {
	//双向链表的节点
	private static class Node<T>{
		public T data;
		public Node<T> prevNode;
		public Node<T> nextNode;
		public Node(T data, Node<T> prevNode, Node<T> nextNode) {
			this.data = data;
			this.prevNode = prevNode;
			this.nextNode = nextNode;
		}
	}
	//定义链表的长度，修改次数，头结点和尾节点。
	private int size;
	private int changedTimes;
	private Node<T> firstNode;
	private Node<T> lastNode;
	/**
	 * 调用init()方法，初始化链表
	 */
	public MyLinkedList(){
		init();
	}
	/**
	 * 初始化MyLinkedList,将长度，被改变次数，头结点和尾节点都初始化。以免发生错误。
	 */
	private void init(){
		size = 0;
		changedTimes = 0;
		firstNode = new Node<T>(null, null, null);
		lastNode = new Node<T>(null, null, null);
		firstNode.nextNode = lastNode;
		lastNode.prevNode = firstNode;
	}
	/**
	 * 将链表清空，修改次数+1
	 */
	public void clear(){
		size = 0;
		changedTimes++;
		firstNode = new Node<T>(null, null, null);
		lastNode = new Node<T>(null, null, null);
		firstNode.nextNode = lastNode;
		lastNode.prevNode = firstNode;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * 返回当前链表长度（不含头尾节点）
	 * @return
	 */
	public int size(){
		return size;
	}
	/**
	 * 将idx索引出节点的值设置成newData
	 * @param idx
	 * @param newData
	 * @return
	 */
	public T set(int idx, T newData){
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newData;
		return oldVal;
	}
	/**
	 * 删除制定索引出的节点，索引从0开始
	 * @param idx
	 * @return
	 */
	public T remove(int idx){
		return remove(getNode(idx));
	}
	
	private T remove(Node<T> p){
		p.nextNode.prevNode = p.prevNode;
		p.prevNode.nextNode = p.nextNode;
		--size;
		++changedTimes;
		return p.data;
	}
	
	/**
	 * 将data添加到链表尾部。
	 * @param data
	 * @return
	 */
	public boolean add(T data){
		add(size, data);
		return true;
	}
	/**
	 * 将链表添加到idx索引出，索引从0开始
	 * @param idx
	 * @param data
	 * @return
	 */
	public boolean add(int idx, T data){
		if(idx < 0 || idx > size){
			throw new IndexOutOfBoundsException();
		}
		Node<T> beforeNode = firstNode;
		for(int i=0;i<size;i++){
			beforeNode = beforeNode.nextNode;
		}
		Node<T> afterNode = beforeNode.nextNode;
		beforeNode.nextNode = new Node(data, beforeNode, afterNode);
		afterNode.prevNode = beforeNode.nextNode;
		++size;
		++changedTimes;
		return true;
	}
	/**
	 * 获取idx索引出的节点的数据，索引从0开始
	 * @param idx
	 * @return
	 */
	public T get(int idx){
		return getNode(idx).data;
	}
	/**
	 * 获取idx索引出的节点
	 * @param idx
	 * @return
	 */
	public Node<T> getNode(int idx){
		if(idx < 0 || idx >= size){
			throw new IndexOutOfBoundsException();
		}
		Node<T> p;
		if(idx < size/2){
			p = firstNode.nextNode;
			for(int i=0; i<idx; i++){
				p = p.nextNode;
			}
		}else{
			p = lastNode;
			for(int i=size; i>idx; i--){
				p = p.prevNode;
			}
		}
		return p;
	}
	
	/**
	 * 返回一个迭代器
	 */
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	/**
	 * 
	 * @author earayu
	 *
	 */
	private class LinkedListIterator implements Iterator<T>{
		//迭代器的当前指向节点，修改次数，可否移除
		private Node<T> currentNode = firstNode.nextNode;
		private int itChangedTimes = changedTimes;
		private boolean removable = false;
		
		/**
		 * 返回是否有下一个节点（不包含尾节点）。
		 */
		@Override
		public boolean hasNext() {
			return currentNode != lastNode;
		}
		/**
		 * 返回当前指向节点的下一个节点的值，并将指向的节点后移一位。
		 */
		@Override
		public T next() {
			if(changedTimes != itChangedTimes){
				throw new ConcurrentModificationException();
			}
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			
			T nextItem = currentNode.data;
			currentNode = currentNode.nextNode;
			removable = true;
			return nextItem;
		}
		/**
		 * 调用外部类的remove方法，移除上一个访问过的节点。即，当前指向节点的前一个节点。
		 */
		public void remove(){
			if(changedTimes != itChangedTimes){
				throw new ConcurrentModificationException();
			}
			if(!removable){
				throw new IllegalStateException();
			}
			
			MyLinkedList.this.remove(currentNode.prevNode);
			removable = false;
			++itChangedTimes;
		}
	}
	
	public static void main(String[] args) {
		MyLinkedList<Integer> mll = new MyLinkedList<>();
		for(int i=0; i<50; i++){
			mll.add(i);
		}
		Node<Integer> a = mll.getNode(10);
		Node<Integer> b = a.nextNode;
		Node<Integer> c = b.nextNode;
		Node<Integer> d = c.nextNode;
		a.nextNode = c;
		b.nextNode = d;
		c.nextNode = b;
		for(int i=0; i<22; i++){
			System.out.println(mll.get(i));
		}
	}
}
