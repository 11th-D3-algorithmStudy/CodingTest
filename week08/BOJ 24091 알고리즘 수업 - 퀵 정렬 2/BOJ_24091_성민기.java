package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_24091_성민기 {
	
	static int[] arr;
	static int N, K, count, result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken())-1;
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		count = 0;
		
		if(K==0) {
			printArr();
			return;
		}
		quickSort(0, N-1);
		if(count < K) System.out.println(-1);
//		else {
//            for (int i = 0; i < N; i++) {
//                System.out.print(arr[i] + " ");
//            }
//        }
	}
	
	public static void quickSort(int left, int right) {
		int pivot;
		if(left < right) {
			pivot = partition(left, right);
			if(pivot==-1) return;
			quickSort(left, pivot-1);
			quickSort(pivot+1, right);
		}
	}
	
	static int partition(int left, int right) {
		int pivot = arr[left];
		int L = left+1, R = right;
		
		while(L<=R) {
			while(L<=R && arr[L] <= pivot) L++;
			while(arr[R] > pivot) R--;
			
			if(L < R) {
				int tmp = arr[L];
				arr[L] = arr[R];
				arr[R] = tmp;
				count++;
				if (count == K) {
					printArr();
					return -1; // K번째 교환 이후의 인덱스 반환
				}
			}
		}
		
		// 피봇과 R 교환
		int tmp = arr[left];
		arr[left] = arr[R];
		arr[R] = tmp;	
		count++;
		if (count == K) {
			printArr();
			return -1; // K번째 교환 이후의 인덱스 반환
		}
		
		return R;
	}
	
	static void printArr() {
		for(int i=0; i<N; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
