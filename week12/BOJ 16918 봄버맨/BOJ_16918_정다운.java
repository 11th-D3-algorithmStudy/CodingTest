package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_정다운 {

	static int R, C, N; // map 크기, 최종 확인할 시간
	static int[][] map;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// 폭탄 자리에 폭탄 터질 시간 입력
		// 짝수 시간일때 빈자리(0)에 폭탄 놓기 
		// 홀수 시간일때 폭탄 & 상하좌우 빈자리로 바꾸기 

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			String input = br.readLine();
			for (int c = 0; c < C; c++) {
				if (input.charAt(c) == 'O') {
					map[r][c] = 3; // 맨 처음 폭탄 터지는 시간 3초
				}
			}
		}

		if (N == 1) {
			// map 그대로 출력
			printMap();
		} else {
			bomberman(2);
			printMap();
		}

	}

	static void printMap() {
		// map 출력하기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0) {
					sb.append('.');
				} else {
					sb.append('O');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	// t : 현재 시간
	static void bomberman(int t) {
		// 짝수 시간에 폭탄 놓기, 홀수 시간에 폭탄 터지기
		if (t % 2 == 0) {
			// 폭탄 놓기
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 0) { // 빈자리 일때 폭탄 놓기
						map[r][c] = t + 3; // 3초후에 터진다
					}
				}
			}
		} else {
			// 폭탄 터지기
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == t) { // 터질 시간이다?
						map[r][c] = 0; // 빈자리로 바꿔주기
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (nr>=0 && nr<R && nc>=0 && nc<C) {
								if (map[nr][nc] != t) {
									// 지금 터질 폭탄이 있는 자리가 아닐 경우만 처리 해주자
									// 냅다 터트리면 ㅠ 다음 폭탄 처리가 제대로 안된다
									map[nr][nc] = 0; 								
								}
							}
						}
					}
				}	
			}
		}

		// N초가 되면 리턴
		if (t == N) {
			return;
		}

		// 다음 시간 고고
		bomberman(t + 1);
	}
}
