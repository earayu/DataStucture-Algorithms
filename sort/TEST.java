package sort;

import java.util.Scanner;

public class TEST {

	public TEST() {
	}

	public static void main(String[] args) {
		while (true) {
			Integer[] r = randomArray();
//			Integer[] r = arrayIn();
			insertion(r);
			//insertion2(r);
			//selection(r);
			//bubble(r);
			//shell(r);
			//mergeSort(r);
			for (int x : r)
				System.out.print(x + " ");
			System.out.println("\n" + isSorted(r) + "\t" + r.length);
			if(!isSorted(r))	break;
		}
//		Integer[] r = arrayIn();
//		System.out.println("\n" + isSorted(r) + "\t" + r.length);
	}

	/**
	 * 生成一个长度为0~600的数组，每个元素的值为0~99999的整数。
	 * 
	 * @return
	 */
	public static Integer[] randomArray() {
		Integer[] r = new Integer[(int) (600 * Math.random())];
		for (int i = 0; i < r.length; i++)
			r[i] = (int) (99999 * Math.random());
		return r;
	}

	/**
	 * 返回一个数组是否是有序的。
	 * @param r
	 * @return
	 */
	public static boolean isSorted(Integer[] r) {
		for (int i = 1; i < r.length; i++)
			if (r[i].compareTo(r[i - 1]) < 0)
				return false;
		return true;
	}
	
	/**
	 * 从标准输入中读取1000000个整数的数组。
	 * @return
	 */
	public static Integer[] arrayIn(){
		Scanner in = new Scanner(System.in);
		Integer[] r = new Integer[1000000];
		for(int i=0;i<1000000;i++)
			r[i] = in.nextInt();
		return r;
	}

	/*************************************************************************/
	public static void insertion(Integer[] r) {
		int N = r.length;
		int j;
		for (int i = 1; i < N; i++) {
			int p = r[i];
			for (j = i; j > 0 && r[j - 1] > p; j--)
				r[j] = r[j - 1];// 将正确位置之后的元素都向后移动一格。
			r[j] = p;//将第i个元素插入到已经有序的数组
		}
	}

	public static void insertion2(Integer[] r) {
		int N = r.length;
		for (int i = 1; i < N; i++)
			for (int j = i; j > 0 && r[j] < r[j - 1]; j--) {
				int temp = r[j];
				r[j] = r[j - 1];
				r[j - 1] = temp;
			}
	}

	public static void selection(Integer[] r) {
		int N = r.length;
		for (int i = 0; i < N - 1; i++) {
			int min = i;//已知最小元素的索引
			for (int j = i + 1; j < N; j++)
				if (r[min] > r[j])//如果找到更小的元素，更新索引
					min = j;
			int temp = r[i];//交换位置
			r[i] = r[min];
			r[min] = temp;
		}
	}

	public static void bubble(Integer[] r) {
		int N = r.length;
		for (int i = 0; i < N - 1; i++) //第i遍，每次沉下一个最大的元素
			for (int j = 0; j < N - 1 - i; j++) //扫描数组
				if (r[j] > r[j + 1]) {//如果逆序就交换
					int temp = r[j];
					r[j] = r[j + 1];
					r[j + 1] = temp;
				}
	}

	public static void shell(Integer[] r) {
		int N = r.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {//i初始化h以避免数组越界
				for (int j = i; j >= h && r[j] < r[j - h]; j -= h) {
					int temp = r[j];
					r[j] = r[j - h];
					r[j - h] = temp;
				}
			}
			h /= 3;
		}
	}
	
	private static Integer[] aux;
	
	public static void mergeSort(Integer[] r){
		aux = new Integer[r.length];
		mergeSort(r, 0, r.length - 1);
	}
	
	private static void mergeSort(Integer[] r, int left, int right){
		if(right<=left)//
			return;
		int mid = (left+right)/2;
		mergeSort(r, left, mid);
		mergeSort(r, mid+1, right);
		merge(r, left, mid, right);
	}
	
	private static void merge(Integer[] r, int left, int mid, int right){
		int i=left, j=mid+1;
		
		for(int k=left; k<=right; k++)
			aux[k] = r[k];
		
		for(int k=left; k<=right; k++)
			if(i>mid)//
				r[k] = aux[j++];
			else if(j>right)
				r[k] = aux[i++];
			else if(aux[i]<aux[j])//
				r[k] = aux[i++];
			else
				r[k] = aux[j++];
	}
	
	
	
	
	

}
