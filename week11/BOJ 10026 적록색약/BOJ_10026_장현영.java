package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_장현영 {

	// arr를 2개만들어서 정상, 적록색약 구분해서 영역 구하기
	// 적록색약 (빨강-초록 합쳐서 보기)
	// bfs case 2개, arr2개써서 bfs 2번 사용

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static boolean[][] visited;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		char[][] arr1 = new char[n][n];
		for (int i = 0; i < n; i++) {
			arr1[i] = br.readLine().toCharArray();
		}

		// G를 R로보는 arr2
		char[][] arr2 = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr1[i][j] != 'G')
					arr2[i][j] = arr1[i][j];
				else {
					arr2[i][j] = 'R';
				}
			}
		}
		visited = new boolean[n][n];
		// 1번째 bfs
		int cnt1 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					char color = arr1[i][j];
					bfs(i, j, color, arr1);
					cnt1++;
				}
			}
		}

		// 2번째 bfs
		visited = new boolean[n][n];
		int cnt2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					char color = arr2[i][j];
					bfs(i, j, color, arr2);
					cnt2++;
				}
			}
		}
		System.out.print(cnt1 + " " + cnt2);
	}

	public static void bfs(int r, int c, char color, char[][] arr) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		visited[r][c] = true;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int cr = curr[0];
			int cc = curr[1];
			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				if (!(0 <= nr && nr < n && 0 <= nc && nc < n))
					continue;
				if (arr[nr][nc] == color && !visited[nr][nc]) {
					queue.add(new int[] { nr, nc });
					visited[nr][nc] = true;
				}

			}
		}
	}
}
