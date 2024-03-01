package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959_조아름 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] arrA = new int[N];
			int[] arrB = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arrA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arrB[i] = Integer.parseInt(st.nextToken());
			}

			int max = Integer.MIN_VALUE;

			if (N < M) {
				for (int i = 0; i <= (M - N); i++) {
					int sum = 0; // i 값이 달라질 떄마다 sum 값이 나와야 하기 때문에 for문 안에서 sum 초기화
					for (int j = 0; j < N; j++) {
						sum += arrA[j] * arrB[i + j];
					}
					max = Math.max(max, sum); // 마찬가지로 max 값도 for문 안에서 초기화
				}
			} else if (N > M) {
				for (int i = 0; i <= (N - M); i++) {
					int sum = 0;
					for (int j = 0; j < M; j++) {
						sum += arrA[i + j] * arrB[j];
					}
					max = Math.max(max, sum);
				}
			} else {
				int sum = 0;
				for (int i = 0; i < N; i++) {
					sum += arrA[i] * arrB[i];
				}
				max = Math.max(max, sum);
			}

			System.out.println("#" + (tc + 1) + " " + max);
		}
	}

}