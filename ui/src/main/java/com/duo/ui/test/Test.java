package com.duo.ui.test;

import com.alibaba.fastjson.JSON;

public class Test {
	
	public static void main(String[] args) {
		
//		int [] arrA = new int[]{1,2,4,50,80};
//		int [] arrB = new int[]{10,20,60,90};
//		int [] arrResult = changeArray(arrA,arrB);
//		System.out.println(JSON.toJSON(arrResult));
		
		
		sortArr();
	}
	
	
	private static void sortArr(){
		int[] A = { 1 , 3 , -1 ,0 , 2 , 1 , -4 , 2 , 0 ,1 ,10};
		int j = A.length;
		for (int i = 0; i < j; i++) {
			int temp = 0;
			if(A[i]<=0){
				temp = A[j-1];
				A[j-1] = A[i];
				A[i] = temp;
				j--;
				i--;
			}
		}
		System.out.println(JSON.toJSON(A));
		
	}
	
	
	private static int[] changeArray(int[] arrA,int[] arrB){
		int length = arrA.length + arrB.length;
		int [] arrResult = new int[length];
		int x = 0; 
		int y = 0;
		for (int i = 0; i < arrResult.length; i++) {
			//if(x < arrA.length && (arrA[x] <= arrB[y] || y == arrB.length)){ 如果将比较放前面会导致越界，用短路避免
			if(x < arrA.length && (y == arrB.length || arrA[x] <= arrB[y])){
				arrResult[i] = arrA[x];
				x++;
			}else if(y < arrB.length && (x == arrA.length || arrA[x] > arrB[y])){
				arrResult[i] = arrB[y];
				y++;
			}
		}
		return arrResult;
		
		
	}

}
