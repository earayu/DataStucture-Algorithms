package sort;

public class Merge {

	public Merge() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		while (true) {
			Integer[] r = randomArray();
			sort(r);
			for (int x : r)
				System.out.print(x + " ");
			System.out.println("\n" + isSorted(r) + "\t" + r.length);
			if(!isSorted(r))	break;
		}
	}

	/**
	 * 生成一个长度为0~100的数组，每个元素的值为0~99999的整数。
	 * 
	 * @return
	 */
	public static Integer[] randomArray() {
		Integer[] r = new Integer[(int) (600 * Math.random())];
		for (int i = 0; i < r.length; i++)
			r[i] = (int) (99999 * Math.random());
		return r;
	}

	public static boolean isSorted(Integer[] r) {
		for (int i = 1; i < r.length; i++)
			if (r[i].compareTo(r[i - 1]) < 0)
				return false;
		return true;
	}
	
	private static Integer[] aux;
	
	public static void sort(Integer[] r){
		aux = new Integer[r.length];
		sort(r, 0, r.length-1);
	}
	
	private static void sort(Integer[] r, int low, int high){
		if(low<high){
			int mid = (low+high)/2;
			sort(r,low,mid);
			sort(r,mid+1,high);
			merge(r,low,mid,high);
		}
	}
	
	private static void merge(Integer[] r, int low , int mid, int high){
		int i=low, j=mid+1;
		
		for(int k=low;k<=high;k++)
			aux[k] = r[k];
		
		for(int k=low; k<=high; k++)
			if(i>mid)
				r[k] = aux[j++];
			else if(j>high)
				r[k] = aux[i++];
			else if(aux[j]<aux[i])
				r[k] = aux[j++];
			else
				r[k] = aux[i++];
	}

	
	
	
	
}
