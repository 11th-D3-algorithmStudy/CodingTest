package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
	int size = 2;
	int fed = 0;
	int r;
	int c;

	public Shark(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public boolean feed(int x, int y) {
		r = x;
		c = y;
		if (++fed >= size) {
			size++;
			fed = 0;
			return true;
		}
		return false;
	}
}

class Fish {
	int r;
	int c;

	public Fish(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

}

class Point implements Comparable<Point> {
	int x;
	int y;
	int time;

	public Point(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(Point o) {
		if (this.time == o.time) {
			if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
		return this.time - o.time;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", time=" + time + "]";
	}

}

public class BOJ_16236_아기상어 {
	static Shark shark;
	static int totalTime;
	static int N;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean grown = true;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				if (num == 9) {
					shark = new Shark(r, c);
				}
			}
		}

		while (true) {
			List<Fish> fishes = findPrey(map);
			List<Point> available = new LinkedList<>();
			for (Fish f : fishes) {
				boolean[][] visited = new boolean[N][N];
				Point p = bfs(map, visited, shark.r, shark.c, f.r, f.c);
				if (p != null) {
					available.add(p);
				}
			}
			if (available.size() == 0) {
				break;
			}
			Collections.sort(available);
			Point p = available.get(0);
			map[p.x][p.y] = 0;
			map[shark.r][shark.c] = 0;
			totalTime += p.time;
//			System.out.println(p);
//			System.out.println(totalTime);
			shark.feed(p.x, p.y);

		}

		System.out.println(totalTime);
	}

	public static List<Fish> findPrey(int[][] map) {
		List<Fish> fishes = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0 && map[r][c] < shark.size) {
					fishes.add(new Fish(r, c));
				}
			}
		}
		return fishes;
	}

	public static Point bfs(int[][] map, boolean[][] visited, int startX, int startY, int targetX, int targetY) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(startX, startY, 0));
		visited[startX][startY] = true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.x;
			int c = p.y;
			if (r == targetX && c == targetY) {
				return p;
			}
			for (int d = 0; d < 4; d++) {
				int x = r + dx[d];
				int y = c + dy[d];
				if (x < N && x >= 0 && y < N && y >= 0 && !visited[x][y]) {
					if (map[x][y] <= shark.size) {
						visited[x][y] = true;
						q.offer(new Point(x, y, p.time + 1));
					}
				}
			}
		}
		return null;
	}
}
