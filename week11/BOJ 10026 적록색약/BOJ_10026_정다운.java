package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10026_정다운 {

	static int N;
	static char[][] arr, cwarr; // 정상인, 색약 배열
	static boolean[][] visit, cwvisit;
	static int cnt, cwcnt = 0; // 결과

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// 정상인이 보는 배열과 색약이 보는 배열 2가지를 만들어서 dfs 진행
		// dfs 인자로 배열을 넘겨줬다면 dfs 메서드를 하나만 써도 됐겠다..~
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		cwarr = new char[N][N];
		visit = new boolean[N][N];
		cwvisit = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			arr[r] = input.toCharArray();
			input = input.replace('G', 'R'); // 초록 -> 빨강
			cwarr[r] = input.toCharArray();
		}

		// dfs 진행 고고
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visit[r][c]) {
					dfs(r, c, arr[r][c]);
					cnt++; // 구역 하나 탐색 완료
				}
				if (!cwvisit[r][c]) {
					cwdfs(r, c, cwarr[r][c]);
					cwcnt++;
				}
			}
		}
		
		System.out.println(cnt+" "+cwcnt);
	}

	// r, c : 좌표 / x : 구역 알파벳
	static void dfs(int r, int c, char x) {
		// 구역의 끝까지 탐색했다면 리턴
		if (visit[r][c]) {
			return;
		}

		visit[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (arr[nr][nc] == x) {
					dfs(nr, nc, x);
				}
			}
		}
	}

	static void cwdfs(int r, int c, char x) {
		// 구역의 끝까지 탐색했다면 리턴
		if (cwvisit[r][c]) {
			return;
		}

		cwvisit[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (cwarr[nr][nc] == x) {
					cwdfs(nr, nc, x);
				}
			}
		}
	}

}
