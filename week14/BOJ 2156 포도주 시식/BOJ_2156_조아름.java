package week14;

import java.util.Scanner;

public class BOJ_2156_조아름 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] arr = new int[n+1];
		int[] dp = new int[n+1];
		
		for(int i=1;i<=n;i++) {
			arr[i] = sc.nextInt();
		}
		
		dp[1] = arr[1];
		if(n>1) { // 만약 n이 1일 때는 아래 구문이 실행될 수 없으므로 if문을 걸어준다.
			dp[2] = arr[1] + arr[2];
		}
		
		// 먹을 수 있는 경우는 0번 연속으로 먹기, 1번 연속으로 먹기, 2번 연속으로 먹기
		// 0번 연속 : dp[n] = dp[n-1]
		// 1번 연속 : dp[n] = dp[n-2] + arr[n]
		// 2번 연속 : dp[n] = dp[n-3] + arr[n-1] + arr[n]
		
		for(int i=3;i<=n;i++) { 
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]));
		}
		
		System.out.println(dp[n]); 
		
	}

}
