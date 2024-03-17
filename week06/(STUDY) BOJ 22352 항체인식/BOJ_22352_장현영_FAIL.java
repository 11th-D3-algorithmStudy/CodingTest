package week06;

import java.io.*;
import java.util.*;

public class BOJ_22352_장현영_FAIL {

	static int n;
	static int m;
	static int[] dr = { 1, 0, 0, -1 };
	static int[] dc = { 0, 1, -1, 0 };
	static int[][] arr;
	static boolean[][] visited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int sr=-1;
		int sc=-1;
		// arr2 접근 안함
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (arr[i][j] != temp) {
					cnt++; // 다른 곳만큼 count
					sr = i;
					sc = j;
				}
			}
		}
		
		if (sr==-1 || bfs(sr, sc) == cnt ) {
			System.out.println("YES");
		} else
			System.out.println("NO");

		// 다른 지점을 찾고 그 지점들이 bfs로 모두 연결된다면 true 아니면 false
	}

	public static int bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[n][m];
		visited[sr][sc] = true;
		queue.offer(new int[] { sr, sc });
		int result = 1;
		int value = arr[sr][sc];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			for (int idx = 0; idx < 4; idx++) {
				int nr = r + dr[idx];
				int nc = c + dc[idx];
				if (0 <= nr && nr < n && 0 <= nc && nc < m && arr[nr][nc] == value && !visited[nr][nc]) {
					// 갈 수 있음
					result++;
					visited[nr][nc] = true;
					int[] next = { nr, nc };
					queue.offer(next);
				}
			}
		}
		return result;
	}
}
