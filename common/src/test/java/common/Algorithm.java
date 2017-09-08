package common;

import java.util.Arrays;

public class Algorithm {

	
	public static void main(String[] args) {
		  int [] arr={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};   
		long s1 = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		insertSort(arr);
		long s2 = System.currentTimeMillis();
		System.out.println(s2);
		System.out.println(s2-s1);
		shellSort(arr);
		
		long s3 = System.currentTimeMillis();
		System.out.println(s3);
		System.out.println(s3-s2);
	}
	
	/**
	 * 插入排序
	 * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排 
	 * 好顺序的，现在要把第n 个数插到前面的有序数中，使得这 n个数 
	 * 也是排好顺序的。如此反复循环，直到全部排好顺序。 
	 * 2017年8月17日
	 * By duoduo
	 * @param arr
	 */
	public static int[] insertSort(int[] arr){
		int temp = 0 ;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			for (int j = i; j > 0; j--) {
				if(arr[j -1 ] > arr[j]){   
					arr[j] = arr[j -1];
					arr[j-1] = temp;
				}
				
			}
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}
	
	/**
	 * 希尔排序（最小增量排序）
	 * 基本思想：算法先将要排序的一组数按某个增量 d（n/2,n为要排序数的个数）分成若
	 * 干组，每组中记录的下标相差 d.对每组中全部元素进行直接插入排序，然后再用一个较小
	 * 的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到 1 时，进行直接
	 * 插入排序后，排序完成。
	 * 2017年8月17日
	 * By duoduo
	 * @param arr
	 * @return
	 */
	public static int[] shellSort(int[] arr){
		
		int d1 = arr.length/2;
		int temp = 0;
		//递归循环选其一即可 
		shellLegth(arr,d1,temp);//递归
		//循环
//		int i = 0;
//		while (d1>1) {
//			if(arr[i] > arr[i+d1]){
//				temp = arr[i+d1];
//				arr[i+d1] = arr[i];
//				arr[i] = temp;
//			}
//			i++;
//			d1 = d1/2;
//		}
		
		System.out.println(Arrays.toString(arr));
		return arr;
	}
	
	
	private static void shellLegth(int[] arr,int d1,int temp){
		for (int i = 0; i < d1; i++) {
			if(arr[i] > arr[i+d1]){
				temp = arr[i+d1];
				arr[i+d1] = arr[i];
				arr[i] = temp;
			}
		}
		if(d1==1){
			
		}else{
			shellLegth(arr,d1/2,temp);
		}
	}
}
