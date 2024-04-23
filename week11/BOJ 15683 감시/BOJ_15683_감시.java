package week11;

import java.io.*;
import java.util.*;

public class BOJ_15683_감시 {
	static int N, M, res;
	static int[][] map;
	static boolean[][] watched;
	static List<CCTV> list;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class CCTV {
		int r, c, type;
		int[][] dir;

		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.dir = direction(type);
		}

		private int[][] direction(int type) {
			switch(type) {
			case 1: return new int[][] { {0}, {1}, {2}, {3} };
			case 2: return new int[][] { {0, 1}, {2, 3} };
			case 3: return new int[][] { {0, 2}, {0, 3}, {1, 2}, {1, 3} };
			case 4: return new int[][] { {0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3} };
			case 5: return new int[][] { {0, 1, 2, 3} };	
			default: return null;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		watched = new boolean[N][M];
		list = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				
				if (num != 0)
					watched[r][c] = true;
				
				if (1 <= num && num <= 5)
					list.add(new CCTV(r, c, num));
			}
		}
		
		// ----- 입력 끝 -----
		
		res = Integer.MAX_VALUE;
		
		solve(0);
		
		System.out.println(res);
	}

	
	static void solve(int idx) {
		// base case
		// - 모든 CCTV의 방향을 결정한 경우 
		if (idx == list.size()) {
			int sum = 0;  // 사각지대 개수 세기
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!watched[r][c])  sum++;
				}
			}
			
			res = Math.min(res, sum);
			return;
		}
		
		
		// recursive case
		CCTV cur = list.get(idx);
		
		for (int i = 0; i < cur.dir.length; i++) {
			// 원상복구를 위해 임시 저장
			boolean[][] tmp = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					tmp[r][c] = watched[r][c];
				}
			}
			
			for (int j = 0; j < cur.dir[i].length; j++) {
				int len = 1;
				while(true) {
					int nr = cur.r + dr[cur.dir[i][j]] * len;
					int nc = cur.c + dc[cur.dir[i][j]] * len;
					
					if (!inMap(nr, nc) || map[nr][nc] == 6)  break;
					
					watched[nr][nc] = true;
					len++;
				}
			} // 확정한 CCTV의 모든 방향별 감시처리 완료
			
			solve(idx + 1);
			
			// 원상복구
			watched = tmp;
		}
	}


	static boolean inMap(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
