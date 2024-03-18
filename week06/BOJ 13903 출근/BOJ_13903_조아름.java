package algo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13903_조아름 {
	static int R, C, N; // 세로, 가로, 이동가능 규칙의 개수
	static int[][] arr;
	static int[] dx, dy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로

		arr = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		N = Integer.parseInt(br.readLine());
		dx = new int[N]; // 이동 배열 만들어 주기
		dy = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dx[i] = Integer.parseInt(st.nextToken());
			dy[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());

	}

	static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		
		for(int i=0;i<C;i++) {
			if(arr[0][i]==1) { // 첫 줄에서 1일 때
				q.add(new Point(0,i,0)); // 큐에서의 첫 시작점
				visited[0][i] = true; // 방문처리
			}
		}
		
		while(!q.isEmpty()) {
			Point p = q.poll(); // 첫 시작점
			for(int i=0;i<N;i++) { // N번만큼 이동
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx>=0 && nx<R && ny>=0 && ny<C && arr[nx][ny]==1 && !visited[nx][ny]) { // 델타배열 이동 시 확인, 방문하지 않았을 때 이동
					if(nx==R-1) {
						return p.dist+1;
					}
					q.add(new Point(nx, ny, p.dist+1));
					visited[nx][ny] = true;
				}
			}
			
		}
		return -1;
		
	}

}

class Point {
	int x,y,dist;

	public Point(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
}
