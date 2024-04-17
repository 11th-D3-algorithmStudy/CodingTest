package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_정다운 {
	
	static class Cheese{
		int r, c, h;

		public Cheese(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}

		@Override
		public String toString() {
			return "Cheese [r=" + r + ", c=" + c + ", h=" + h + "]";
		}
	}
	
	static int N, M; 
	static int[][] map;
	static boolean[][] visit;
	static Queue<Cheese> queue = new ArrayDeque<>(); // bfs용
	static int res;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		// 외부공기 -1 표시
		// 치즈 제거할때마다 외부공기 처리
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int r=0; r<N; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st2.nextToken());
			}
		}
		
		// 일단 외부공기 처리
		// 모눈종이 가장자리는 치즈가 놓이지 않는다 -> 0,0 무조건 외부공기
		isOut(0,0);

		bfs();
		
		System.out.println(res);
	
	}

	static void bfs() {
		// 첫 1시간에 녹을 치즈 고르기
		findMeltingCheese(1);
		
		while(!queue.isEmpty()) {
			Cheese next = queue.poll();
			
			int r = next.r;
			int c = next.c;
			int h = next.h;
			
			// 녹여서 공기로 바꾸기
			// 치즈가 녹은 부분은 무조건 외부공기
			// 주변공기도 바꿔주자
			isOut(r,c);
			
			// 녹일 치즈 찾기~
			findMeltingCheese(h+1);
			
			res = h; // 결과 업데이트
		}
		
	}

	static void isOut(int r, int c) {
		map[r][c] = -1; // 외부공기 처리
		
		// dfs
		for (int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (nr>=0 && nr<N && nc>=0 && nc<M) {
				if (map[nr][nc] == 0) {
					isOut(nr,nc);
				}
			}
		}
		
	}
	
	static void findMeltingCheese(int h) {
		// 전체 맵 탐색하면서 외부공기와 2칸 이상 접촉한 치즈 찾기
		// 가장자리는 빼고 찾기
		for (int r=1; r<N-1; r++) {
			for (int c=1; c<M-1; c++) {
				if (map[r][c] == 1) { // 치즈세요? 
					int cnt = 0; // 외부 공기와 접촉한 정도
					
					for (int d=0; d<4; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						
						if (map[nr][nc] == -1) {
							cnt++;
						}
					}
					
					if (cnt >= 2 && !visit[r][c]) {
						queue.add(new Cheese(r, c, h));
						visit[r][c] = true;
					}
				}
			}
		}
	}
}
