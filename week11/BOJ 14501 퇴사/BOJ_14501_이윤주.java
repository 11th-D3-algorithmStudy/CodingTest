package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_이윤주 {
	// 퇴사

	// 1일~N일까지 최대한 많은 상담을 할 때의 최대 수익 구하기

	static int N;
	static int[] T, P;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 날짜 수 1~15
		T = new int[N + 1]; // 걸리는 기간 1~5
		P = new int[N + 1]; // 받을 수 있는 금액 1~1000

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		max = 0;

		findMax(1, 0);

		System.out.println(max);
	}

	// idx : 현재날짜 상담 받을지 체크
	private static void findMax(int idx, int sum) {
		// 기저조건
		if (idx >= N + 1) { 
			max = Math.max(max, sum);
			return;
		}

		if (idx + T[idx] <= N + 1)
			//N일째에 1일짜리 일을 하면 N+1일까지 가니깐
			//idx가 N+1가 될때까지는 상담을 끝마칠 수 있다!
			// 상담을 끝마칠 수 있다면 오늘 날짜 상담 받는다!
			findMax(idx + T[idx], sum + P[idx]);
		else
			// 오늘 상담 받으면 N을 지나버린다...!
			findMax(idx + T[idx], sum);

		// 오늘 그냥 스킵
		findMax(idx + 1, sum);
	}

}
