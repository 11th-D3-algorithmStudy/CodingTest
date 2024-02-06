package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10163_색종이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] arr = new int[1001][1001];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;

		for (int c = 0; c < tc; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());

			for (int i = startX; i < startX + endX; i++) {
				for (int j = startY; j < startY + endY; j++) {
					arr[i][j] += 1;
				}
			}

			for (int i = 0; i < 1001; i++) {
				for (int j = 0; j < 1001; j++) {
					if (arr[i][j] > 1) {
						arr[i][j] = 0;
					}
				}
			} // 만약 배열 값이 2이상이면 0으로 초기화 함.

		} // 색종이에 1을 채워 줌

		for (int c = 0; c < tc; c++) {
			int sum = 0;
			
			for (int i = startX; i < startX + endX; i++) {
				for (int j = startY; j < startY + endY; j++) {
					sum += arr[i][j];
				}
			}
			
			System.out.println(sum);
		}

	}

}
