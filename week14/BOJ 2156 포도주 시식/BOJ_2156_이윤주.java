import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2156_이윤주 {
	// 포도주 시식
	// 연속 3잔 불가
	// 가장 많은 양의 포도주를 마실 때의 총 포도주양 구하기 -> dp[n-1]
	// 최적해 구하는 DP
	// 현재 위치(맨 오른쪽) 기준으로 3가지 경우 중
	// OOX OXO XOO
	// 어떤 경우가 가장 많이 먹을 수 있는지 판단
	// 점화식
	// dp[n] = Math.max(3가지 경우)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 포도주 잔 개수 1~10000
		int[] wine = new int[N];
		for (int i = 0; i < N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[N]; // 최적해를 구하는 dp 배열

		// OOX인 경우
		// dp[i] = dp[i - 1]
		// OXO인 경우
		// dp[i] = dp[i - 2] + wine[i]
		// XOO인 경우
		// dp[i] = dp[i - 3] + wine[i - 1] + wine[i]

		// 3가지 경우 중 max로 갱신하기

		// i - 3 이 점화식에 있으므로 i = 3부터 시작
		dp[0] = wine[0];
		
		//outofindex 에러 -> N이 1인 경우에 dp[1], dp[2] 설정하려고 하면 에러남!!
		if (N > 1)
			dp[1] = wine[0] + wine[1];
		if (N > 2)
			dp[2] = Math.max(dp[1], Math.max(wine[0] + wine[2], wine[1] + wine[2]));

		for (int i = 3; i < N; i++) {

			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
		}

		System.out.println(dp[N - 1]);
	}
}
