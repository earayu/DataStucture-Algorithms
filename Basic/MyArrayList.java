package list;
import java.util.Iterator;


public class MyArrayList<T> implements Iterable<T> {
	
	//默认容量和增量
	private static final int DEFAULT_CAPACITY = 10;
	private static final int CAPACITY_INCREMENT = 5;
	
	//存放的元素数量
	private static int size = 0;
	//数组本身
	private T[] items;
	
	/**
	 * 默认初始化10容量的数组
	 */
	public MyArrayList() {
		super();
		init();
	}
	
	/**
	 * 初始化initSize容量的数组
	 * @param initSize
	 */
	
	public MyArrayList(int initSize) {
		super();
		init(initSize);
	}
	
	@SuppressWarnings("unchecked")
	private void init(){
		items = (T[]) new Object[DEFAULT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	private void init(int initSize){
		items = (T[]) new Object[initSize];
	}
	
	public void clear(){
		size = 0;
		init();
	}
	
	@SuppressWarnings("unchecked")
	public void trimToSize(){
		T[] newItems = (T[]) new Object[size];
		for(int i=0; i<size; i++){
			newItems[i] = items[i];
		}
		items = newItems;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	/**
	 * 在数组末尾添加一个T类型的item
	 * @param item	插入的item值
	 * @return
	 */
	public void add(T item){
		ensureCapacity();
		items[size] = item;
		++size;
	}
	
	/**
	 * 在索引位置插入一个item
	 * @param idx	索引位置
	 * @param item	插入的item的值
	 * @return
	 */
	public void add(int idx, T item){
		if(idx < 0 || idx > size){
			throw new ArrayIndexOutOfBoundsException();
		}
		ensureCapacity();
		for(int i=size;i>idx;i--){
			items[i] = items[i-1];
		}
		items[idx] = item;
		++size;
	}
	
	/**
	 * 获取索引位置的元素
	 * @param idx	索引位置
	 * @return
	 */
	public T get(int idx){
		if(idx < 0 || idx >= size){
			throw new ArrayIndexOutOfBoundsException();
		}
		return (T) items[idx];
	}
	
	/**
	 * 将idx位置的元素设置成item
	 * @param idx	索引位置
	 * @param item	新的item值
	 * @return		旧的idx位置的item值
	 */
	public T set(int idx, T item){
		if(idx < 0 || idx >= size){
			throw new ArrayIndexOutOfBoundsException();
		}
		T oldItem = items[idx];
		items[idx] = item;
		return oldItem;
	}
	
	/**
	 * 移除idx处的元素
	 * @param idx	元素的索引
	 * @return		返回被移除的item
	 */
	public T remove(int idx){
		if(idx < 0 || idx >= size){
			throw new ArrayIndexOutOfBoundsException();
		}
		T oldItem = items[idx];
		for(int i=idx;i<size-1;i++){
			items[i] = items[i+1];
		}
		size--;
		return oldItem;
	}
	
	
	/**
	 * 数组容量不够时增加容量，确保不会越界。重要
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(){
		if(size==items.length){
			T[] newItems = (T[]) new Object[size+CAPACITY_INCREMENT];
			for(int i=0; i<size; i++){
				newItems[i] = items[i];
			}
			items = newItems;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T>{

		private int position = 0;
		
		@Override
		public boolean hasNext() {
			return position < size();
		}

		@Override
		public T next() {
			return (T) items[position++];
		}
		
		public void remove(){
			MyArrayList.this.remove(--position);
		}
	}
}
