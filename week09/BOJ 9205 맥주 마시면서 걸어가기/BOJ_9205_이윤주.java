package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_9205_이윤주 {
	/*
	 * Q. 맥주 마시면서 걸어가기
	 */

	static int t, n;
	static Point[] points;
	static boolean possible;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine()); // 편의점 개수
			points = new Point[n + 2]; // 좌표들 저장하는 배열
			// n + 2 개의 좌표
			for (int i = 0; i < n + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} // 입력 끝
			// 0: 시작지점, n+1:도착지점

			possible = false;
			if (distance(points[0], points[n + 1]) <= 1000) {
				// 지금 가진 맥주 다 마시는동안 도착할 수 있으면 성공
				System.out.println("happy");
				continue;
			}

			visited = new boolean[n + 2];

			bfs(0, n + 1); // 0부터 n+1까지 최단거리 구하기

			if (possible) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}

		} // 테스트케이스 끝
	}

	// 시작지점 0으로 n+1까지의 최단 거리 구하는 메서드
	private static void bfs(int start, int end) {
		Queue<Integer> queue = new ArrayDeque<>();

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			Point now = points[queue.poll()];

			if (now == points[end]) {
				// 페스티벌에 도착했다!
				possible = true;
			}

			for (int i = 1; i < n + 2; i++) {
				// 모든 지점 확인
				Point p = points[i];

				if (distance(now, p) <= 1000 && !visited[i]) {
					// 지금 가진 맥주로 갈 수 있는 거리이면서, 가지 않은 곳
					visited[i] = true;
					queue.offer(i);
				}
			}
		}

	}
    
	// a점과 b점 사이의 맨해튼 거리 구하는 메서드
	static int distance(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}