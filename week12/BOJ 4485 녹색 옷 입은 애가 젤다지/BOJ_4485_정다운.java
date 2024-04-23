package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_정다운 {
	
	static class Point implements Comparable<Point>{
		int r, c, w; // 좌표, 비용

		public Point(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.w-o.w;
		}
	}
	
	
	static int N; 
	static int[][] map;
	static boolean[][] visit;
	
	static PriorityQueue<Point> pq;
	
	static int pCnt = 1; 
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		// 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		// 0이 입력될때까지 계속 입력받기
		label:
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break label;
			}
			map = new int[N][N];
			visit = new boolean[N][N];
			pq = new PriorityQueue<>();
			
			for (int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			pq.add(new Point(0, 0, map[0][0]));
			
			// 다익스트라 bfs
			while (!pq.isEmpty()) {
				Point next = pq.poll();
				
				if (visit[next.r][next.c]) continue;
				
				visit[next.r][next.c] = true;
				map[next.r][next.c] = next.w;
				
				for (int d=0; d<4; d++) {
					int nr = next.r+dr[d];
					int nc = next.c+dc[d];
					
					if (nr>=0 && nr<N && nc>=0 && nc<N) {
						int np = next.w + map[nr][nc];
						pq.add(new Point(nr, nc, np));
					}
				}
			}
			
			sb.append("Problem "+pCnt+": "+map[N-1][N-1]+"\n");
			
			pCnt++;
		}
		System.out.println(sb);
	}
}
