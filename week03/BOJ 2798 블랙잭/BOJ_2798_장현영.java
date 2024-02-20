package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798_장현영 {
	// 블랙잭
	// 세 카드 합 <=m 만들기
	// n이 100이므로 N^3으로 도전
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer =0;
		
		for (int i = 0; i < n-2; i++) {
			for (int j = i + 1; j < n-1; j++) {
				for (int k = j + 1; k < n; k++) {
					int temp = arr[i]+arr[j]+arr[k];
					if (temp <= m)
						answer = Math.max(answer, temp);
				}
			}
		}
		System.out.println(answer);
	}
}
