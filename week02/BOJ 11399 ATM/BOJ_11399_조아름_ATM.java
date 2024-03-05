package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399_조아름_ATM {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 값 받아서 배열에 넣기
		}
		
		Arrays.sort(arr); // 최솟값을 구하기 위해 배열 정렬
		
		int[] newArr = new int[N];
		for(int i=0;i<N;i++) {
			if(i==0) {
				newArr[i] = arr[0]; // 새로운 배열의 맨 처음 값은 이전 배열과 동일
			}else if(i>0){
				newArr[i] = arr[i] + newArr[i-1]; // 최솟값의 합은 이전 배열과 새로운 배열의 합
			}
		}
		
		int sum = 0;
		for(int i=0;i<N;i++) {
			sum += newArr[i];
		}
		
		System.out.println(sum);
	}

}
