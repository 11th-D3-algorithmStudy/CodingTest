package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16173_이규빈 {
	/*
	* Q. 점프왕 쪨리 (Small)
	* # 풀이시간 : 1시간 30분
	* # 메모리 / 실행시간 : 11608kb / 84ms
	* # 문제해석
	*  - 오른쪽 또는 아래쪽으로만 진행 가능 -> 방문했던 곳을 다시 방문할 일은 없으므로, 굳이 방문처리 X
	* # 막힌 부분 해결
	*  DFS로 하니까 메모리 초과 -> BFS로 변경해도 메모리 초과.. 대체 왜??
	*  -> 찾아보니 칸의 숫자가 0이면 큐가 계속 쌓여서 메모리가 초과되는 거였음
	*  -> 숫자가 0인 경우 bfs를 종료하도록 변경해 해결
	*/
	static int N;
	static int[][] map;
	static boolean flag;
	static Queue<Point> q;
	
	static class Point {
		int r, c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		flag = false;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ---------------- INPUT END ------------------
		
		q = new LinkedList<>();
		
		bfs();
		
		if(flag) {
			System.out.println("HaruHaru");
		} else {
			System.out.println("Hing");
		}
		
	}

	private static void bfs() {
		q.offer(new Point(0, 0));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			// [막힌 부분 해결] 숫자가 0인 경우 bfs를 종료한다.
			if (map[r][c] == 0) break;
			
			if (r == N - 1 && c == N - 1) {
				flag = true;
				break;
			}
			
			// 아래 방향 진행
			int nr = r + map[r][c];
			if (nr < N) {
				q.offer(new Point(nr, c));
			}
			
			// 오른쪽 방향 진행
			int nc = c + map[r][c];
			if (nc < N) {
				q.offer(new Point(r, nc));
			} 
		}
	}

}
