package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_조아름 {
	// 구글링을 통해 해결
	static int N, M, R, C, L;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수

		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 지하터널 세로 크기
			M = Integer.parseInt(st.nextToken()); // 지하터널 가로 크기
			R = Integer.parseInt(st.nextToken()); // 맨홀뚜껑 세로 크기
			C = Integer.parseInt(st.nextToken()); // 맨홀뚜껑 가로 크기
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간 크기

			arr = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			
			System.out.printf("#%d %d\n", c+1 , bfs());
		}

	}

	static int bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(R,C,1)); // 1시간 이후부터니까 time이 1부터
		visited[R][C] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int now = arr[p.x][p.y]; // 현 위치 
			int[][] next = op(now); // op에 현 위치 값을 넣고, next라는 2차원 배열에 op에서 가져온 값을 넣어준다.
			for(int[] dxdy : next) { // next를 돌리면서 dxdy로 출력
				int nx = p.x+dxdy[0]; // dxdy에서 0은 x값, 1은 y값
				int ny = p.y+dxdy[1];
				if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && arr[nx][ny]!=0) {
					if(p.time >=L) continue; // 시간을 벗어나면 continue
					boolean check = false ; // 사실 여기서부터 잘 이해가 안됌
					for(int[] a : op(arr[nx][ny])) { // 왜 이동?? , 마찬가지로 op를 한번 더 돌림
						int cx = nx + a[0];
						int cy = ny + a[1];
						if(cx == p.x && cy == p.y) { // ?
							check=true;
						}
						if(check) {
							visited[nx][ny]=true;
							q.add(new Point(nx,ny,p.time+1));
						}
					}
				}
				
			}
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]) count++; // 이동값 출력
			}
		}
		
		return count;
		
	}
	
	static int[][] op(int tunnel){ // 번호에 따른 이동가능 범위
		switch (tunnel) {
		case 1: 
			return new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
		case 2:
			return new int[][] {{1,0},{-1,0}};
		case 3 :
			return new int[][] {{0,1},{0,-1}};
		case 4:
			return new int[][] {{-1,0},{0,1}};
		case 5:
			return new int[][] {{1,0},{0,1}};
		case 6:
			return new int[][] {{1,0},{0,-1}};
		case 7:
			return new int[][] {{-1,0},{0,-1}};
		}
		return new int[][] {{0,0}};
	}

}

class Point{
	int x,y,time;

	public Point(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}
	
}
