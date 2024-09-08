import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int max = 0;
	static int currMax = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] workdays = new int[N][2];
		int[] dp = new int[N+1];
		for (int i = 0; i < N; i++) {
			String[] info = br.readLine().split(" ");
			workdays[i][0] = Integer.parseInt(info[0]);
			workdays[i][1] = Integer.parseInt(info[1]);
		}
		for (int i = 0; i < N; i++) {
			int dur = workdays[i][0];
			int pay = workdays[i][1];
			currMax = Math.max(currMax, dp[i]);
			if (i + dur < N + 1) {
				dp[i + dur] = Math.max(dp[i + dur], currMax + pay);		
				max = Math.max(max, dp[i + dur]);
			}
		}
		System.out.println(max);
	}
}
