package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15486_이서영 {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] work = new int[N];
		int[] pay = new int[N];
		dp = new int[N + 1];
		for (int i = 0; i < N; i++) {
			String[] info = br.readLine().split(" ");
			int w = Integer.parseInt(info[0]);
			int p = Integer.parseInt(info[1]);
			work[i] = w;
			pay[i] = p;
		}

		dp(work, pay);

	}

	static void dp(int[] work, int[] pay) {
		int max = -1;
		for (int i = 0; i < N; i++) {
			int lastDay = i + work[i];
			max = Math.max(dp[i], max);
			if (lastDay <= N) {
				dp[lastDay] = Math.max(max + pay[i], dp[lastDay]);
			}
		}
		System.out.println(Math.max(max, dp[N]));
	}
}
