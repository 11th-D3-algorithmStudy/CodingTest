package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942_이서영 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		boolean[][] palin = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			palin[i][i] = true;

			if (i < N - 1) {
				int start = i;
				int end = i + 1;
				while (start >= 0 && end < N && seq[start] == seq[end]) {
					palin[start--][end++] = true;
				}
				while (start >= 0 && end < N) {
					palin[start--][end++] = false;
				}
			}

			if (i < N - 2) {
				int start = i;
				int end = i + 2;
				while (start >= 0 && end < N && seq[start] == seq[end]) {
					palin[start--][end++] = true;
				}
				while (start >= 0 && end < N) {
					palin[start--][end++] = false;
				}
			}
		}
		int ops = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < ops; j++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			if (palin[s][e]) {
				sb.append("1" + "\n");
			} else {
				sb.append("0" + "\n");
			}
		}
		System.out.println(sb);
	}

}
