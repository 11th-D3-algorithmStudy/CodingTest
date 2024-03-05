package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 팀 정하기
// 

public class BOJ_10163_장현영 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] result = new int[N]; // 정답이 담길 배열
		int[][] arr = new int[1001][1001]; // 맵
		for (int k = 1; k <= N; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int rgap = Integer.parseInt(st.nextToken());
			int cgap = Integer.parseInt(st.nextToken());
			
			// 해당 넓이 지정
			for (int i = r; i < r + rgap; i++) {
				for (int j = c; j < c + cgap; j++) {
					arr[i][j] = k;
				}
			}
		}

		// 넓이 세기
		int sum = 0;
		for (int k = 1; k <= N; k++) {
			sum = 0;
			for (int i = 0; i < 1001; i++) {
				for (int j = 0; j < 1001; j++) {
					if (arr[i][j] == k) {
						sum++;
					}
				}
			}
			System.out.println(sum);
		}
		
	}
}
