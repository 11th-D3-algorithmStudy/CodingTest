package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11726_정다운 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		// 큰 수일거라 생각해서 배열에 저장할 수 있는 값을 long으로 설정했지만 틀림......
		// 경계값이 long을 넘어가는 엄~~청 큰 수라고 한다
		// 배열에 % 10007 연산한 값을 저장해주자 
//		long[] dp = new long[n+1];
		int[] dp = new int[n+1];
		
		/*
		 * 
		 * dp[0] = 0
		 * dp[1] = 1
		 * dp[2] = 2
		 * dp[3] = 3
		 * dp[4] = 5
		 * ...
		 * dp[n] = dp[n-2]+dp[n-1]
		 * 
		 */
		
		dp[1] = 1;
		if (n >= 2) {
			dp[2] = 2;
		}
		
		for (int i=3; i<=n; i++) {
			dp[i] = (dp[i-2]+dp[i-1])%10007;
		}
		
		System.out.println(dp[n]);
	}
}
