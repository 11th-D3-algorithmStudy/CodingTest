package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11722_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] seq = new int[N];
		
		for (int i = 0; i < N; i ++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(Arrays.toString(seq));
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (seq[j] > seq[i]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
		}
		
		Arrays.sort(dp);
		System.out.println(dp[N-1]);
	}
}
