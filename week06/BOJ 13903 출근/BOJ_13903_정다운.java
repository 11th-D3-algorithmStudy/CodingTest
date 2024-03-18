package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13903_정다운 {

	static int R, C, N; // 행,열,델타배열 크기
	static int[][] blockArr; // 보도블록 상태 배열
	static int[] dr, dc; // 델타배열
	static boolean[][] visit; // 방문 여부 배열
	static int res;
	static Queue<int[]> queue;
	static boolean flag = false;

	public static void main(String[] args) throws Exception {
		
		// 첫번째 row 중 1인 경우에 마지막 row까지 가는 bfs를 시도한다
		// N방 탐색
		
		// 처음 시도시 첫번째 row가 1일때 bfs를 호출하도록 함
		// (main에서 for문 돌려서 bfs 실행)
		// => 시간초과........
		// bfs 내부에서 첫번째 row가 1인 경우를 queue에 넣고 시작하니 시간초과 해결
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		blockArr = new int[R][C];
		visit = new boolean[R][C];
		res = Integer.MAX_VALUE;
		queue = new LinkedList<>();

		for (int r = 0; r < R; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				blockArr[r][c] = Integer.parseInt(st2.nextToken());
			}
		}

		N = Integer.parseInt(br.readLine());
		dr = new int[N];
		dc = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());

			int nr = Integer.parseInt(st3.nextToken());
			int nc = Integer.parseInt(st3.nextToken());

			dr[i] = nr;
			dc[i] = nc;
		}

		bfs();

		// 해답이 없을 경우 어떻게 처리??????
		if (flag) {
			System.out.println(res);
		} else {
			System.out.println(-1);
		}

	}

	static void bfs() {
		for (int c = 0; c < C; c++) {
			if (blockArr[0][c] == 1) {
				int[] arr = { 0, c, 0 }; // r, c, 몇걸음 째 인지
				queue.add(arr);
				visit[0][c] = true;
			}
		}

		while (!queue.isEmpty()) {
			int[] next = queue.poll();
			if (next[0] == R - 1) { // 회사 도착
				// 루트부터 얼마나 왔는지 어떻게 확인?? -> 몇걸음째 왔는지도 저장 ㄱㄱ
				res = Math.min(res, next[2]);
				flag = true; // 해답 없는 경우 확인하기 위한 flag
				return;
			}

			int cnt = next[2] + 1; // 한걸음 추가
			for (int d = 0; d < N; d++) {
				int nr = next[0] + dr[d];
				int nc = next[1] + dc[d];
				
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visit[nr][nc] && blockArr[nr][nc] == 1) {
					int[] temp = { nr, nc, cnt };
					queue.add(temp);
					visit[nr][nc] = true;
				}
			}
		}

	}

}
