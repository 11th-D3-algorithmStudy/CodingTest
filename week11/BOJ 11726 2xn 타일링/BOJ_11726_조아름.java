package week11;

import java.util.Scanner;

public class BOJ_11726_조아름 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] dp = new int[n+1];
		
		dp[0] = 0;
		dp[1] = 1;
		
		for(int i=2;i<=n;i++) {
			if(i/2==0) {
				dp[i] = dp[i-1]+1;
			}
		}
		
		for(int i=2;i<=n;i++) {
			if(i%2==0) {
				dp[i] = dp[i-1]+1;
			}
			dp[i] = dp[i-1]+(i/2);
		}
	}

}
