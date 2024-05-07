package week12;

import java.io.*;
import java.util.*;

public class BOJ_16918_봄버맨_이규빈 {
	static int R, C, N;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	
	// 델타 배열 - 상하좌우 + 해당칸 포함
	static int[] dr = {-1, 1, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		// ----- 입력 끝 -----
		
		// N이 짝수라면, 전부 O를 채워 출력
		if (N % 2 == 0) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					sb.append('O');
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return; // 메인 메서드 종료
		}
		
		// 이 구역에 들어오면, N은 홀수
		solve(1);
	}

	
	// 최악의 경우의 시간복잡도 예측
	// 100(time이 홀수일때만 계산하므로) * 200*200(map) * 2번 순회
	// = 약 8백만 -> 할만 할듯?
	static void solve(int time) {
		// base case
		if (time == N) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return;
		}
		
		
		
		// recursive case
		boolean[][] exploded = new boolean[R][C];
		
		// map 1차 순회
		// - 'O'면 폭발 작업 수행
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'O') {
					// 상하좌우 및 해당 칸 폭발
					for (int d = 0; d < 5; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (!inMap(nr, nc))  continue;  // 경계조건
						
						exploded[nr][nc] = true;
					}
				}
			}
		} // 1차 순회 끝
		
		// map 2차 순회
		// - 폭발한 칸이면 '.'를, 폭발하지 않은 칸이면 'O'를 채우기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (exploded[r][c])  map[r][c] = '.';
				else  				 map[r][c] = 'O';
			}
		} // 2차 순회 끝
		
		solve(time + 2);
	}


	static boolean inMap(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
