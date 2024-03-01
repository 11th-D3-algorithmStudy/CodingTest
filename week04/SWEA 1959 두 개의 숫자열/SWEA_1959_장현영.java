package week04;

import java.io.*;
import java.util.*;

public class SWEA_1959_장현영 {

	/*
	 * 두 개의 숫자열 n-m+1 (n>m)가짓수가 전체 가짓수 두 개의 arr을 확인하는데, 숫자가 더 작은 배열의 index 이동시켜서
	 * 계산해주는 방식으로 진행 풀이 시간 25분
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] nArr = new int[n];
			int[] mArr = new int[m];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nArr[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				mArr[i] = Integer.parseInt(st.nextToken());
			}

			// 경우의 수 k

			int k = Math.abs(m - n) + 1;
			int sum = 0;
			int answer = 0;

			// 큰 배열에 경우의 수만큼 더해서 계산해주기
			// 만약에 n = 4, m = 6이면
			// 경우의 수는 1
			// n 배열은 항상 고정으로 0~3
			// m 배열이 0~3, 1~4 2~5만큼 더해져야 하므로
			// 큰 배열에 경우의 수 만큼을 더해줌

			// 구조가 흡사하여 코드 중복 간결하게 할 수 있을 듯
			if (n > m) {
				for (int idx = 0; idx < k; idx++) {
					for (int j = 0; j < m; j++)
						sum += mArr[j] * nArr[j + idx];
					answer = Math.max(answer, sum);
					sum = 0;
				}
			} else {
				for (int idx = 0; idx < k; idx++) {
					for (int j = 0; j < n; j++)
						sum += mArr[j + idx] * nArr[j];
					answer = Math.max(answer, sum);
					sum = 0;
				}
			}
			System.out.println("#" + t + " " + answer);
		}

	}

}
