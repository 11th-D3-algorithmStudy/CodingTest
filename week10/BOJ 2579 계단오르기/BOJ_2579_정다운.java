package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2579_정다운 {
	
	static int N; // 계단의 수
	static int[] arr; // 계단 점수 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N];
		
		/*
		 * 
		 * 해설 참고 &
		 * 0부터 하나씩 다 써보면서 해결.......
		 * dp[0] = arr[0]
		 * dp[1] = arr[0]+arr[1]
		 * dp[2] = arr[0]+arr[2] or arr[1]+arr[2]
		 * dp[3] = arr[0]+arr[1]+arr[3] or arr[0]+arr[2]+arr[3] 
		 * dp[4] = arr[0]+arr[1]+arr[3]+arr[4] or arr[1]+arr[2]+arr[4]
		 * 		   or arr[0]+arr[2]+arr[4];
		 * 
		 */
		
		dp[0] = arr[0];
		if (N>1) {
			dp[1] = arr[0]+arr[1];			
		}
		if (N>2) {
			dp[2] = Math.max(arr[0]+arr[2], arr[1]+arr[2]);			
		}
		
		for (int i=3; i<N; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3]+arr[i-1])+arr[i];
		}
		
		System.out.println(dp[N-1]);
	}
}
