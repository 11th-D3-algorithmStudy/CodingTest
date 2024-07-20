package week17;

import java.io.*;
import java.util.*;

public class 가장긴감소하는부분수열 {
	
	static int N;
	static int max = 0;
	static int[] arr, dp;
	
	public static void main(String[] args) throws Exception {
		// https://blackvill.tistory.com/208 참고
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<=N; i++) {
			dp[i] = 1;
			for (int j=1; j<i; j++) {
				if (arr[j] > arr[i]) { // 이전 값이 더 클때
					dp[i] = Math.max(dp[i], dp[j]+1); // 현재 길이 & 이전에 연결된 길이의 최대값+1 과 비교
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
