package week12;

import java.io.*;
import java.util.*;

public class BOJ_16918_장현영 {
	// 봄버맨
	// n 홀수 짝수 구분해서 진행
	// 홀수 일때 bfs로 상하좌우 찾아서 터트려준 arr 출력하기
	// 짝수면 o로 채운 것 출력하기
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] arr;
	static boolean[][] visited;
	static Queue<int[]> bombs;
	static int R, C, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}

		bombs = new LinkedList<>();
		for (int k = 2; k <= N; k++) {
			if (k % 2 == 1) {
				for (int a = 0; a < R; a++) {
					for (int b = 0; b < C; b++) {
						if (arr[a][b] == 'O')
							bombs.offer(new int[] { a, b });
					}
				}
				for (char[] each : arr) {
					Arrays.fill(each, 'O');
				}
				bfs(); // O->.
			}
			if (N % 2 == 0) { // O로 채운 그래프 출력
				for (char[] each : arr) {
					Arrays.fill(each, 'O');
				}
			}
		}
		// 최종 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}

	}

	static void bfs() {
		while (!bombs.isEmpty()) {
			int[] bomb = bombs.poll();
			int x = bomb[0];
			int y = bomb[1];
			arr[x][y] = '.';
			for (int i = 0; i < 4; i++) {
				int nx = x + dr[i];
				int ny = y + dc[i];
				if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
					if (arr[nx][ny] == 'O') {
						arr[nx][ny] = '.';
					}
				}
			}
		}

	}
}
