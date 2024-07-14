package week12;

import java.io.*;
import java.util.*;

public class BOJ_16234_장현영 {
	// 인구이동
	// 풀이시간 1시간-2시간
    // static 변수명은 절대 사용하면 안됨 r, R 찾느라 오래걸림
	// 인구이동이 가능한지 bfs로 접근하기

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[][] arr;
	static boolean[][] visited;
	static int n, L, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		while (true) {
			visited = new boolean[n][n];
			int movingCnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j])
						movingCnt += bfs(i,j);
				}
			}
			// 이동안했다면 break
			if(movingCnt == 0) break;
			ans++;
		}
		System.out.println(ans);
	}

	static int bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> moveQ = new LinkedList<>();
		queue.offer(new int[] { r, c });
		visited[r][c] = true;
		int people = 0;
		int cnt = 0;
		boolean flag = false; // 이동 여부

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			r = curr[0];
			c = curr[1];
			moveQ.offer(curr);
			people += arr[r][c];
			cnt++;

			for (int idx = 0; idx < 4; idx++) {
				int nr = r + dr[idx];
				int nc = c + dc[idx];

				if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
					// 차이 계속 계산해서 갈 수 있는는 지 확인
					int diff = Math.abs(arr[r][c] - arr[nr][nc]);
					if (diff >= L && diff <= R) {
						visited[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
						flag = true;
					}
				}
			}
		}

		// 이동한 Q는 따로 담아서 인구 update 진행하기
		while (!moveQ.isEmpty()) {
			int[] moveCurr = moveQ.poll();
			int x = moveCurr[0];
			int y = moveCurr[1];
			arr[x][y] = people / cnt;
		}
		if(flag) 
			return 1; // 연합 형성
		return 0;
	}

}
