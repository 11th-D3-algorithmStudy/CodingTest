package algo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16173_조아름 {
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();

	}

	static void bfs() {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();

		q.add(new Point(0, 0)); // 좌표가 0,0
		visited[0][0] = true;

		int[] dx = { 0, 1 }; // 우, 아래
		int[] dy = { 1, 0 };

		while (!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x; // 시작점
			int y = p.y;

			int k = arr[x][y]; // 몇 칸 이동할지 결정
			for (int i = 0; i < 2; i++) {
				int nx = x + dx[i] * k;
				int ny = y + dy[i] * k;
				if(nx==N-1 && ny==N-1) { // 마지막 위치일 때
					System.out.println("HaruHaru");
					return;
				}
				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				q.add(new Point(nx, ny));
			}
		}
		System.out.println("Hing");
	}

}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
