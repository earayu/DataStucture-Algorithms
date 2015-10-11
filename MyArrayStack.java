package list;
//待测试
public class MyArrayStack<T> {

	private T[] items;
	private int pointer;
	private static final int DEFAULT_CAPACITY = 20;
	private static final int CAPACITY_INCREMENT = 20;
	
	public MyArrayStack(){
		init();
	}
	/**
	 * 初始化栈，栈顶指向-1表明栈空， 设置默认容量。
	 */
	private void init(){
		items = (T[]) new Object[DEFAULT_CAPACITY];
		pointer = -1;
	}
	/**
	 * 如果栈顶指向-1，则表示栈空
	 * @return
	 */
	public boolean isEmpty(){
		return pointer == -1;
	}
	/**
	 * 将item压入栈中， 栈顶+1
	 * @param item
	 * @return
	 */
	public boolean push(T item){
		ensureCapacity();
		items[++pointer] = item;
		return true;
	}
	/**
	 * 弹出栈顶元素， 栈顶-1
	 * @return
	 */
	public T pop(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("the Stack is empty!");
		}
		return items[pointer--];
	}
	/**
	 * 返回栈顶元素
	 * @return
	 */
	public T top(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("the Stack is empty!");
		}
		return items[pointer];
	}
	/**
	 * 确保容量足够
	 */
	private void ensureCapacity(){
		if(pointer+1==items.length){
			T[] newItems = (T[]) new Object[pointer+1+CAPACITY_INCREMENT];
			for(int i=0; i<pointer+1; i++){
				newItems[i] = items[i];
			}
			items = newItems;
		}
	}
	/**
	 * 释放多余的容量
	 */
	public void trimToSize(){
		T[] newItems = (T[]) new Object[pointer+1];
		for(int i=0; i<pointer+1; i++){
			newItems[i] = items[i];
		}
		items = newItems;
	}
}
