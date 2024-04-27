package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486_조아름 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N + 2][2]; // 배열에 1부터 저장하고 마지막날+1일까지 일할 수 있으므로 N+2만큼 배열을 만든다.
		int[] dp = new int[N + 2]; // 현재까지의 최대 이익

		for (int i = 1; i < N + 1; i++) { // 입력값은 1부터 N까지
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 기간
			arr[i][1] = Integer.parseInt(st.nextToken()); // 금액
		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N + 1; i++) {
			if (max < dp[i]) {
				max = dp[i];
			}

			int day = i + arr[i][0]; // i일에 상담하면 끝나는 날짜
			if (day <= N + 1) {
				dp[day] = Math.max(dp[day], max + arr[i][1]);
			}
		}
		System.out.println(dp[N+1]);
	}

}
