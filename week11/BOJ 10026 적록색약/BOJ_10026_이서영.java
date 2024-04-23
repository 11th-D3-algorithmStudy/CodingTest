package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_이서영 {
	static boolean[][] visited;
	static boolean[][] visitedForWeak;
	static int N;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int count = 0;
	static int weakCnt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}
		visited = new boolean[N][N];
		visitedForWeak = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				bfs(map, r, c);
				bfsForWeak(map, r, c);
			}
		}
		System.out.println(count + " " + weakCnt);
	}

	static void bfs(char[][] map, int r, int c) {
		if (!visited[r][c]) {
			count++;
			Queue<Integer> qX = new LinkedList<>();
			Queue<Integer> qY = new LinkedList<>();
			qX.offer(r);
			qY.offer(c);
			visited[r][c] = true;
			while (!qX.isEmpty()) {
				int row = qX.poll();
				int col = qY.poll();
				char colour = map[row][col];
				for (int d = 0; d < 4; d++) {
					int x = row + dx[d];
					int y = col + dy[d];
					if (x < N && x >= 0 && y < N && y >= 0 && !visited[x][y]) {
						if (map[x][y] == colour) {
							qX.offer(x);
							qY.offer(y);
							visited[x][y] = true;
						}
					}
				}
			}
		}
	}

	static void bfsForWeak(char[][] map, int r, int c) {
		if (!visitedForWeak[r][c]) {
			weakCnt++;
			Queue<Integer> qX = new LinkedList<>();
			Queue<Integer> qY = new LinkedList<>();
			qX.offer(r);
			qY.offer(c);
			visitedForWeak[r][c] = true;
			while (!qX.isEmpty()) {
				int row = qX.poll();
				int col = qY.poll();
				char colour = map[row][col];
				for (int d = 0; d < 4; d++) {
					int x = row + dx[d];
					int y = col + dy[d];
					if (x < N && x >= 0 && y < N && y >= 0 && !visitedForWeak[x][y]) {
						if (colour == 'R' || colour == 'G') {
							if (map[x][y] == 'R' || map[x][y] == 'G') {
								qX.offer(x);
								qY.offer(y);
								visitedForWeak[x][y] = true;
							}
						} else {
							if (map[x][y] == colour) {
								qX.offer(x);
								qY.offer(y);
								visitedForWeak[x][y] = true;
							}
						}
					}
				}
			}
		}
	}
}
