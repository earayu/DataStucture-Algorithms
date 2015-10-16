package list;

public class MyArrayQuene<T> {

	private T[] items;
	private int size;
	private int front;
	private int back;
	private static final int DEFAULT_CAPACITY = 100;
	
	public MyArrayQuene() {
		init();
	}

	public void init(){
		size = 0;
		items = (T[]) new Object[DEFAULT_CAPACITY];
		front = 0;
		back = -1;
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public void clear(){
		init();
	}
	
	public boolean enQuene(T item){
		if(back==items.length-1){
			back = -1;
		}
		items[++back] = item;
		++size;
		return true;
	}
	
	public T deQuene(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("空队列异常");
		}
		if(front==items.length){
			front = 0;
		}
		--size;
		return items[front++];
	}
}	
