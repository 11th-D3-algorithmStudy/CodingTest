package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10163_조아름 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] arr = new int[1001][1001];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine()); // 값 입력
		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;

		for (int c = 1; c <= tc; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 값 입력
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());

			for (int i = startX; i < startX + endX; i++) { // 색종이 번호에 맞는 값 입력, 이때 0부터 시작하지 않도록 주의. 
				for (int j = startY; j < startY + endY; j++) {// 왜나하면 배열의 기본값이 0이기 때문
					arr[i][j] = c;
				}
			}
		} 

		for (int c = 1; c <= tc; c++) {// 배열을 돌아다니면서 색종이 숫자에 맞는 값출력
			int cnt = 0;
			
			for (int i = 0; i < 1001; i++) {
				for (int j = 0; j < 1001; j++) {
					if(arr[i][j]==c) {
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}

	}

}
